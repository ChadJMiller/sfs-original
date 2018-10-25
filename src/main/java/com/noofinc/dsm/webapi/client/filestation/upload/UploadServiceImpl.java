package com.noofinc.dsm.webapi.client.filestation.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noofinc.dsm.webapi.client.core.DsmWebapiResponse;
import com.noofinc.dsm.webapi.client.core.authentication.AuthenticationHolder;
import com.noofinc.dsm.webapi.client.filestation.exception.FileAlreadyExistsException;
import com.noofinc.dsm.webapi.client.core.AbstractDsmServiceImpl;
import com.noofinc.dsm.webapi.client.core.DsmUrlProvider;
import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiClientException;
import com.noofinc.dsm.webapi.client.filestation.common.OverwriteBehavior;
import com.noofinc.dsm.webapi.client.core.timezone.TimeZoneUtil;
//import com.sun.deploy.net.HttpResponse;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Could not succeed to implement this with RestTemplate
 * Implemented with Apache's HttpClient and Jackson's object mapper
 */
@Component
public class UploadServiceImpl extends AbstractDsmServiceImpl implements UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    private static final String API_ID = "SYNO.FileStation.Upload";

    private static final String DELIMITER = "--AaB03x";
    private static final String CRLF = "\r\n";

    @Value("${dsm.webapi.username}")
    private String username;
    @Value("${dsm.webapi.password}")
    private String password;
    @Value("${dsm.webapi.host}")
    private String host;

    @Autowired
    private DsmUrlProvider dsmUrlProvider;

    @Autowired
    private AuthenticationHolder authenticationHolder;

    @Autowired
    private TimeZoneUtil timeZoneUtil;

    @Autowired
    private ObjectMapper objectMapper;

    public UploadServiceImpl() {
        super(API_ID);
    }

    @Override
    public void uploadFile(String parentPath, String name, byte[] content) {
        uploadFile(UploadRequest.createBuilder(parentPath, name, content).build());
    }

    @Override
    public void uploadFile(UploadRequest uploadRequest) {
        DsmWebapiResponse response = doUploadRequest(uploadRequest);
        if(!response.isSuccess()) {
            switch (response.getError().getCode()) {
                case 1805:
                    throw new FileAlreadyExistsException(response.getError(), uploadRequest.getParentFolderPath(), uploadRequest.getFileName());
                case 414:
                    throw new FileAlreadyExistsException(response.getError(), uploadRequest.getParentFolderPath(), uploadRequest.getFileName());
                    // TODO handle other cases
            }
        }
    }


    @Override
    public void uploadFtpFile(String parentPath, String name, byte[] content) {
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(this.host);
            ftp.login(this.username, this.password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            InputStream is = new ByteArrayInputStream(content);
            ftp.storeFile(parentPath + name, is);
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException ioe) {
                //eat it
            }
        } catch (IOException e) {
            throw new DsmWebApiClientException("Unable to upload file via FTP", e);
        }
    }

    @Override
    public void uploadFileWithFtpFailOver(String parentPath, String name, byte[] content) {
        try {
            uploadFile(parentPath, name, content);
        } catch (Exception e) {
            LOGGER.info("RESTful file upload failed, attempting via FTP");
            uploadFtpFile(parentPath, name, content);
        }
    }

    private DsmWebapiResponse doUploadRequest(UploadRequest uploadRequest) {
        try {
            String request = createRequest(uploadRequest);
            LOGGER.debug("Upload Request Body: \n{}", request);

            Response response = Request.Post(createUrl())
                    .addHeader("Content-type", String.format("multipart/form-data, boundary=%s", DELIMITER.substring(2)))
                    .bodyByteArray(request.getBytes())
                    .execute();
                Content content = response.returnContent();
                LOGGER.debug("Response body: {}", content.asString());
                return objectMapper.readValue(content.asBytes(), DsmWebapiResponse.class);
        } catch (IOException e) {
            throw new DsmWebApiClientException("Could not upload file.", e);
        }
    }

    private String createUrl() {
        return UriComponentsBuilder.fromHttpUrl(dsmUrlProvider.getDsmUrl())
                .path("/webapi/entry.cgi")
                .toUriString();
    }

    private String createRequest(UploadRequest uploadRequest) {
        StringBuilder request = new StringBuilder();
        appendParameter(request, "api", getApiId());
        appendParameter(request, "version", "2");
        appendParameter(request, "method", "upload");
        appendParameter(request, "overwrite", Boolean.toString(true));
        appendParameter(request, "_sid", authenticationHolder.getLoginInformation().getSid());
        appendParameter(request, "path", uploadRequest.getParentFolderPath());
        appendParameter(request, "create_parents", Boolean.toString(uploadRequest.isCreateParents()));
        appendOverwriteBehaviorIfNeeded(request, uploadRequest.getOverwriteBehavior());
        appendTimeParameterIfNeeded(request, "mtime", uploadRequest.getLastModificationTime());
        appendTimeParameterIfNeeded(request, "crtime", uploadRequest.getCreationTime());
        appendTimeParameterIfNeeded(request, "atime", uploadRequest.getLastAccessTime());
        appendFileParameter(request, uploadRequest.getFileName(), uploadRequest.getContent());
        return request.toString();
    }

    private void appendOverwriteBehaviorIfNeeded(StringBuilder request, OverwriteBehavior overwriteBehavior) {
        switch (overwriteBehavior) {
            case OVERWRITE:
                appendParameter(request, "overwrite", "true");
                break;
            case SKIP:
                appendParameter(request, "overwrite", "true");
                break;
            case ERROR:
                // Default behavior: no parameter to add
                break;
            default:
                throw new AssertionError("Cannot happen");
        }
    }

    private void appendTimeParameterIfNeeded(StringBuilder request, String parameterName, Optional<LocalDateTime> time) {
        if(time.isPresent()) {
            long unixTime = time.get().toEpochSecond(timeZoneUtil.getZoneOffset()) * 1000;
            appendParameter(request, parameterName, Long.toString(unixTime));
        }
    }

    private void appendParameter(StringBuilder request, String parameterName, String parameterValue) {
        request
                .append(DELIMITER)
                .append(CRLF)
                .append(String.format("Content-Disposition: form-data; name=\"%s\"", parameterName))
                .append(CRLF)
                .append(parameterValue)
                .append(CRLF);
    }

    private void appendFileParameter(StringBuilder request, String fileName, byte[] fileContent) {
        request
                .append(DELIMITER)
                .append(CRLF)
                .append(String.format("Content-Disposition: form-data; name=\"file\"; filename=\"%s\"", fileName))
                .append(CRLF)
                .append("Content-Type: application/octet-stream")
                .append(CRLF)
                .append(CRLF)
                .append(new String(fileContent))
                .append(CRLF)
                .append(CRLF)
                .append(DELIMITER)
                .append("--");
    }
}
