package com.example.shop.apiserver;

import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.Init;
import com.example.shop.enrty.Item;
import com.example.shop.enrty.Time;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 用户查询ApiService
 */
public interface ProductApiService {
    public static final String BASE_URL = "http://nanhai655.cn:666/";
    //http://nanhai655.cn:8080/shop/init
    /**
     * 查询所有商品
     * @return 商品
     */
    @GET("user/queryAllItem ")
    Call<Item> queryAllProduct();

    @GET("shop/init")
    Call<Init> queryInit();

    @GET("shop/AllFlash")
    Call<Time> querytimeProduct();

    @GET("shop/AllCommodity")
    Call<Commodity> queryAllCommodity();
}

