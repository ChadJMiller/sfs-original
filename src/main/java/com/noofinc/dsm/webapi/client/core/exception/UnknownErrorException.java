package com.noofinc.dsm.webapi.client.core.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class UnknownErrorException extends DsmWebApiErrorException {

    public UnknownErrorException(DsmWebApiResponseError error) {
        super("Unknown error", error);
    }
}
