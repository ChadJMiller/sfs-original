package com.noofinc.dsm.webapi.client.filestation.exception;

import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiErrorException;
import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class FileAlreadyExistsException extends DsmWebApiErrorException {

    private final String parentFolderPath;
    private final String fileName;

    public FileAlreadyExistsException(DsmWebApiResponseError error, String parentPath, String fileName) {
        super(String.format("File already exists: %s/%s", parentPath, fileName), error);
        this.parentFolderPath = parentPath;
        this.fileName = fileName;
    }

    public String getParentFolderPath() {
        return parentFolderPath;
    }

    public String getFileName() {
        return fileName;
    }
}
