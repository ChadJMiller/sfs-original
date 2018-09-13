package com.noofinc.dsm.webapi.client.core;

public interface ErrorHandler {

    void handleError(DsmWebapiRequest request, DsmWebApiResponseError error);
}
