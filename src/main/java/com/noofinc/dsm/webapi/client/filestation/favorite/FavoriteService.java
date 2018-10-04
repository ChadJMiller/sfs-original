package com.noofinc.dsm.webapi.client.filestation.favorite;

import com.noofinc.dsm.webapi.client.filestation.common.PaginationAndSorting;

import java.util.Optional;

public interface FavoriteService {

    Favorite.FavoriteList list(PaginationAndSorting paginationAndSorting, Optional<String> statusFilter);
    Favorite.FavoriteList list();
    void add();
    void delete();
    void clearBroken();
    void edit();
    void replaceAll();
}