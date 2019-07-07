package com.example.shop;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.shop.home.CarFragment;
import com.example.shop.home.HomeFragment;
import com.example.shop.home.LoginByPhoneFragment;
import com.example.shop.home.MangerFragment;
import com.example.shop.home.MeFragment;
import com.example.shop.home.MessageFragment;
import com.example.shop.receiver.MyReceiver;
import com.example.shop.receiver.MyService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.MenuItem;
import android.view.View;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

private MyReceiver myReceiver;
private IntentFilter intentFilter;
Intent intent;
    HomeFragment homeFragment;
    MeFragment meFragment;
    CarFragment carFragment;
    MessageFragment messageFragment;
    BottomNavigationView bottomNavigationView;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadRootFragment( R.id.manger, new HomeFragment() );
        bottomNavigationView = findViewById(R.id.nav_view);
        navView = findViewById(R.id.nav_view);
        init();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



//        loadRootFragment(R.id.main_layout, MangerFragment.newInstance(),true,true);

        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //当网络发生变化的时候，系统广播会发出值为android.net.conn.CONNECTIVITY_CHANGE这样的一条广播
        //注册
        registerReceiver(myReceiver,intentFilter);
        intent = new Intent(this, MyService.class);
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        unregisterReceiver(myReceiver);
    }
    public void init(){
        homeFragment = new HomeFragment();
        carFragment = new CarFragment();
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadRootFragment( R.id.manger, new HomeFragment() );
                    replaceFragment( new HomeFragment(), true );
                    return true;
                case R.id.navigation_dashboard:
                    loadRootFragment( R.id.manger, new MessageFragment() );
                    replaceFragment( new MessageFragment(), true );
                    return true;
                case R.id.navigation_message:

                    loadRootFragment( R.id.manger, new CarFragment() );
                    replaceFragment( new CarFragment(), true );
                    return true;
                case R.id.navigation_notifications:
                    loadRootFragment( R.id.manger, new MeFragment() );
                    replaceFragment( new MeFragment(), true );
                    return true;

            }
            return false;
        }
    };
}
