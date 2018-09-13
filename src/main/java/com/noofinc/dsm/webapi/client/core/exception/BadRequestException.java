package com.noofinc.dsm.webapi.client.core.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class BadRequestException extends DsmWebApiErrorException {

    public BadRequestException(String message, DsmWebApiResponseError error) {
        super(message, error);
    }
}
