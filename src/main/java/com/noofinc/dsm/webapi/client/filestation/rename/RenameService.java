package com.noofinc.dsm.webapi.client.filestation.rename;

import com.noofinc.dsm.webapi.client.filestation.common.File;

public interface RenameService {

    File rename(String path, String name);

    File rename(String path, String name, String searchTaskId);

}
