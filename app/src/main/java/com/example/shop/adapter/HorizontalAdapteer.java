package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.enrty.AllFlash;
import com.example.shop.enrty.Commodity;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class HorizontalAdapteer extends RecyclerView.Adapter<HorizontalAdapteer.ViewHolder>{

    List<AllFlash.FlashBean> objects;
    Context context;
    LayoutInflater layoutInflater;
    public HorizontalAdapteer(Context context,List<AllFlash.FlashBean> objects){

        this.context = context;
        this.objects = objects;

    }

    @Override
    public HorizontalAdapteer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);

        HorizontalAdapteer.ViewHolder holder = new HorizontalAdapteer.ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(HorizontalAdapteer.ViewHolder holder, int position) {
        AllFlash.FlashBean flashBean = objects.get(position);
        ImageLoader.getInstance().displayImage(flashBean.getImg(),holder.gvImgPro);


        holder.gvProNewPrice.setText("¥"+(flashBean.getMoney()-100));
        holder.gvProOldPrice.setText("¥"+flashBean.getMoney());
        holder.gvProOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);

    }


    @Override
    public int getItemCount() {
        return objects.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView gvImgPro;
        private TextView gvProNewPrice;
        private TextView gvProOldPrice;



        public ViewHolder(View view) {
            super(view);
            gvImgPro =  view.findViewById(R.id.img_product);
            gvProNewPrice = view.findViewById(R.id.new_price);
            gvProOldPrice = view.findViewById(R.id.old_price);


        }
    }


}
