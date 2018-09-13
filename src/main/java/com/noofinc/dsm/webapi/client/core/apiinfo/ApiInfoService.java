package com.noofinc.dsm.webapi.client.core.apiinfo;

import java.util.List;

public interface ApiInfoService {
    List<ApiInfo> findAll();

    ApiInfo findOne(String api);
}
