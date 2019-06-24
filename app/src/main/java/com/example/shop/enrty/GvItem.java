package com.example.shop.enrty;

import android.widget.ImageView;
import android.widget.TextView;

public class GvItem {
    int imageView;
    String name;

    public GvItem(int imageView, String name) {
        this.imageView = imageView;
        this.name = name;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
