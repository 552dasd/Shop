package com.example.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.MyApplication;
import com.example.shop.R;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.GvItem;
import com.example.shop.enrty.GvProduct;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class GvProductAdapter extends BaseAdapter {
    private List<Commodity.CommodityBean> objects ;

    private Context context;
    private LayoutInflater layoutInflater;

    public GvProductAdapter(Context context, List<Commodity.CommodityBean> objects) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Commodity.CommodityBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.gv_product, null);
            convertView.setTag(new GvProductAdapter.ViewHolder(convertView));
        }
        initializeViews((Commodity.CommodityBean)getItem(position), (GvProductAdapter.ViewHolder) convertView.getTag());
        return convertView;
    }


    private void initializeViews(Commodity.CommodityBean object, GvProductAdapter.ViewHolder holder) {
        ImageLoader.getInstance().displayImage(object.getImage(),holder.gvImgPro);
        holder.gvImgProName.setText(object.getTitle());
        holder.gvProPre.setText(object.getSellPoint());
        holder.gvProNewPrice.setText("¥"+(object.getPrice()-11111));
        holder.gvProOldPrice.setText("¥"+object.getPrice());
        holder.gvProOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    protected class ViewHolder {
        private ImageView gvImgPro;
        private TextView gvImgProName;
        private TextView gvProPre;
        private TextView gvProNewPrice;
        private TextView gvProOldPrice;
        private Button btnGo;


        public ViewHolder(View view) {
            gvImgPro = (ImageView) view.findViewById(R.id.gv_img_pro);
            gvImgProName = (TextView) view.findViewById(R.id.gv_pro_name);
            gvProPre = view.findViewById(R.id.gv_pro_pre);
            gvProNewPrice = view.findViewById(R.id.gv_pro_new_price);
            gvProOldPrice = view.findViewById(R.id.gv_pro_old_price);
            btnGo = view.findViewById(R.id.btn_go);

        }
    }
}
