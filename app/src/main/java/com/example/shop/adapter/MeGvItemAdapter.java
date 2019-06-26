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
import com.example.shop.enrty.MeGvItem;

public class MeGvItemAdapter extends BaseAdapter {

    private List<MeGvItem> objects = new ArrayList<MeGvItem>();

    private Context context;
    private LayoutInflater layoutInflater;

    public MeGvItemAdapter(Context context,List<MeGvItem> objects) {
        this.context = context;
        this.objects = objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MeGvItem getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.me_gv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MeGvItem)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MeGvItem object, ViewHolder holder) {
        holder.meImg.setImageResource(object.getMeImg());
        holder.meImgName.setText(object.getMeImgName());
    }

    protected class ViewHolder {
        private ImageView meImg;
    private TextView meImgName;

        public ViewHolder(View view) {
            meImg = (ImageView) view.findViewById(R.id.me_img);
            meImgName = (TextView) view.findViewById(R.id.me_img_name);
        }
    }
}
