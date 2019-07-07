package com.example.shop.receiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.Initcountdown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mob.MobSDK.getContext;

public class MyService extends Service {

    private int startId;
    MyReceiver myBroadcastReceiver;
    public enum Control{

        PLAY,PAUSE,STOP
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("1111111111", "pause: 4444444");

        return null;
    }

    public MyService() {
        super();


    }

/*  new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Log.d("1111111111", "pause: 333333333");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;

        return super.onStartCommand(intent, flags, startId);
    }

    private void pause() {

        myBroadcastReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyAction");
        registerReceiver(myBroadcastReceiver,intentFilter);
        Intent intent = new Intent();
        intent.setAction("MyAction");
        sendBroadcast(intent);
        Log.d("1111111111", "pause: 11111111");
    }


}
