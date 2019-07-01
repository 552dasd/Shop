package com.example.shop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shop.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginByPasswordFragment extends SupportFragment {
    View view;
    EditText et_phone;
    EditText et_password;
    TextView tv_by_phone;
    Button btn_login;

    public LoginByPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login_by_password, container, false);
        initData();
        initListener();
        return view;
    }

    public void initData(){
        et_phone = view.findViewById(R.id.et_phone);
        et_password = view.findViewById(R.id.et_password);
        tv_by_phone = view.findViewById(R.id.tv_by_phone);
        btn_login = view.findViewById(R.id.btn_login);
    }

    public void initListener(){
        tv_by_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(new LoginByPhoneFragment());

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(new HomeFragment());
            }
        });
    }


// 在尝试读取通信录时以弹窗提示用户（可选功能）



}
