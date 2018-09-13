package com.noofinc.dsm.webapi.client.filestation.upload;

public interface UploadService {

    void uploadFile(String parentPath, String name, byte[] content);

    void uploadFile(UploadRequest uploadRequest);

}
