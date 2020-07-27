package com.example.admin.apps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.apps.R;
import com.example.admin.apps.model.Giohang;
import com.example.admin.apps.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChitietlaptopActivity extends AppCompatActivity {
    Toolbar toolbar_ct;
    ImageView imageView_ct;
    TextView tentxt_ct;
    TextView giatxt_ct;
    TextView motatxt_ct;
    Spinner spinnersl_ct;
    Button btdatmua_ct;
    int id =0;
    String tenchitietsp = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String motasanpham = "";
    int idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietlaptop);
        init();
        toolbarback();
        Getinformation();
        EventSpinner();
        EventBT_buy();

    }



    private void EventBT_buy() {
        btdatmua_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0){
                        int sl = Integer.parseInt((spinnersl_ct.getSelectedItem().toString()));
                        boolean exit=false;
                        for (int i = 0;i<MainActivity.manggiohang.size();i++){
                            if(MainActivity.manggiohang.get(i).getIdsp() == id){
                                MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() +sl);
                                if(MainActivity.manggiohang.get(i).getSoluongsp() >=10){
                                    MainActivity.manggiohang.get(i).setSoluongsp(10);
                                }
                                MainActivity.manggiohang.get(i).setGiasp(giachitiet*MainActivity.manggiohang.get(i).getSoluongsp());
                                exit = true;
                            }
                        }
                        if(exit == false){
                            int soluongsp = Integer.parseInt((spinnersl_ct.getSelectedItem().toString()));
                            long Giamoi = soluongsp * giachitiet;
                            MainActivity.manggiohang.add(new Giohang(id,tenchitietsp,Giamoi,hinhanhchitiet,soluongsp));

                        }
                }else {
                    int soluongsp = Integer.parseInt((spinnersl_ct.getSelectedItem().toString()));
                    long Giamoi = soluongsp * giachitiet;
                    MainActivity.manggiohang.add(new Giohang(id,tenchitietsp,Giamoi,hinhanhchitiet,soluongsp));
                }
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void EventSpinner() {
        Integer[] soluongsanpham = new  Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluongsanpham);
        spinnersl_ct.setAdapter(arrayAdapter);
    }

    private void Getinformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        tenchitietsp  = sanpham.getTensanpham();
        giachitiet = sanpham.getGiasanpham();
        hinhanhchitiet = sanpham.getHinhanhsanpham();
        motasanpham = sanpham.getMotasanpham();
        idsanpham = sanpham.getIDSanpham();
        tentxt_ct.setText(tenchitietsp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giatxt_ct.setText("Giá : "+decimalFormat.format(giachitiet)+"VNĐ");
        motatxt_ct.setText(motasanpham);
        Picasso.with(getApplicationContext()).load(hinhanhchitiet)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.error)
                .into(imageView_ct);
    }

    private void toolbarback() {
        setSupportActionBar(toolbar_ct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_ct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        toolbar_ct = (Toolbar) findViewById(R.id.ctsp_toolbar);
        imageView_ct = (ImageView) findViewById(R.id.img_ctsp);
        tentxt_ct =(TextView) findViewById(R.id.ctsp_ten);
        giatxt_ct =(TextView) findViewById(R.id.ctsp_gia);
        motatxt_ct =(TextView) findViewById(R.id.ctsp_scrollview_textview);
        spinnersl_ct =(Spinner) findViewById(R.id.ctsp_soluong);
        btdatmua_ct =(Button) findViewById(R.id.ctsp_themgiohang);

    }
}
