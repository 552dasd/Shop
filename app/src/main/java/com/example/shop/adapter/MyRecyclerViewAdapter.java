package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.enrty.Item;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Item.BodeBean> objects = new ArrayList<Item.BodeBean>();
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 1;// 购买的商品总数量
    private View view;

    public List<Item.BodeBean> getObjects() {
        return objects;
    }

    public void setObjects(List<Item.BodeBean> objects) {
        this.objects = objects;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvPrice;
        CheckBox checkBox;
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initData();
        }

        public void initData(){
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            checkBox = itemView.findViewById(R.id.cb_single);
            editText = itemView.findViewById(R.id.et_count);
        }
    }

        public MyRecyclerViewAdapter(List<Item.BodeBean> objects, View view){
        this.objects = objects;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_adapter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Item.BodeBean item = objects.get(position);
        holder.tvPrice.setText(item.getPrice()+"123123123");
        holder.tvName.setText(item.getTitle()+"11111111111111111");
        holder.editText.setText(2+"");
        //单选商品
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setCheck(b);
                sum((TextView) view.findViewById(R.id.tv_show_price),(TextView) view.findViewById(R.id.tv_settlement),(EditText)view.findViewById(R.id.et_count));
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void sum(TextView tv_show_price, TextView tv_settlement,EditText et){

        totalPrice = 0.00;
        totalCount = 0;
        int sum = 0;
        List<Item.BodeBean> list = getObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                sum = Integer.parseInt(et.getText().toString());
                totalCount = totalCount + sum;
                totalPrice = list.get(i).getPrice() * totalCount;
            }
        }
        tv_show_price.setText("合计:" + totalPrice);
        tv_settlement.setText("结算(" + totalCount + ")");
    }
}
