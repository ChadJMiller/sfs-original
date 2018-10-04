package com.noofinc.dsm.webapi.client.filestation.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noofinc.dsm.webapi.client.core.AbstractDsmServiceImpl;
import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;
import com.noofinc.dsm.webapi.client.core.DsmWebapiRequest;
import com.noofinc.dsm.webapi.client.core.DsmWebapiResponse;
import org.springframework.stereotype.Service;

@Service
public class FileStationInformationServiceImpl extends AbstractDsmServiceImpl implements FileStationInformationService {

    // API Infos
    private static final String API_ID = "SYNO.FileStation.Info";
    private static final String API_VERSION = "1";

    // API Methods
    private static final String METHOD_GET_INFO = "get";

    public FileStationInformationServiceImpl() {
        super(API_ID);
    }

    @Override
    public FileStationInformation getFileStationInformation() {
        FileStationInformationResponse response = getDsmWebapiClient().call(new DsmWebapiRequest(getApiInfo().getApi(), API_VERSION, getApiInfo().getPath(), METHOD_GET_INFO), FileStationInformationResponse.class);
        return response.getData();
    }

    public static class FileStationInformationResponse extends DsmWebapiResponse<FileStationInformation> {

        @JsonCreator
        public FileStationInformationResponse(@JsonProperty("success") boolean success,
                                              @JsonProperty("data") FileStationInformation data,
                                              @JsonProperty("error") DsmWebApiResponseError error) {
            super(success, data, error);
        }
    }
}
