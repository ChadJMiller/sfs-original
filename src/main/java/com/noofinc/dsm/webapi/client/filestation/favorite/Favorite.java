package com.noofinc.dsm.webapi.client.filestation.favorite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noofinc.dsm.webapi.client.filestation.common.PaginatedList;
import com.noofinc.dsm.webapi.client.filestation.filelist.FileProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Favorite {
    private final String path;
    private final String name;
    private final String status;
    private final FavoriteProperties properties;

    @JsonCreator
    public Favorite(
            @JsonProperty("path") String path,
            @JsonProperty("name") String name,
            @JsonProperty("status") String status,
            @JsonProperty("additional") FavoriteProperties properties) {
        this.path = path;
        this.name = name;
        this.status = status;
        this.properties = properties;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String status () { return status; }

    public FavoriteProperties getProperties() {
        return properties;
    }

    public static class FavoriteList extends PaginatedList<Favorite> {

        public FavoriteList(@JsonProperty("total") int total,
                        @JsonProperty("offset") int offset,
                        @JsonProperty("favorites") List<Favorite> favorites
        ) {
            super(total, offset, favorites);
        }
    }

}