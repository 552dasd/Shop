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
import android.widget.Toast;

import com.example.shop.R;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginByPhoneFragment extends SupportFragment {

    View view;
    EditText et_my_phone;
    EditText et_code;
    Button btn_get_code;
    TextView tv_by_password;
    Button b_login;

    public LoginByPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login_by_phone, container, false);
        MobSDK.init(getContext());
        initData();
        initListener();
        return view;
    }

    public void initData(){
        et_my_phone = view.findViewById(R.id.et_my_phone);
        et_code = view.findViewById(R.id.et_code);
        btn_get_code = view.findViewById(R.id.btn_get_code);
        tv_by_password = view.findViewById(R.id.tv_by_password);
        b_login = view.findViewById(R.id.b_login);
    }

    public void initListener(){
        tv_by_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(new LoginByPasswordFragment());

            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMSSDK.submitVerificationCode("86", et_my_phone.getText().toString(), et_code.getText().toString());
            }
        });
        btn_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 注册一个事件回调，用于处理SMSSDK接口请求的结果
                SMSSDK.registerEventHandler(eventHandler);

// 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", et_my_phone.getText().toString());

// 提交验证码，其中的code表示验证码，如“1357”

            }
        });
    }


    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Toast.makeText(LoginByPhoneFragment.this.getActivity(),"成功下发验证码",Toast.LENGTH_LONG).show();
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            Toast.makeText(LoginByPhoneFragment.this.getActivity(),"验证成功",Toast.LENGTH_LONG).show();
                            start(new HomeFragment());
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };


    // 使用完EventHandler需注销，否则可能出现内存泄漏
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
