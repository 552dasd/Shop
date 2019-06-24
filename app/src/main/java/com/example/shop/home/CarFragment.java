package com.example.shop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.MyRecyclerViewAdapter;
import com.example.shop.enrty.Item;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends SupportFragment implements CompoundButton.OnCheckedChangeListener {

    private RecyclerView recyclerView ;
    private List<Item.BodeBean> objects = new ArrayList<Item.BodeBean>();
    private View view;
    private View adapterView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    private TextView tv_null;
    private TextView tv_show_price;
    private TextView tv_settlement;
    private CheckBox cb_all;
    private ImageView iv_car_null;

    public CarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        initData();
        add();
        show();
        initListener();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()); //指定布局样式(显示规则)
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(objects,view);//实例适配器,传入输入
        recyclerView.setAdapter(myRecyclerViewAdapter); //设置适配器
        return view;
    }

    public void initData(){
        recyclerView = view.findViewById(R.id.recycler_view);
        iv_car_null = view.findViewById(R.id.iv_car_null);
        tv_null = view.findViewById(R.id.tv_null);
        cb_all  = view.findViewById(R.id.cb_all);
        tv_show_price = view.findViewById(R.id.tv_show_price);
        tv_settlement = view.findViewById(R.id.tv_settlement);
    }

    public void show(){
        if (objects.size()!=0) {
            iv_car_null.setVisibility(View.GONE);
            tv_null.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.GONE);  //屏蔽recyclerview
        }
    }

    public void add(){
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
        this.objects.add(new Item.BodeBean());
    }

    public void initListener(){
        cb_all.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        List<Item.BodeBean> list = myRecyclerViewAdapter.getObjects();
        for (int i=0;i<list.size();i++) {
            list.get(i).setCheck(b);
        }
        myRecyclerViewAdapter.setObjects(list);
        myRecyclerViewAdapter.notifyDataSetChanged();
        myRecyclerViewAdapter.sum(tv_show_price,tv_settlement,(EditText) view.findViewById(R.id.et_count));
    }
}
