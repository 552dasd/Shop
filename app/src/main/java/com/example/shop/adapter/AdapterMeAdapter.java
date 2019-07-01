package com.example.shop.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.enrty.Me;

public class AdapterMeAdapter extends BaseAdapter {

    private List<Me> objects = new ArrayList<Me>();

    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterMeAdapter(Context context,List<Me> objects) {
        this.context = context;
        this.objects = objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Me getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_me, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Me)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Me object, ViewHolder holder) {
       holder.tvMe.setText(object.getTitle());
       holder.ivMe.setImageResource(object.getInteger());
    }

    protected class ViewHolder {
        private ImageView ivMe;
    private TextView tvMe;

        public ViewHolder(View view) {
            ivMe = (ImageView) view.findViewById(R.id.iv_me);
            tvMe = (TextView) view.findViewById(R.id.tv_me);
        }
    }
}
