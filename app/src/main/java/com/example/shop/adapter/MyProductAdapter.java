package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.MyApplication;
import com.example.shop.R;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.Item;
import com.example.shop.enrty.ProductItem;
import com.example.shop.home.HomeFragment;
import com.example.shop.home.MeFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.ViewHolder> {
    List<Commodity.CommodityBean> objects;
    Context context;
    LayoutInflater layoutInflater;
    public MyProductAdapter(Context context,List<Commodity.CommodityBean> objects){
        this.context = context;
        this.objects = objects;

    }

    @Override
    public MyProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gv_product,parent,false);


        MyProductAdapter.ViewHolder holder = new MyProductAdapter.ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(MyProductAdapter.ViewHolder holder, final int position) {
        final Commodity.CommodityBean productItem = objects.get(position);
        ImageLoader.getInstance().displayImage(productItem.getImage(),holder.gvImgPro);
        holder.gvImgProName.setText(productItem.getTitle());
        holder.gvProPre.setText(productItem.getSellPoint());
        holder.gvProNewPrice.setText("¥"+(productItem.getPrice()/100));
        holder.gvProOldPrice.setText("¥"+productItem.getPrice());
        holder.gvProOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        holder.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Commodity.CommodityBean commodityBean = objects.get(position);
                EventBus.getDefault().post(productItem.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return objects.size();

    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        View objects;
        private ImageView gvImgPro;
        private TextView gvImgProName;
        private TextView gvProPre;
        private TextView gvProNewPrice;
        private TextView gvProOldPrice;
        private LinearLayout btnGo;


        public ViewHolder(View view) {
            super(view);
            objects = view;
            gvImgPro = (ImageView) view.findViewById(R.id.gv_img_pro);
            gvImgProName = (TextView) view.findViewById(R.id.gv_pro_name);
            gvProPre = view.findViewById(R.id.gv_pro_pre);
            gvProNewPrice = view.findViewById(R.id.gv_pro_new_price);
            gvProOldPrice = view.findViewById(R.id.gv_pro_old_price);
            btnGo = view.findViewById(R.id.btn_go);

        }
    }

}
