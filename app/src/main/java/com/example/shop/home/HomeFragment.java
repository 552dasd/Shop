package com.example.shop.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.Item;

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
    View view;


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

        Call<Item> itemCall = productApiService.queryAllProduct();

        itemCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
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
        view = inflater.inflate(R.layout.fragment_home, container, false);;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
