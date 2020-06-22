package com.example.admin.doancn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.doancn.Activity.ChititietkhachhangActivity;
import com.example.admin.doancn.Activity.MainActivity;
import com.example.admin.doancn.Model.KhachHangInfo;
import com.example.admin.doancn.Model.Tepkh;
import com.example.admin.doancn.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Tepkh_Adapter extends RecyclerView.Adapter<Tepkh_Adapter.ItemHolder> {
    Context context;
    List<KhachHangInfo> listKH = new ArrayList<>();

    public Tepkh_Adapter(Context context, List<KhachHangInfo> array_kh) {
        this.context = context;
        this.listKH = array_kh;
    }

    @NonNull

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_kh_new, null);
        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder itemHolder, final int position) {
        final KhachHangInfo tepkh = listKH.get(position);
        itemHolder.tenkh.setText(tepkh.getTenkhachhang());
    }



    @Override
    public int getItemCount() {
        return listKH.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView tenkh;
        private LinearLayout ll_itemkh;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            init();
            ll_itemkh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //truyền màng hình
                    Intent intent = new Intent(context, ChititietkhachhangActivity.class);
                    //chuuyển dữ liệu
                    intent.putExtra("thongtinsanpham", listKH.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }

        private void init() {
            tenkh = (TextView) itemView.findViewById(R.id.ten_kh);
            ll_itemkh = (LinearLayout) itemView.findViewById(R.id.ll_itemkh);
        }
    }
}
