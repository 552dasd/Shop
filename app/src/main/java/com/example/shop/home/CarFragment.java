package com.example.shop.home;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shop.MyApplication;
import com.example.shop.R;
import com.example.shop.adapter.MyRecyclerViewAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.UserShopping;
import com.example.shop.product.ShopActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.ListPopupWindow.MATCH_PARENT;
import static com.mob.MobSDK.getContext;


public class CarFragment extends SupportFragment {

    private SwipeRecyclerView recyclerView ;

    private List<UserShopping.CommodityBean> objects = new ArrayList<UserShopping.CommodityBean>();
    private View view,adapterView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private TextView tv_null,tv_show_price,tv_settlement,bt_header_right;
    private CheckBox cb_all;
    private ImageView iv_car_null;
    ArrayList<UserShopping.CommodityBean> list = new ArrayList<UserShopping.CommodityBean>();
    public CarFragment() {
        // Required empty public constructor
    }
    //子线程运行
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    //结束刷新动作
                    test();
                    //更新adapter

                    break;
                case 1:
//                    myRecyclerViewAdapter.notifyDataSetChanged();
//                    test();
                    replaceFragment( new CarFragment(), true );
                  //  test();
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car, container, false);
        volley();

        initData();
        initListener();
        // 设置监听器。
        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        // 侧滑删除菜单点击监听。
        recyclerView.setOnItemMenuClickListener(new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                delete(list.get(position).getId());
                volley();
                handler.sendEmptyMessageDelayed(1,500);


            }
        });
        handler.sendEmptyMessageDelayed(0,500);

        return view;
    }
    //实例控件
    public void initData(){
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView = view.findViewById(R.id.recycler_view);
        iv_car_null = view.findViewById(R.id.iv_car_null);
        tv_null = view.findViewById(R.id.tv_null);
        cb_all  = view.findViewById(R.id.cb_all);
        tv_show_price = view.findViewById(R.id.tv_show_price);
        tv_settlement = view.findViewById(R.id.tv_settlement);
        bt_header_right = view.findViewById(R.id.bt_header_right);
        recyclerView  = view.findViewById(R.id.recycler_view);
    }

    //初始化监听
    public void initListener(){
        tv_settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShopActivity.class);
                startActivity(intent);

            }
        });
    }




    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {


            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                    .setBackground(R.color.sred)
                    .setText("删除") // 文字。
                    .setImage(R.mipmap.delete)
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(300)
                    .setHeight(MATCH_PARENT);
            rightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.

        }
    };

    public void volley(){
        String url ="http://nanhai655.cn:8080/shop/UserShopping?User=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                "",
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();


                            final ArrayList<UserShopping.CommodityBean> subjectList = gson.fromJson(response.getJSONArray("Commodity").toString(),new TypeToken<List<UserShopping.CommodityBean>>(){}.getType());
                         list = subjectList;




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley",error.toString());
                    }
                }
        );
        MyApplication.addRequest(jsonObjectRequest,"MainActivity");

    }

    public void test(){


        //判断购物车是否为空
        if (list.size()!=0) {
            iv_car_null.setVisibility(View.GONE);
            tv_null.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.GONE);//屏蔽recyclerview
        }

        //设置全选
        cb_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int j=0;j<list.size();j++) {
                    list.get(j).setCheck(b);
                }
                myRecyclerViewAdapter.notifyDataSetChanged();
                myRecyclerViewAdapter.sum(tv_show_price,tv_settlement);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()); //指定布局样式(显示规则)
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(list, view);

        recyclerView.setAdapter(myRecyclerViewAdapter);


    }
    public void  delete(int id){
        String url ="http://nanhai655.cn:8080/shop/findusershopping?User="+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                "",
                new Response.Listener< JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();


                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley",error.toString());
                    }
                }
        );
        MyApplication.addRequest(jsonObjectRequest,"MainActivity");

    }


}
