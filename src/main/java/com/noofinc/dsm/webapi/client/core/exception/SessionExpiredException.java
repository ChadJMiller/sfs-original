package com.noofinc.dsm.webapi.client.core.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class SessionExpiredException extends DsmWebApiErrorException {

    public SessionExpiredException(String message, DsmWebApiResponseError error) {
        super(message, error);
    }
}
