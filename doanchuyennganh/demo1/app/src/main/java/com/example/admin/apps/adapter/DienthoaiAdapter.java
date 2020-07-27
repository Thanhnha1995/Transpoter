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

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public DienthoaiAdapter(Context context, ArrayList<Sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydienthoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Viewholder{
        public TextView  tensp_dienthoai;
        public TextView Gia_dienthoai;
        public TextView Mota_dienthoai;
        public ImageView igm_dienthoai;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Viewholder viewholder =  null;
        if(view == null){
            viewholder = new Viewholder();
            LayoutInflater inflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_dienthoai,null);
            viewholder.tensp_dienthoai = (TextView) view.findViewById(R.id.tensp_dienthoai);
            viewholder.Gia_dienthoai = (TextView) view.findViewById(R.id.Gia_dienthoai);
            viewholder.Mota_dienthoai = (TextView) view.findViewById(R.id.Mota_dienthoai);
            viewholder.igm_dienthoai = (ImageView) view.findViewById(R.id.igm_dienthoai);
            //xét tag vào viewholder
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewholder.tensp_dienthoai.setText(sanpham.getTensanpham());
        //format lại giá
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.Gia_dienthoai.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đồng/Vnđ");
        //set số lượng dòng
        viewholder.Mota_dienthoai.setMaxLines(2);
        viewholder.Mota_dienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewholder.Mota_dienthoai.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(viewholder.igm_dienthoai);
        return view;
    }
}
