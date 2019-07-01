package com.example.shop.product;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.MyProductAdapter;
import com.example.shop.adapter.NatureAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.Detail;
import com.example.shop.enrty.MyRecycelView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailFragment extends SupportFragment {
    List<Detail> list = new ArrayList<>();
    List<Commodity.CommodityBean> beanList = new ArrayList<>();
    RecyclerView recyclerView;
    MyRecycelView detailRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initData();
        queryAllCommodity();
        recyclerView = view.findViewById(R.id.rv_nature);
        detailRecyclerView = view.findViewById(R.id.rv_phone);
        detailRecyclerView.setAdapter(new MyProductAdapter(getContext(),beanList));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        NatureAdapter adapter = new NatureAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }



    public void queryAllCommodity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<Commodity> itemCall = productApiService.queryAllCommodity();

        itemCall.enqueue(new Callback<Commodity>() {
            @Override
            public void onResponse(Call<Commodity> call, Response<Commodity> response) {

                //商品
                Commodity i = response.body();
                detailRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                MyProductAdapter my=new MyProductAdapter(getContext(),i.getCommodity());
//                detailRecyclerView.setNestedScrollingEnabled(false);
                detailRecyclerView.setAdapter(my);


            }

            @Override
            public void onFailure(Call<Commodity> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initData(){
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
        list.add(new Detail(R.drawable.balance,"1111","1111"));
    }
}
