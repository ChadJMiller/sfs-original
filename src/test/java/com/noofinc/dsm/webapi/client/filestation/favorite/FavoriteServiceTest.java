package com.noofinc.dsm.webapi.client.filestation.favorite;


import com.noofinc.dsm.webapi.client.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class FavoriteServiceTest extends AbstractTest {

    @Autowired
    FavoriteService favoriteService;

    @Test
    public void testList() throws Exception {
        Favorite.FavoriteList list = favoriteService.list();
        Assert.assertEquals(3, list.getElements().size());
    }

}
