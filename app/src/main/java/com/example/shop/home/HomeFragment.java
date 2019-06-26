package com.example.shop.home;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shop.MyImageLoader;
import com.example.shop.R;
import com.example.shop.adapter.GvItemAdapter;
import com.example.shop.adapter.MyPagerAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.GvItem;
import com.example.shop.enrty.Item;
import com.example.shop.enrty.News;
import com.example.shop.enrty.NoScroller;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

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

    SpringView refreshableView;
    NoScroller gv;
    ScrollView scrollView;
    MyImageLoader mMyImageLoader;
    Banner mBanner;
    List<GvItem> objects = new ArrayList<>();
    MarqueeView marqueeView;

    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;

    public HomeFragment() {
        // Required empty public constructor
    }


    /**
     * 查询所有商品
     */
//    public void queryAllProduct() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ProductApiService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ProductApiService productApiService = retrofit.create(ProductApiService.class);
//
//        Call<com.example.shop.enrty.Item> itemCall = productApiService.queryAllProduct();
//
//        itemCall.enqueue(new Callback<com.example.shop.enrty.Item>() {
//            @Override
//            public void onResponse(Call<com.example.shop.enrty.Item> call, Response<com.example.shop.enrty.Item> response) {
//                Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Item> call, Throwable t) {
//                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        refreshableView = view.findViewById(R.id.refresh);
        mBanner = view.findViewById(R.id.banner);
        marqueeView = view.findViewById(R.id.marqueeView);
        refreshableView.setType(SpringView.Type.FOLLOW);
        refreshableView.setHeader(new DefaultHeader(getContext()));
        refreshableView.setFooter(new DefaultFooter(getContext()));
        gv = view.findViewById(R.id.gv);
        scrollView = view.findViewById(R.id.scroll);
        gv.setAdapter(new GvItemAdapter(getContext(),objects));

        initData();
        initView();


        refreshableView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshableView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshableView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
        return view;
    }

    public void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.a);
        imagePath.add(R.drawable.b);
        imagePath.add(R.drawable.c);
        imageTitle.add("秒杀");
        imageTitle.add("抢购");
        imageTitle.add("鲜果计划");

        objects.add(new GvItem(R.drawable.news,"新品发布"));
        objects.add(new GvItem(R.drawable.mutli,"众筹"));
        objects.add(new GvItem(R.drawable.more,"超多惊喜"));
        objects.add(new GvItem(R.drawable.spell,"平团"));
        objects.add(new GvItem(R.drawable.sell,"超值特卖"));
        objects.add(new GvItem(R.drawable.time,"秒杀"));
        objects.add(new GvItem(R.drawable.change,"以旧换新"));
        objects.add(new GvItem(R.drawable.hot_sell,"电视热卖"));
        objects.add(new GvItem(R.drawable.electric,"家电热卖"));
        objects.add(new GvItem(R.drawable.card,"优惠卡"));

        List<String> messages = new ArrayList<>();
        messages.add(" 大家好");
        messages.add(" 欢迎大家！");
        messages.add(" 积分兑换");
        marqueeView.startWithList(messages);
        marqueeView.startWithList(messages, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    private void initView() {
        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //轮播图的监听
                //开始调用的方法，启动轮播图。
                .start();
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }
}
