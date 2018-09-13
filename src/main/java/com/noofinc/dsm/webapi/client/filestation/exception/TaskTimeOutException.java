package com.noofinc.dsm.webapi.client.filestation.exception;

import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiClientException;

public class TaskTimeOutException extends DsmWebApiClientException {

    public TaskTimeOutException(Throwable cause) {
        super("Task did not complete within allowed delay", cause);
    }

    public TaskTimeOutException() {
        super("Task did not complete within allowed delay");
    }
}
