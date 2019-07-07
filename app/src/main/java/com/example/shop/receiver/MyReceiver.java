package com.example.shop.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.shop.MainActivity;
import com.example.shop.R;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.Initcountdown;
import com.example.shop.home.LoginByPhoneFragment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.volley.VolleyLog.TAG;
import static com.mob.MobSDK.getContext;

public class MyReceiver extends BroadcastReceiver {


    public static final String id = "channel_1";
    public static final String name = "channel_name_1";

    Intent intent1 ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("success", "onReceive: 广播接收成功" + action);

        intent1 = intent;
        final long timeInterval = 10000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
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

                            if (i.getList().get(0).getSecond().equals(""+0) ) {

                            } else {
                                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                                NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
                                notificationManager.createNotificationChannel(channel);
                                intent1 = new Intent(context, MainActivity.class);
                                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
                                Notification.Builder builder = new Notification.Builder(context.getApplicationContext(), id)
                                        .setTicker("广播")
                                        .setContentTitle("有促销商品啦！")
                                        .setContentText("快来抢购呀！！！！点击了解详情")
                                        .setSmallIcon(R.drawable.no_image)
                                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setAutoCancel(true)
                                        .setOnlyAlertOnce(true)
                                        .setContentIntent(pendingIntent);

                                notificationManager.notify(1, builder.build());
                            }
                        }

                        @Override
                        public void onFailure(Call<Initcountdown> call, Throwable t) {
                            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }
    public void query(){

    }



}
