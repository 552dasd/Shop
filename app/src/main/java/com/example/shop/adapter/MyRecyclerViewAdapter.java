package com.example.shop.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.enrty.Item;
import com.example.shop.enrty.UserShopping;
import com.example.shop.home.CarFragment;
import com.example.shop.home.HomeFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<UserShopping.CommodityBean> objects = new ArrayList<UserShopping.CommodityBean>();
    private View view;

    public List<UserShopping.CommodityBean> getObjects() {
        return objects;
    }

    public void setObjects(List<UserShopping.CommodityBean> objects) {
        this.objects = objects;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvPrice;
        CheckBox cbSingle;
        EditText etCount;
        View carView;
        ImageView ivImage;

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
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }

        public MyRecyclerViewAdapter(List<UserShopping.CommodityBean> objects, View view){

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
        final UserShopping.CommodityBean item = objects.get(position);
        holder.tvPrice.setText(item.getMoney()+"");
        holder.tvName.setText(item.getName());
        holder.etCount.setText(item.getCount()+"");
        ImageLoader.getInstance().displayImage(item.getImg(),holder.ivImage);
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
        List<UserShopping.CommodityBean> list = getObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                sum = 2;
                totalCount = totalCount + sum;
                totalPrice += list.get(i).getMoney();
            }
        }
        tv_show_price.setText("合计:" + totalPrice);

        tv_settlement.setText("结算(" + totalCount + ")");
        Context ctx = view.getContext();
        SharedPreferences sp = ctx.getSharedPreferences("SP",MODE_PRIVATE);
        //存入数据
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("STRING_KEY2", (int) totalPrice);

        editor.commit();





    }
}
