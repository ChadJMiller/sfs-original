package com.noofinc.dsm.webapi.client.core.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;

public class PermissionDeniedException extends DsmWebApiErrorException {

    public PermissionDeniedException(DsmWebApiResponseError error) {
        super("The logged in session does not have permission", error);
    }
}
