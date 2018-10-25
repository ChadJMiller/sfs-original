package com.noofinc.dsm.webapi.client.filestation.upload;

public interface UploadService {

    void uploadFile(String parentPath, String name, byte[] content);

    void uploadFile(UploadRequest uploadRequest);

    void uploadFtpFile(String parentPath, String name, byte[] content);

    void uploadFileWithFtpFailOver(String parentPath, String name, byte[] content);
}
