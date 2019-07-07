package com.example.shop.home;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.shop.MainActivity;
import com.example.shop.MyApplication;
import com.example.shop.MyImageLoader;
import com.example.shop.R;
import com.example.shop.adapter.GvItemAdapter;
import com.example.shop.adapter.HorizontalAdapteer;
import com.example.shop.adapter.MyProductAdapter;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.AllFlash;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.GvItem;
import com.example.shop.enrty.GvProduct;
import com.example.shop.enrty.Init;
import com.example.shop.enrty.Initcountdown;
import com.example.shop.enrty.NoScroller;
import com.example.shop.enrty.ProductItem;
import com.example.shop.enrty.Time;
import com.example.shop.product.ProductDetailActivity;
import com.example.shop.receiver.MyReceiver;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



import static android.content.Context.MODE_PRIVATE;




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

    MarqueeView marqueeView;
    RecyclerView recyclerView,recyclerViewProduct;
    ImageView imageView1,imageView2,imageView3;
    TextView time;
    SearchView searchView;
    private View view;
     String name = "";

    private ArrayList<String> imagePath = new ArrayList<>();
    private ArrayList<String> imageTitle= new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    //子线程运行
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    //结束刷新动作
                    queryInit();
                    queryInitTime();
                    queryAllCommodity();
                    queryAllFlash();

                    //更新adapter

                    break;
            }
        }
    };


    //轮播
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

            }

            @Override
            public void onFailure(Call<Init> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                Log.e("tag", "onFailure: "+t.getMessage() );
            }
        });
    }

    //水平
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
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new ProductAdapter(getContext(),productItems));
            }

            @Override
            public void onFailure(Call<Time> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //瀑布流所有商品
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

    //模糊搜索
    public void queryShop(String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<Commodity> itemCall = productApiService.queryShop(name);

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

    //水平布局商品
    public void queryAllFlash() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<AllFlash> itemCall = productApiService.allFlash();

        itemCall.enqueue(new Callback<AllFlash>() {
            @Override
            public void onResponse(Call<AllFlash> call, Response<AllFlash> response) {

                //商品
                AllFlash i = response.body();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
                HorizontalAdapteer my=new HorizontalAdapteer(getContext(),i.getFlash());
                recyclerView.setAdapter(my);
            }

            @Override
            public void onFailure(Call<AllFlash> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //秒杀倒计时
    public void queryTime() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<Initcountdown> itemCall = productApiService.time();

        itemCall.enqueue(new Callback<Initcountdown>() {
            @Override
            public void onResponse(Call<Initcountdown> call, Response<Initcountdown> response) {
                Initcountdown i = response.body();
                time.setText(i.getList().get(0).getTime());
            }

            @Override
            public void onFailure(Call<Initcountdown> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        refreshableView.setType(SpringView.Type.FOLLOW);
        refreshableView.setHeader(new DefaultHeader(getContext()));
        refreshableView.setFooter(new DefaultFooter(getContext()));
        handler.sendEmptyMessageDelayed(0,1000);
        EventBus.getDefault().register(this);
        Runnable runnable = new Runnable() {
            public void run() {
                queryTime();
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
        gv.setAdapter(new GvItemAdapter(getContext(),objects));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                name = newText;
                queryShop(name);
                return false;
            }
        });
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

    //九宫格布局,实例化控件
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

        refreshableView = view.findViewById(R.id.refresh);
        mBanner = view.findViewById(R.id.banner);
        marqueeView = view.findViewById(R.id.marqueeView);
        recyclerView =view.findViewById(R.id.recycler_view);
        scrollView = view.findViewById(R.id.scroll);
        imageView1 = view.findViewById(R.id.img_shop_1);
        imageView2 = view.findViewById(R.id.img_shop_2);
        imageView3 = view.findViewById(R.id.img_shop_3);
        gv = view.findViewById(R.id.gv);
        time = view.findViewById(R.id.time);
        searchView = view.findViewById(R.id.search_view);
        recyclerViewProduct = view.findViewById(R.id.gv_product);
    }

    //banner轮播设置
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

    /**
     * 跳转
     * @param id
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(Integer id) {
        int Id = id;

        Intent intent = new Intent(HomeFragment.this.getActivity(), ProductDetailActivity.class);
        startActivity(intent);

         Context ctx = HomeFragment.this.getActivity();
         SharedPreferences sp = ctx.getSharedPreferences("SP",MODE_PRIVATE);
                                        //存入数据
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("STRING_KEY", Id);
        editor.commit();
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







