package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.MyApplication;
import com.example.shop.R;
import com.example.shop.enrty.ProductItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<ProductItem> objects;
    Context context;
    LayoutInflater layoutInflater;
    public ProductAdapter(Context context,List<ProductItem> objects){
        this.context = context;
        this.objects = objects;

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        ProductItem productItem = objects.get(position);
        ImageLoader.getInstance().displayImage(productItem.getImgProduct(),holder.img, MyApplication.getLoaderOptions());
        holder.newPrice.setText("¥"+productItem.getNewPrice()+"");
        holder.oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        holder.oldPrice.setText("¥"+productItem.getOldPrice()+"");
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView newPrice;
        TextView oldPrice;
        public ViewHolder( View view) {
            super(view);
            img = view.findViewById(R.id.img_product);
            newPrice =view.findViewById(R.id.new_price);
            oldPrice = view.findViewById(R.id.old_price);


        }
    }
}
