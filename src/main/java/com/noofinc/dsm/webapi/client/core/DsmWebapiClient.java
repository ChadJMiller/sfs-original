package com.noofinc.dsm.webapi.client.core;

import java.net.URI;

public interface DsmWebapiClient {

    <T extends DsmWebapiResponse<?>> T call(DsmWebapiRequest request, Class<T> responseType);

    <T extends DsmWebapiResponse<?>> T call(DsmWebapiRequest request, Class<T> responseType, ErrorHandler errorHandler);

    URI buildUri(DsmWebapiRequest request);
}
