package com.example.shop.home;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.shop.MainActivity;
import com.example.shop.MyApplication;
import com.example.shop.MyImageLoader;
import com.example.shop.R;
import com.example.shop.adapter.GvItemAdapter;
import com.example.shop.adapter.GvProductAdapter;
import com.example.shop.adapter.MyProductAdapter;
import com.example.shop.adapter.MyRecyclerViewAdapter;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.GvItem;
import com.example.shop.enrty.GvProduct;
import com.example.shop.enrty.Init;
import com.example.shop.enrty.Item;
import com.example.shop.enrty.NoScroller;
import com.example.shop.enrty.ProductItem;
import com.example.shop.enrty.Time;
import com.example.shop.product.ProductDetailFragment;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

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
    List<ProductItem> productItems = new ArrayList<>();
    List<GvProduct> gvProducts = new ArrayList<>();
    MarqueeView marqueeView;
    RecyclerView recyclerView;
    RecyclerView recyclerViewProduct;
    ImageView imageView1,imageView2,imageView3;

    private ArrayList<String> imagePath = new ArrayList<>();
    private ArrayList<String> imageTitle= new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * 查询所有商品
     */
    public void queryInit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<Init> itemCall = productApiService.queryInit();

        itemCall.enqueue(new Callback<Init>() {
            @Override
            public void onResponse(Call<Init> call, Response<Init> response) {
                Init i = response.body();


                imagePath.add(i.getList().get(0).getCarouselImage1());
                imagePath.add(i.getList().get(0).getCarouselImage2());
                imagePath.add(i.getList().get(0).getCarouselImage3());
                imagePath.add(i.getList().get(0).getCarouselImage4());

                imageTitle.add(i.getList().get(0).getCarouselName1());
                imageTitle.add(i.getList().get(0).getCarouselName2());
                imageTitle.add(i.getList().get(0).getCarouselName3());
                imageTitle.add(i.getList().get(0).getCarouselName4());
                initView();

                //跑马灯
                List<String> messages = new ArrayList<>();
                messages.add(i.getList().get(0).getNew1());
                messages.add(i.getList().get(0).getNew2());
                messages.add(i.getList().get(0).getNew3());
                marqueeView.startWithList(messages);
                marqueeView.startWithList(messages, R.anim.anim_bottom_in, R.anim.anim_top_out);



                ImageLoader.getInstance().displayImage(i.getList().get(0).getBigImage1(), imageView1, MyApplication.getLoaderOptions());
                ImageLoader.getInstance().displayImage(i.getList().get(0).getBigImage2(), imageView2, MyApplication.getLoaderOptions());
                ImageLoader.getInstance().displayImage(i.getList().get(0).getBigImage3(), imageView3, MyApplication.getLoaderOptions());
                Log.e("value", "onResponse: "+ i.getList().get(0).getBigImage1() );
                Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Init> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                Log.e("tag", "onFailure: "+t.getMessage() );
            }
        });
    }


    public void queryInitTime() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        final Call<Time> itemCall = productApiService.querytimeProduct();

        itemCall.enqueue(new Callback<Time>() {
            @Override
            public void onResponse(Call<Time> call, Response<Time> response) {

                //秒杀
                Time i = response.body();

                productItems.add(new ProductItem(i.getFlash().get(0).getImg1(),i.getFlash().get(0).getMoney1()-100,i.getFlash().get(0).getMoney1()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg2(),i.getFlash().get(0).getMoney2()-100,i.getFlash().get(0).getMoney2()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg3(),i.getFlash().get(0).getMoney3()-100,i.getFlash().get(0).getMoney3()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg4(),i.getFlash().get(0).getMoney4()-100,i.getFlash().get(0).getMoney4()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg5(),i.getFlash().get(0).getMoney5()-100,i.getFlash().get(0).getMoney5()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg6(),i.getFlash().get(0).getMoney6()-100,i.getFlash().get(0).getMoney6()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg7(),i.getFlash().get(0).getMoney7()-100,i.getFlash().get(0).getMoney7()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImg8(),i.getFlash().get(0).getMoney8()-100,i.getFlash().get(0).getMoney8()));
                productItems.add(new ProductItem(i.getFlash().get(0).getImh9(),i.getFlash().get(0).getMoney9()-100,i.getFlash().get(0).getMoney9()));
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new ProductAdapter(getContext(),productItems));

                Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Time> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();

            }
        });
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
                recyclerViewProduct.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                MyProductAdapter my=new MyProductAdapter(getContext(),i.getCommodity());
                recyclerViewProduct.setAdapter(my);
                
            }

            @Override
            public void onFailure(Call<Commodity> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        refreshableView = view.findViewById(R.id.refresh);
        mBanner = view.findViewById(R.id.banner);
        marqueeView = view.findViewById(R.id.marqueeView);
        recyclerView =view.findViewById(R.id.recycler_view);
        scrollView = view.findViewById(R.id.scroll);
        imageView1 = view.findViewById(R.id.img_shop_1);
        imageView2 = view.findViewById(R.id.img_shop_2);
        imageView3 = view.findViewById(R.id.img_shop_3);
        gv = view.findViewById(R.id.gv);
        recyclerViewProduct = view.findViewById(R.id.gv_product);

        refreshableView.setType(SpringView.Type.FOLLOW);
        refreshableView.setHeader(new DefaultHeader(getContext()));
        refreshableView.setFooter(new DefaultFooter(getContext()));

        EventBus.getDefault().register(this);
        initData();
        queryInit();
        queryInitTime();
        queryAllCommodity();


        gv.setAdapter(new GvItemAdapter(getContext(),objects));


        refreshableView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshableView.onFinishFreshAndLoad();
                        queryInit();
                        queryInitTime();
                        queryAllCommodity();
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
    }

    private void initView() {

        Log.d("TAG", "onResponse: "+imagePath);
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


    /**
     * 跳转
     * @param id
     */
        @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
        public void onMessageEvent(Integer id) {
            int Id = id;

            start(new ProductDetailFragment());
            MainActivity mainActivity = new MainActivity();



        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            // 注销订阅者
            EventBus.getDefault().unregister(this);
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







