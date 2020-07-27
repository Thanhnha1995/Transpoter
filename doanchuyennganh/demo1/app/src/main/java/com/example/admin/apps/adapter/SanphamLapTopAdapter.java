package com.example.admin.apps.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.apps.R;
import com.example.admin.apps.activity.ChitietsanphamActivity;
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.ultil.Checkconnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamLapTopAdapter extends RecyclerView.Adapter<SanphamLapTopAdapter.ItemHolder>
{
    Context context;
    //truyền vào mảng sản phẩm
    ArrayList<Sanpham> arraySanpham;

    public SanphamLapTopAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
      //gán thuộc tính
       ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }
//hỗ trợ set và get layout
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int i) {
        //gọi khuông
        Sanpham sanpham = arraySanpham.get(i);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"");
        Picasso.with(context)
                .load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        //return tấc cả giá trị trong mảng
        return arraySanpham.size();
    }

    public  class  ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageview;
        public TextView txttensanpham;
        public  TextView txtgiasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview_sanpham);
            txtgiasanpham = (TextView) itemView.findViewById(R.id.textview_giasanpham);
            txttensanpham = (TextView) itemView.findViewById(R.id.textview_tensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context,ChitietsanphamActivity.class);
                    //chuuyển dữ liệu
                    intent.putExtra("thongtinsanpham",arraySanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Checkconnection.ShowToast_Short(context,arraySanpham.get(getPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });
        }
    }
}
