package com.noofinc.dsm.webapi.client.filestation.favorite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noofinc.dsm.webapi.client.filestation.common.TimeInformation;
import com.noofinc.dsm.webapi.client.filestation.common.User;
import com.noofinc.dsm.webapi.client.filestation.filelist.FilePermission;

public class FavoriteProperties {

    private final String realPath;
    private final User owner;
    private final TimeInformation timeInformation;
    private final FilePermission filePermission;
    private final String mountPointType;
    private final String type;

    @JsonCreator
    public FavoriteProperties(
            @JsonProperty("real_path") String realPath,
            @JsonProperty("owner") User owner,
            @JsonProperty("time") TimeInformation timeInformation,
            @JsonProperty("perm") FilePermission filePermission,
            @JsonProperty("mount_point_type") String mountPointType,
            @JsonProperty("type") String type) {
        this.realPath = realPath;
        this.owner = owner;
        this.timeInformation = timeInformation;
        this.filePermission = filePermission;
        this.mountPointType = mountPointType;
        this.type = type;
    }

    public String getRealPath() {
        return realPath;
    }

    public User getOwner() {
        return owner;
    }

    public TimeInformation getTimeInformation() {
        return timeInformation;
    }

    public FilePermission getFilePermission() {
        return filePermission;
    }

    public String getMountPointType() {
        return mountPointType;
    }

    public String getType() {
        return type;
    }
}
