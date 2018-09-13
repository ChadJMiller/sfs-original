package com.noofinc.dsm.webapi.client.filestation.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;
import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiErrorException;

public class NoSuchTaskException extends DsmWebApiErrorException {

    public NoSuchTaskException(DsmWebApiResponseError error) {
        super("No such task of the file operation", error);
    }
}
