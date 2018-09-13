package com.noofinc.dsm.webapi.client.core.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class DsmWebApiErrorException extends DsmWebApiClientException {

    private final DsmWebApiResponseError error;

    public DsmWebApiErrorException(String message, DsmWebApiResponseError error) {
        super(message);
        this.error = error;
    }

    public DsmWebApiErrorException(String message, Throwable cause, DsmWebApiResponseError error) {
        super(message, cause);
        this.error = error;
    }

    public DsmWebApiErrorException(Throwable cause, DsmWebApiResponseError error) {
        super(cause);
        this.error = error;
    }

    public DsmWebApiResponseError getError() {
        return error;
    }
}
