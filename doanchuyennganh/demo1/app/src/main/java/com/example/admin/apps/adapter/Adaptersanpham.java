package com.example.admin.apps.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.admin.apps.R;
import com.example.admin.apps.activity.ChitietsanphamActivity;
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.ultil.Checkconnection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adaptersanpham extends RecyclerView.Adapter<Adaptersanpham.ViewHolder> implements Filterable {
    Context mContext;
    List<Sanpham> lisanpham;
    List<Sanpham> listSanPhamFilter;

    //    ViewSanPham viewSanPham;
    public Adaptersanpham(Context cont, List<Sanpham> lisp) {//get data
        this.mContext = cont;
        this.lisanpham = lisp;
        this.listSanPhamFilter = lisp;
//        this.viewSanPham = viewSanPham;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//khoi tao layout xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_dienthoai, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {//set data
        Sanpham san_pham = listSanPhamFilter.get(position);
//        byte[] foodImage = san_pham.getHinhanhsanpham();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage,0,foodImage.length);
//        holder.img_sp.setImageBitmap(bitmap);
        holder.tensp.setText(san_pham.getTensanpham());
//        holder.tv_des.setText(san_pham.getMotasanpham());
        holder.tv_gia.setText(san_pham.getGiasanpham() + "");
        Picasso.with(mContext)
                .load(san_pham.getHinhanhsanpham())
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(holder.imageview);

        holder.ll_itemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SanPham", "current item: " + position);
                Intent intent= new Intent(mContext,ChitietsanphamActivity.class);
                intent.putExtra("thongtinsanpham",listSanPhamFilter.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Checkconnection.ShowToast_Short(mContext,listSanPhamFilter.get(position).getTensanpham());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSanPhamFilter.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {// find id control
        private LinearLayout ll_itemSP;
        private ImageView imageview;
        private TextView tensp;
        private TextView tv_des;
        private TextView tv_gia;

        private TextView tv_id;

        public ViewHolder(View context) {
            super(context);
//            imageview = (ImageView) itemView.findViewById(R.id.imageview_sanpham);
            ll_itemSP = (LinearLayout) context.findViewById(R.id.ll_itemSP);
            imageview = (ImageView) context.findViewById(R.id.igm_dienthoai);
            tensp = (TextView) context.findViewById(R.id.tensp_dienthoai);
            tv_des = (TextView) context.findViewById(R.id.Gia_dienthoai);
            tv_gia = (TextView) context.findViewById(R.id.Mota_dienthoai);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            //CharSequence chuỗi
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    listSanPhamFilter = lisanpham;
                } else {
                    //tạo mới mảng San_pham
                    List<Sanpham> listsp = new ArrayList<>();
                    //vòng lặp
                    //cho cột San_pham thành lisanpham
                    for (Sanpham row : lisanpham) {
                        //nếu cột tên sp là chuỗi
                        if (row.getTensanpham().contains(constraint)) {
                            //add thêm cot
                            listsp.add(row);
                        }
                    }
                    //lấy từ lisanpham bằng mảng  listsp
                    listSanPhamFilter = listsp;
                }
                //tạo mới 1 giá trị đếm được
                FilterResults filterResults = new FilterResults();
                //giá trị bằng lisanpham
                filterResults.values = listSanPhamFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //mảng lisanpham = mảng San_pham
                listSanPhamFilter = (ArrayList<Sanpham>) results.values;
                //thay đổi dữ liệu
                notifyDataSetChanged();
            }
        };
    }

}
