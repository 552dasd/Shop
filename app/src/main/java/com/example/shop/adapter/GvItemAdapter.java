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

public class GvItemAdapter extends BaseAdapter{

    private List<Object> objects = new ArrayList<Object>();

    private Context context;
    private LayoutInflater layoutInflater;

    public GvItemAdapter(Context context,List<Object> objects) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
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
        initializeViews((Object)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Object object, ViewHolder holder) {
        //TODO implement
        holder.gvPrice.setText("1111");
        holder.gvName.setText("11121");
    }

    protected class ViewHolder {
        private ImageView gvImg;
    private TextView gvName;
    private TextView gvPrice;

        public ViewHolder(View view) {
            gvImg = (ImageView) view.findViewById(R.id.gv_img);
            gvName = (TextView) view.findViewById(R.id.gv_name);
            gvPrice = (TextView) view.findViewById(R.id.gv_price);
        }
    }
}
