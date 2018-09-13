package com.noofinc.dsm.webapi.client.core.apiinfo;

import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiClientException;

public class ApiNotFoundException extends DsmWebApiClientException {

    private final String apiId;

    public ApiNotFoundException(String apiId) {
        super(String.format("Api %s not found", apiId));
        this.apiId = apiId;
    }

    public String getApiId() {
        return apiId;
    }
}
