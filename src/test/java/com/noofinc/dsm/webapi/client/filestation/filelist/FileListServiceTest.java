package com.noofinc.dsm.webapi.client.filestation.filelist;

import com.noofinc.dsm.webapi.client.filestation.common.File;
import com.noofinc.dsm.webapi.client.AbstractTest;
import com.noofinc.dsm.webapi.client.filestation.exception.FileNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class FileListServiceTest extends AbstractTest {

    @Autowired
    FileListService fileListService;

    @Test
    public void testList() throws Exception {
        List<File> list = fileListService.list("/noofinc-ws-it");
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testGetFiles() {
        List<File> files = fileListService.getFiles(Arrays.asList("/noofinc-ws-it/test-1", "/noofinc-ws-it/test-text-file.txt"));
        Assert.assertEquals(2, files.size());
    }

    @Test(expected = FileNotFoundException.class)
    public void testListWrongName() throws Exception {
        List<File> list = fileListService.list("/noofinc-ws-it/brol");
    }

    @Test
    public void testGetFilesWrongName() {
        List<File> files = fileListService.getFiles(Arrays.asList("/noofinc-ws-it/test-1", "/noofinc-ws-it/brol"));
        Assert.assertEquals(1, files.size());
    }
}
