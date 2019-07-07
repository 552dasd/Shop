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
import com.example.shop.enrty.UserShopping;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CommitAdapter extends BaseAdapter {

    private List<UserShopping.CommodityBean> objects = new ArrayList<UserShopping.CommodityBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CommitAdapter(Context context,List<UserShopping.CommodityBean> objects) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public UserShopping.CommodityBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.commit, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((UserShopping.CommodityBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(UserShopping.CommodityBean object, ViewHolder holder) {
        holder.proName.setText(object.getName());
        holder.proTitle.setText(object.getName());
        holder.proPrice.setText(object.getMoney()+"");
        ImageLoader.getInstance().displayImage(object.getImg(),holder.ivProduct);
    }

    protected class ViewHolder {
        private ImageView ivProduct;
    private TextView proName;
    private TextView proTitle;
    private TextView proPrice;

        public ViewHolder(View view) {
            ivProduct = (ImageView) view.findViewById(R.id.iv_product);
            proName = (TextView) view.findViewById(R.id.pro_name);
            proTitle = (TextView) view.findViewById(R.id.pro_title);
            proPrice = (TextView) view.findViewById(R.id.pro_price);
        }
    }
}
