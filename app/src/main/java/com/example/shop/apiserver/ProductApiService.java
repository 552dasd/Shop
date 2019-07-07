package com.example.shop.apiserver;

import com.example.shop.enrty.AllFlash;
import com.example.shop.enrty.Bean;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.Init;
import com.example.shop.enrty.Initcountdown;
import com.example.shop.enrty.Item;

import com.example.shop.enrty.MyAddress;
import com.example.shop.enrty.Time;
import com.example.shop.enrty.UserShopping;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 用户查询ApiService
 */
public interface ProductApiService {
    public static final String BASE_URL = "http://nanhai655.cn:8080/";
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

    @GET("shop/Initcountdown")
    Call<Initcountdown> time();

    @GET("shop/AllFlash")
    Call<AllFlash> allFlash();

    @GET("shop/Commodity")
    Call<Commodity> queryCommodity(@Query("id") int id);

    @GET("shop/UserShopping")
    Call<UserShopping> queryShopping(@Query("User")int id);

    @GET("shop/findusershopping")
    Call<UserShopping> deleteShoping(@Query("User")int id);

    @GET("shop/shopping")
    Call<Bean> addShop(@Query("commodityid")int id);

    @GET("shop/vagueCommodity")
    Call<Commodity> queryShop(@Query("shopping")String name);

    @GET("shop/map")
    Call<MyAddress> address(@Query("IP")String ip);
}

