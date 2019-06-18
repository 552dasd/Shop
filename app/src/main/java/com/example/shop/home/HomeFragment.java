package com.example.shop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.News;
import com.example.shop.adapter.MyPagerAdapter;
import com.example.shop.enrty.Item;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends SupportFragment {

    ArrayList<News> news;
    ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }


    /**
     * 查询所有商品
     */
    public void queryAllProduct(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<com.example.shop.enrty.Item> itemCall = productApiService.queryAllProduct();

        itemCall.enqueue(new Callback<com.example.shop.enrty.Item>() {
            @Override
            public void onResponse(Call<com.example.shop.enrty.Item> call, Response<com.example.shop.enrty.Item> response) {
                Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(getContext(),"失败",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.vp);
        initData();
        viewPager.setAdapter(new MyPagerAdapter(getContext(),news));
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2- (Integer.MAX_VALUE / 2) % news.size());
        autoPlay();
        return view;
    }
    public void initData(){
        news = new ArrayList<>();
        news.add(new News(R.drawable.a));
        news.add(new News(R.drawable.b));
        news.add(new News(R.drawable.c));
    }

    public void autoPlay(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
