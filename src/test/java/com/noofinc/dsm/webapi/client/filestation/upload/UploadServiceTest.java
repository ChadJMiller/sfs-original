package com.noofinc.dsm.webapi.client.filestation.upload;

import com.noofinc.dsm.webapi.client.AbstractTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class UploadServiceTest extends AbstractTest {

    @Autowired
    private UploadService uploadService;

    @Test
    public void testUpload() throws Exception {
        UploadRequest uploadRequest = UploadRequest.createBuilder("/noofinc-ws-it/createFiles" + System.currentTimeMillis(), "test-file.txt", "this is a test file with strsing content\nHelloWorld!\n".getBytes("UTF-8"))
                .createParents(true)
                .creationTime(LocalDateTime.of(1984, 3, 9, 10, 0))
                .lastAccessTime(LocalDateTime.of(1984, 3, 9, 10, 0))
                .lastModificationTime(LocalDateTime.of(1984, 3, 9, 10, 0))
                .build();
        uploadService.uploadFile(uploadRequest);

    }

    @Test
    public void testFtpUpload() {
        String fileName = "uploadTestFile.txt";
        try {
            File f = new File(getShareMountPoint() + "/" + fileName);
            FileUtils.forceDelete(f);
        } catch (IOException e) {
            int i = 1;
        }
        uploadService.uploadFtpFile("//noofinc-ws-it/", fileName, "Upload test1 file contents".getBytes());

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {

        }
        File f = new File(getShareMountPoint() + "/" + fileName);
        assert f.exists() && !f.isDirectory();
    }

    @Test
    public void testUploadFileWithFtpFailOver() {
        String fileName = "uploadTestFile.txt";
        try {
            File f = new File(getShareMountPoint() + "/" + fileName);
            FileUtils.forceDelete(f);
        } catch (IOException e) {
            int i = 1;
        }
        uploadService.uploadFileWithFtpFailOver("//noofinc-ws-it/", fileName, "Upload test1 file contents".getBytes());

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {

        }
        File f = new File(getShareMountPoint() + "/" + fileName);
        assert f.exists() && !f.isDirectory();
    }
}
