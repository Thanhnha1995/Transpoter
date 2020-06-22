package com.example.admin.apps.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.apps.R;
import com.example.admin.apps.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter{
    Context context;
    ArrayList<Sanpham> arraylaptop;

    public LaptopAdapter(Context context, ArrayList<Sanpham> arraylaptop) {
        this.context = context;
        this.arraylaptop = arraylaptop;
    }

    @Override
    public int getCount() {
        return arraylaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Viewholder{
        public TextView tensp_laptop;
        public TextView Gia_laptop;
        public TextView Mota_laptop;
        public ImageView igm_laptop;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LaptopAdapter.Viewholder viewholder =  null;
        if(view == null){
            viewholder = new Viewholder();
            LayoutInflater inflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_laptop,null);
            viewholder.tensp_laptop = (TextView) view.findViewById(R.id.tensp_laptop);
            viewholder.Gia_laptop = (TextView) view.findViewById(R.id.Gia_laptop);
            viewholder.Mota_laptop = (TextView) view.findViewById(R.id.Mota_laptop);
            viewholder.igm_laptop = (ImageView) view.findViewById(R.id.igm_laptop);
            //xét tag vào viewholder
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewholder.tensp_laptop.setText(sanpham.getTensanpham());
        //format lại giá
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.Gia_laptop.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+" Đồng/Vnđ");
        //set số lượng dòng
        viewholder.Mota_laptop.setMaxLines(2);
        viewholder.Mota_laptop.setEllipsize(TextUtils.TruncateAt.END);
        viewholder.Mota_laptop.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(viewholder.igm_laptop);
        return view;
    }
}
