package com.example.shop.product;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shop.MainActivity;
import com.example.shop.My;
import com.example.shop.MyApplication;
import com.example.shop.MyImageLoader;
import com.example.shop.R;
import com.example.shop.adapter.HorizontalAdapteer;
import com.example.shop.adapter.MyProductAdapter;
import com.example.shop.adapter.MyRecyclerViewAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.AllFlash;
import com.example.shop.enrty.Bean;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.Detail;
import com.example.shop.enrty.Init;
import com.example.shop.enrty.MyRecycelView;
import com.example.shop.enrty.UserShopping;
import com.example.shop.home.CarFragment;
import com.example.shop.home.HomeFragment;
import com.google.gson.Gson;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mob.MobSDK.getContext;

public class ProductDetailActivity extends SwipeBackActivity {
    List<Detail> list = new ArrayList<>();
    List<Commodity.CommodityBean> beanList = new ArrayList<>();

    MyRecycelView detailRecyclerView;
    MyImageLoader mMyImageLoader;
    Banner banner;
    int id;
    LinearLayout add;
    private ArrayList<String> imagePath = new ArrayList<>();
    private ArrayList<String> imageTitle= new ArrayList<>();
    TextView title,price,detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Context ctx = this;
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        id = sp.getInt("STRING_KEY", 1);

        initData();
        initListener();
        queryAllCommodity();
        queryCommodity();


    }
    //控件监听
    public void initListener(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, My.class);
                startActivity(intent);


            Toast.makeText(ProductDetailActivity.this,"成功加入购物车",Toast.LENGTH_LONG).show();
            }
        });
    }
    //实例化控件
    public void initData(){
        detailRecyclerView = findViewById(R.id.rv_phone);
        banner = findViewById(R.id.big_img);
        add = findViewById(R.id.add_product);
        title = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);
        detailTitle =findViewById(R.id.product_detail_title);
    }

    //加入购物车
    public void addShopping( int id) {
        Log.d("qqqqqqqqq", "onResponse: 111111111");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("qqqqqqqqq", "onResponse: 22222222");
        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);
        Log.d("qqqqqqqqq", "onResponse: 33333333");
        Call<Bean> itemCall = productApiService.addShop(15);
        Log.d("qqqqqqqqq", "onResponse: 4444444444");
        itemCall.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {

                Log.d("qqqqqqqqq", "onResponse:5555555 "  );

            }
            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //设置轮播
    public void queryCommodity(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<Commodity> itemCall = productApiService.queryCommodity(id);

        itemCall.enqueue(new Callback<Commodity>() {
            @Override
            public void onResponse(Call<Commodity> call, Response<Commodity> response) {
                Commodity i = response.body();
                imagePath.add(i.getCommodity_image().get(0).getImg1());
                imagePath.add(i.getCommodity_image().get(0).getImg2());
                imagePath.add(i.getCommodity_image().get(0).getImg3());
                imageTitle.add(i.getCommodity_image().get(0).getTitle1());
                imageTitle.add(i.getCommodity_image().get(0).getTitle2());
                imageTitle.add(i.getCommodity_image().get(0).getTitle3());
                price.setText("¥"+i.getCommodity().get(0).getPrice()+"");
                title.setText(i.getCommodity().get(0).getTitle());
                detailTitle.setText(i.getCommodity().get(0).getSellPoint());
                init();

            }
            @Override
            public void onFailure(Call<Commodity> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //banner轮播
    public void  init(){
        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        banner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        banner.setImages(imagePath)
                //轮播图的监听
                //开始调用的方法，启动轮播图。
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {


                PictureConfig config = new PictureConfig.Builder()
                        .setListData(imagePath)	//图片数据List<String> list
                        .setPosition(position)	//图片下标（从第position张图片开始浏览）
                        .setDownloadPath("pictureviewer")	//图片下载文件夹地址
                        .setIsShowNumber(true)//是否显示数字下标
                        .needDownload(true)	//是否支持图片下载
                        .setPlacrHolder(R.mipmap.ic_launcher)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                        .build();
                ImagePagerActivity.startActivity(ProductDetailActivity.this,config);

            }
        });
    }

    //猜你喜欢商品列表
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
                detailRecyclerView.setAdapter(my);

            }

            @Override
            public void onFailure(Call<Commodity> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 跳转
     * @param id
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(Integer id) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }

}
