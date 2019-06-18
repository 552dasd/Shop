package com.example.shop.com.example.zds;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;


public class Pager extends PagerAdapter {

    ArrayList<News> news;
    Context context;
    public Pager( Context context,ArrayList<News> news){
        this.news = news;
        this.context = context;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.pager_img,null);

        ImageView imageView = view.findViewById(R.id.img);
        imageView.setImageResource(news.get(position % news.size()).getId());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
