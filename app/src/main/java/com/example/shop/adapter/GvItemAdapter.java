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
import com.example.shop.enrty.GvItem;
import com.youth.banner.loader.ImageLoader;

public class GvItemAdapter extends BaseAdapter{

    private List<GvItem> objects ;

    private Context context;
    private LayoutInflater layoutInflater;

    public GvItemAdapter(Context context,List<GvItem> objects) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public GvItem getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.gv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((GvItem)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(GvItem object, ViewHolder holder) {
        holder.gvName.setText(object.getName());
        holder.gvImg.setImageResource(object.getImageView());
    }

    protected class ViewHolder {
        private ImageView gvImg;
    private TextView gvName;


        public ViewHolder(View view) {
            gvImg = (ImageView) view.findViewById(R.id.gv_img);
            gvName = (TextView) view.findViewById(R.id.gv_name);

        }
    }
}
