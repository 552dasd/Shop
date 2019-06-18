package com.example.shop.com.example.zds;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SearchView;

import com.example.shop.R;

public class MySearchView extends SearchView {
    SearchView searchView;
    public MySearchView(Context context) {
        super(context);
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void change(){
        searchView = findViewById(R.id.search_bar);
        searchView.setIconifiedByDefault(false);
    }
}
