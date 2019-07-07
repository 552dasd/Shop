package com.example.shop.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Imag;
import com.example.shop.MainActivity;
import com.example.shop.My;
import com.example.shop.MyApplication;
import com.example.shop.R;
import com.example.shop.adapter.CommitAdapter;
import com.example.shop.adapter.HorizontalAdapteer;
import com.example.shop.adapter.bean;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.AllFlash;
import com.example.shop.enrty.MyAddress;
import com.example.shop.enrty.UserShopping;
import com.example.shop.home.CarFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mob.MobSDK.getContext;

public class ShopActivity extends SwipeBackActivity {
    TextView person_name,tel,person_adress,together_price,together;
    LinearLayout back;
    ListView listView;
    Button button;
    int id;
    int price;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        imageView = findViewById(R.id.erweima);


        //收id
        Context ctx = this;
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        id = sp.getInt("STRING_KEY", 1);
        price = sp.getInt("STRING_KEY2", 1);



        initData();
        queryAddress();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ShopActivity.this,"成功提交订单",Toast.LENGTH_LONG).show();
                dingdan();
                Intent intent = new Intent(getContext(), ShopActivity.class);
                startActivity(intent);
                finish();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void initData(){
        person_name = findViewById(R.id.person_name);
        tel = findViewById(R.id.tel);
        person_adress = findViewById(R.id.person_adress);
        button = findViewById(R.id.commit);
        together_price = findViewById(R.id.together_price);
        together = findViewById(R.id.together);
        back = findViewById(R.id.back);
        listView = findViewById(R.id.pro_list);

    }



    public void queryAddress() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<MyAddress> itemCall = productApiService.address(getIPAddress(this));

        itemCall.enqueue(new Callback<MyAddress>() {
            @Override
            public void onResponse(Call<MyAddress> call, Response<MyAddress> response) {


                MyAddress i = response.body();
                person_adress.setText(i.getUserList().getCity()+"");

                Log.e("223", "onResponse: "+person_adress +getIPAddress(getContext()).toString());

                queryPro();


            }

            @Override
            public void onFailure(Call<MyAddress> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //点击完返回键，执行的动作
            finish();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);


        }
        return true;
    }



    public void queryPro() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<UserShopping> itemCall = productApiService.queryShopping(1);

        itemCall.enqueue(new Callback<UserShopping>() {
            @Override
            public void onResponse(Call<UserShopping> call, Response<UserShopping> response) {


                UserShopping i = response.body();
                if(i.getCommodity().size()==0){
                    Intent intent = new Intent(getContext(), Imag.class);
                    startActivity(intent);
                    finish();
                }else {
                    CommitAdapter adapter = new CommitAdapter(getContext(),i.getCommodity());
                    listView.setAdapter(adapter);
                    together.setText("共："+i.getCommodity().size()+"件");
                    together_price.setText("合计："+"¥"+price);
                    code("提交了"+i.getCommodity().size()+"件商品");
                }



            }

            @Override
            public void onFailure(Call<UserShopping> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });



    }



    //获取ip地址
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

  /*  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(bean phone) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }*/
public void dingdan(){
My my = new My();
my.dingdan(1);
}
public void code(String co){
    My my = new My();
    my.code(co);
}



}
