package com.example.shop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.shop.R;
import com.example.shop.adapter.MeGvItemAdapter;
import com.example.shop.enrty.MeGvItem;
import com.example.shop.enrty.NoScroller;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends SupportFragment {
    View view;
   NoScroller gridView;
   NoScroller noScroller;
    List<MeGvItem> list = new ArrayList<>();
    List<MeGvItem> secondList = new ArrayList<>();

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
        gridView.setAdapter(new MeGvItemAdapter(getContext(),list));
        noScroller.setAdapter(new MeGvItemAdapter(getContext(),secondList));
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
    }


    public void init(){
        gridView = view.findViewById(R.id.me_gv_list);
        noScroller = view.findViewById(R.id.me_gv);
    }

}
