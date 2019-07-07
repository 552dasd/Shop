package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public class Imag extends SwipeBackActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imag);
        imageView = findViewById(R.id.imageView2);
        ImageLoader.getInstance().displayImage("http://nanhai655.cn/code.jpg", imageView, MyApplication.getLoaderOptions());

    }
}
