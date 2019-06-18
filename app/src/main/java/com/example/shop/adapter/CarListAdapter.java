package com.example.shop.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import com.example.shop.R;
import com.example.shop.enrty.Item;

public class CarListAdapter extends BaseAdapter {

    private List<Item.BodeBean> objects = new ArrayList<Item.BodeBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CarListAdapter(Context context, List<Item.BodeBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Item.BodeBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_car_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Item.BodeBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Item.BodeBean object, ViewHolder holder) {
        //TODO implement

    }

    protected class ViewHolder {
        private CheckBox checkBox;
    private ImageView proImageView;
    private TextView tvName;
    private TextView tvSpec;
    private TextView tvPrice;
    private ImageView reduce;
    private EditText etCount;
    private ImageView puls;

        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            proImageView = (ImageView) view.findViewById(R.id.pro_imageView);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvSpec = (TextView) view.findViewById(R.id.tv_spec);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            reduce = (ImageView) view.findViewById(R.id.reduce);
            etCount = (EditText) view.findViewById(R.id.et_count);
            puls = (ImageView) view.findViewById(R.id.puls);
        }
    }
}
