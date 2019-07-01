package com.example.shop;

import android.os.Bundle;

import com.example.shop.home.CarFragment;
import com.example.shop.home.HomeFragment;
import com.example.shop.home.MeFragment;
import com.example.shop.home.MessageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    HomeFragment homeFragment;
    MeFragment meFragment;
    CarFragment carFragment;
    MessageFragment messageFragment;
    BottomNavigationView bottomNavigationView;
    /**
     * 底部导航栏点击事件
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showHideFragment(homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    showHideFragment(messageFragment);
                    return true;
                case R.id.navigation_message:
                    showHideFragment(carFragment);
                    return true;
                case R.id.navigation_notifications:
                    showHideFragment(meFragment);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        init();

    }


    /**
     * 初始化
     */
    public void init(){
        homeFragment = new HomeFragment();
        carFragment = new CarFragment();
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();


        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ISupportFragment[] fragments = {homeFragment,carFragment,messageFragment,meFragment};
        loadMultipleRootFragment(R.id.main_layout,0,fragments);

    }


}
