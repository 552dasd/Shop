package com.example.shop.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MangerFragment extends SupportFragment {
    HomeFragment homeFragment;
    MeFragment meFragment;
    CarFragment carFragment;
    MessageFragment messageFragment;
    BottomNavigationView bottomNavigationView;
    BottomNavigationView navView;
    public MangerFragment() {
        // Required empty public constructor
    }
    public static MangerFragment newInstance() {
        return new MangerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manger, container, false);


        return view;
    }

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

    /**
     * 初始化
     */
    public void init(){
        homeFragment = new HomeFragment();
        carFragment = new CarFragment();
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();

    }
}
