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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Item.BodeBean> objects = new ArrayList<Item.BodeBean>();
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
        CheckBox cbSingle;
        EditText etCount;
        View carView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carView = itemView;
            initData();
        }

        public void initData(){
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            cbSingle = itemView.findViewById(R.id.cb_single);
            etCount = itemView.findViewById(R.id.et_count);
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Item.BodeBean item = objects.get(position);
        holder.tvPrice.setText(item.getPrice()+"123123123");
        holder.tvName.setText(item.getTitle()+"11111111111111111");
        holder.cbSingle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                item.setCheck(isChecked);
                sum((TextView) view.findViewById(R.id.tv_show_price), (TextView) view.findViewById(R.id.tv_settlement));
            }});
        holder.cbSingle.setChecked(item.isCheck());

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    /**
     * 结算
     *
     * @param tv_show_price 显示合计的View
     * @param tv_settlement 显示结算个数的View
     */
    public void sum(TextView tv_show_price, TextView tv_settlement) {
        double totalPrice = 0.00;
         int totalCount = 0;
        int sum = 0;
        List<Item.BodeBean> list = getObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                sum = 2;
                totalCount = totalCount + sum;
                totalPrice += 1;
            }
        }
        tv_show_price.setText("合计:" + totalPrice);
        tv_settlement.setText("结算(" + totalCount + ")");
    }
}
