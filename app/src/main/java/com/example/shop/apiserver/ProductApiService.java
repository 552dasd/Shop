package com.example.shop.apiserver;

import com.example.shop.enrty.Item;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 用户查询ApiService
 */
public interface ProductApiService {
    public static final String BASE_URL = "http://192.168.1.102:8080/ShopServer_war_exploded/";

    /**
     * 查询所有商品
     * @return 商品
     */
    @GET("user/queryAllItem ")
    Call<Item> queryAllProduct();

}

