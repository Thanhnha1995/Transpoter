package com.example.admin.apps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.apps.R;
import com.example.admin.apps.activity.GiohangActivity;
import com.example.admin.apps.activity.MainActivity;
import com.example.admin.apps.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView tengiohang;
        public TextView giagiohang;
        public ImageView imggiohang;
        public Button btthemgiohang;
        public Button btgiamgiohang;
        public ImageView imageView_trash;
        private Button giatrigiohang;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang, null);
            viewHolder.tengiohang = (TextView) convertView.findViewById(R.id.ten_ghang);
            viewHolder.giagiohang = (TextView) convertView.findViewById(R.id.gia_ghang);
            viewHolder.imggiohang = (ImageView) convertView.findViewById(R.id.hinh_giohang);
            viewHolder.btthemgiohang = (Button) convertView.findViewById(R.id.BT_tangghang);
            viewHolder.btgiamgiohang = (Button) convertView.findViewById(R.id.BT_giamslghang);
            viewHolder.giatrigiohang = (Button) convertView.findViewById(R.id.BT_giatrighang);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        Giohang giohang = (Giohang) getItem(position);
        Giohang giohang = arraygiohang.get(position);
        viewHolder.tengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.giagiohang.setText(decimalFormat.format(giohang.getGiasp()) + "VNĐ");
        Picasso.with(context).load(giohang.getHinhanhsp())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.giatrigiohang.setText(giohang.getSoluongsp() + "");
        //button +-
        int sl = Integer.parseInt(viewHolder.giatrigiohang.getText().toString());
//        if(sl>=10){
//            viewHolder.btthemgiohang.setVisibility(View.INVISIBLE);
//            viewHolder.btgiamgiohang.setVisibility(View.VISIBLE);
//        }else
        if (sl <= 1) {
            viewHolder.btgiamgiohang.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            viewHolder.btgiamgiohang.setVisibility(View.VISIBLE);
            viewHolder.btthemgiohang.setVisibility(View.VISIBLE);
        }
        //update giá số lượng

        final ViewHolder finalViewHolder = viewHolder;

        viewHolder.btthemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.giatrigiohang.getText().toString()) + 1;
                int slhientai = MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoi);
                //công thức tính
                long giamoi = (giaht * slmoi) / slhientai;
                MainActivity.manggiohang.get(position).setGiasp(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.giagiohang.setText(decimalFormat.format(giamoi) + "VNĐ");
                GiohangActivity.Eventgiohang();
                if (slmoi > 1) {
//                    finalViewHolder.btthemgiohang.setVisibility(View.INVISIBLE);
                    finalViewHolder.btgiamgiohang.setVisibility(View.VISIBLE);
                    finalViewHolder.giatrigiohang.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.btgiamgiohang.setVisibility(View.VISIBLE);
                    finalViewHolder.btthemgiohang.setVisibility(View.VISIBLE);
                    finalViewHolder.giatrigiohang.setText(String.valueOf(slmoi));
                }
            }
        });
        viewHolder.btgiamgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.giatrigiohang.getText().toString()) - 1;
                int slhientai = MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoi);
                //công thức tính
                long giamoi = (giaht * slmoi) / slhientai;

                MainActivity.manggiohang.get(position).setGiasp(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.giagiohang.setText(decimalFormat.format(giamoi) + "VNĐ");
                GiohangActivity.Eventgiohang();
                if (slmoi < 1) {
                    finalViewHolder.btgiamgiohang.setVisibility(View.INVISIBLE);
                    finalViewHolder.btthemgiohang.setVisibility(View.VISIBLE);
                    finalViewHolder.giatrigiohang.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.btgiamgiohang.setVisibility(View.VISIBLE);
                    finalViewHolder.btthemgiohang.setVisibility(View.VISIBLE);
                    finalViewHolder.giatrigiohang.setText(String.valueOf(slmoi));
                }
            }
        });
        return convertView;
    }
}
