package com.example.shop.home;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.AdapterMeAdapter;
import com.example.shop.adapter.MeGvItemAdapter;
import com.example.shop.enrty.Me;
import com.example.shop.enrty.MeGvItem;
import com.example.shop.enrty.MyListView;
import com.example.shop.enrty.NoScroller;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends SupportFragment {
    View view;
   NoScroller gridView;
   NoScroller noScroller;
    List<MeGvItem> list = new ArrayList<>();
    List<MeGvItem> secondList = new ArrayList<>();
    List<Me> sList = new ArrayList<>();
    MyListView myListView ;
    TextView me_nike;
    ImageView me_head;
    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_me, container, false);
        init();
        initData();
        initListener();
        gridView.setAdapter(new MeGvItemAdapter(getContext(),list));
        noScroller.setAdapter(new MeGvItemAdapter(getContext(),secondList));
        myListView = view.findViewById(R.id.lv_me);
        myListView.setAdapter(new AdapterMeAdapter(getContext(),sList));
        //收id
        Context ctx = getContext();
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        String name = sp.getString("STRING_KEY3", "用户名");
        me_nike.setText(name);
        onResume();
        return view;
    }

    private void initData() {
        list.add(new MeGvItem(R.drawable.payment,"待付款"));
        list.add(new MeGvItem(R.drawable.car,"待收货"));
        list.add(new MeGvItem(R.drawable.evaluate,"待评价"));
        list.add(new MeGvItem(R.drawable.replace,"退换修"));

        secondList.add(new MeGvItem(R.drawable.coupon,"优惠券"));
        secondList.add(new MeGvItem(R.drawable.balance,"可用额度"));
        secondList.add(new MeGvItem(R.drawable.gift,"礼品卡"));
        secondList.add(new MeGvItem(R.drawable.wallet,"我的钱包"));

        sList.add(new Me("会员中心",R.drawable.balance));
        sList.add(new Me("直供点",R.drawable.balance));
        sList.add(new Me("领券中心",R.drawable.balance));
        sList.add(new Me("闲置换钱",R.drawable.balance));
        sList.add(new Me("每日返现",R.drawable.balance));
        sList.add(new Me("更多功能",R.drawable.balance));
    }

    public void init(){
        gridView = view.findViewById(R.id.me_gv_list);
        noScroller = view.findViewById(R.id.me_gv);
        me_nike = view.findViewById(R.id.me_nike);
        me_head = view.findViewById(R.id.me_head);
    }

    public void initListener(){
        me_nike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(new LoginByPhoneFragment());
            }
        });

        me_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(new LoginByPhoneFragment());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
