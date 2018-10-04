package com.noofinc.dsm.webapi.client.filestation.dirsize;

import com.noofinc.dsm.webapi.client.AbstractTest;
import com.noofinc.dsm.webapi.client.filestation.exception.NoSuchTaskException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DirSizeServiceImplTest extends AbstractTest {

    @Autowired
    private DirSizeService dirSizeService;

    @Test
    public void testSynchronousDirSize() throws Exception {
        DirSizeResult dirSizeResult = dirSizeService.synchronousDirSize("/noofinc-ws-it/");
        Assert.assertEquals(true, dirSizeResult.isFinished());
        Assert.assertEquals(3, dirSizeResult.getNumberOfDirectories());
        Assert.assertEquals(5, dirSizeResult.getNumberOfFiles());
        Assert.assertEquals(147743L, dirSizeResult.getTotalByteSize());
    }

    @Test
    public void testNotExistingDir() throws Exception {
        DirSizeResult dirSizeResult = dirSizeService.synchronousDirSize("/noofinc-ws-it/not-existing");
        Assert.assertEquals(true, dirSizeResult.isFinished());
        Assert.assertEquals(0, dirSizeResult.getNumberOfDirectories());
        Assert.assertEquals(0, dirSizeResult.getNumberOfFiles());
        Assert.assertEquals(0L, dirSizeResult.getTotalByteSize());

        dirSizeResult = dirSizeService.synchronousDirSize("/not-existing/not-existing");
        Assert.assertEquals(true, dirSizeResult.isFinished());
        Assert.assertEquals(0, dirSizeResult.getNumberOfDirectories());
        Assert.assertEquals(0, dirSizeResult.getNumberOfFiles());
        Assert.assertEquals(0L, dirSizeResult.getTotalByteSize());
    }

    @Test(expected = NoSuchTaskException.class)
    public void testGetStatusNotExistingTask() {
        DirSizeResult status = dirSizeService.status("Not Existing");
    }
}
