package com.noofinc.dsm.webapi.client.core.exception;

public class DsmWebApiClientException extends RuntimeException {

    public DsmWebApiClientException(String message) {
        super(message);
    }

    public DsmWebApiClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DsmWebApiClientException(Throwable cause) {
        super(cause);
    }
}
