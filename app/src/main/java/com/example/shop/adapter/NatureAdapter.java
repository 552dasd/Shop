package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.enrty.Detail;

import java.util.ArrayList;
import java.util.List;

public class NatureAdapter extends RecyclerView.Adapter<NatureAdapter.ViewHolder> {
    Context context;
    List<Detail> list = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nature_adapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Detail detail = list.get(position);
        holder.textView.setText(detail.getName());
        holder.tv.setText(detail.getTitle());
        holder.imageView.setImageResource(detail.getIv());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img);
            textView = itemView.findViewById(R.id.tv_detail_name);
            tv = itemView.findViewById(R.id.tv_detail_title);
        }
    }

    public NatureAdapter(Context context,List<Detail> list){
        this.list = list;
        this.context = context;
    }

}
