package com.noofinc.dsm.webapi.client.filestation.exception;

import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiErrorException;
import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class CouldNotCopyOrMoveFilesException extends DsmWebApiErrorException {

    public CouldNotCopyOrMoveFilesException(String message, DsmWebApiResponseError error) {
        super(message, error);
    }
}
