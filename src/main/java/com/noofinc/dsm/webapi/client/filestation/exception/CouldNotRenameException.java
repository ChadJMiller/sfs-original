package com.noofinc.dsm.webapi.client.filestation.exception;

import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;
import com.noofinc.dsm.webapi.client.core.exception.DsmWebApiErrorException;

public class CouldNotRenameException extends DsmWebApiErrorException {

    private final String path;
    private final String name;

    public CouldNotRenameException(String path, String name, DsmWebApiResponseError error) {
        super(String.format("Could not rename. Path: %s, name: %s", path, name), error);
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
