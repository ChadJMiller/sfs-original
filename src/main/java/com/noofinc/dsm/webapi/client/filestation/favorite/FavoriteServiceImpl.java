package com.noofinc.dsm.webapi.client.filestation.favorite;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noofinc.dsm.webapi.client.core.DsmWebapiResponse;
import com.noofinc.dsm.webapi.client.filestation.common.ErrorCodes;
import com.noofinc.dsm.webapi.client.filestation.common.File;
import com.google.common.base.Joiner;
import com.noofinc.dsm.webapi.client.core.AbstractDsmServiceImpl;
import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;
import com.noofinc.dsm.webapi.client.core.DsmWebapiRequest;
import com.noofinc.dsm.webapi.client.core.ErrorHandler;
import com.noofinc.dsm.webapi.client.filestation.common.FileType;
import com.noofinc.dsm.webapi.client.filestation.common.PaginationAndSorting;
import com.noofinc.dsm.webapi.client.filestation.exception.FileNotFoundException;
import com.noofinc.dsm.webapi.client.filestation.favorite.Favorite;
import com.noofinc.dsm.webapi.client.filestation.favorite.FavoriteService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FavoriteServiceImpl extends AbstractDsmServiceImpl implements FavoriteService {

    // API Infos
    private static final String API_ID = "SYNO.FileStation.Favorite";
    private static final String API_VERSION = "1";

    // Methods
    private static final String METHOD_LIST = "list";
    private static final String METHOD_ADD = "add";
    private static final String METHOD_DELETE = "delete";

    // Parameters
    private static final String PARAMETER_OFFSET = "offset";
    private static final String PARAMETER_LIMIT = "limit";
    private static final String PARAMETER_STATUS_FILTER = "status_filter";
    private static final String PARAMETER_ADDITIONAL = "additional";
    // Parameter values
    private static final String PARAMETER_VALUE_ADDITIONAL = "real_path,owner,time,perm,mount_point_type";


    public FavoriteServiceImpl() {
        super(API_ID);
    }

    @Override
    public Favorite.FavoriteList list(PaginationAndSorting paginationAndSorting, Optional<String> statusFilter) {
        DsmWebapiRequest request = new DsmWebapiRequest(getApiId(), API_VERSION, getApiInfo().getPath(), METHOD_LIST)
                .parameter(PARAMETER_OFFSET, Integer.toString(paginationAndSorting.getOffset()))
                .parameter(PARAMETER_LIMIT, Integer.toString(paginationAndSorting.getLimit()))
                .parameter(PARAMETER_STATUS_FILTER, statusFilter)
                .parameter(PARAMETER_ADDITIONAL, PARAMETER_VALUE_ADDITIONAL);
        FavoriteListResponse response = getDsmWebapiClient().call(request, FavoriteListResponse.class);
        return response.getData();
    }

    @Override
    public Favorite.FavoriteList list() {
        return list(new PaginationAndSorting(0, 0, PaginationAndSorting.Sort.NAME, PaginationAndSorting.SortDirection.ASC), Optional.<String>empty());
    }


    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void clearBroken() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void replaceAll() {

    }

    private static class FavoriteListResponse extends DsmWebapiResponse<Favorite.FavoriteList> {

        @JsonCreator
        public FavoriteListResponse(
                @JsonProperty("success") boolean success,
                @JsonProperty("data") Favorite.FavoriteList data,
                @JsonProperty("error") DsmWebApiResponseError error) {
            super(success, data, error);
        }
    }

//    private static class FavoriteListErrorHandler implements ErrorHandler {
//        @Override
//        public void handleError(DsmWebapiRequest request, DsmWebApiResponseError error) {
//
//        }
//    }
}
