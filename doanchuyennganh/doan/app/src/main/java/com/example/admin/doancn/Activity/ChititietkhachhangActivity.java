package com.example.admin.doancn.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.doancn.Model.KhachHangInfo;
import com.example.admin.doancn.Model.Tepkh;
import com.example.admin.doancn.R;

import java.text.DecimalFormat;

public class ChititietkhachhangActivity extends AppCompatActivity {
    Toolbar tb_main;

    TextView name_ct;
    TextView sdt_ct;
    TextView Dc_chitiet;
    TextView tensp_ct;
    TextView sl_ct;
    TextView gia_ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chititietkhachhang);
        init();
        toolbarback();
        getData();
    }

    private void getData() {
        KhachHangInfo khachHangInfo = (KhachHangInfo) getIntent().getSerializableExtra("thongtinsanpham");
        Log.i("Khachhang", "Khach hang: " + khachHangInfo.getTenkhachhang());
        name_ct.setText(khachHangInfo.getTenkhachhang());
        sdt_ct.setText(khachHangInfo.getSdt()+"");
        Dc_chitiet.setText(khachHangInfo.getDiachi()+"");
        gia_ct.setText(khachHangInfo.getThantien()+"");
        sl_ct.setText(khachHangInfo.getSoluong()+"");
        tensp_ct.setText(khachHangInfo.getTensanpham());

    }

    private void init() {
        tb_main = (Toolbar) findViewById(R.id.tb_main);
        //layout
        name_ct = (TextView) findViewById(R.id.name_ct);
        sdt_ct = (TextView) findViewById(R.id.sdt_ct);
        Dc_chitiet = (TextView) findViewById(R.id.Dc_chitiet);
        tensp_ct = (TextView) findViewById(R.id.tensp_ct);
        sl_ct = (TextView) findViewById(R.id.sl_ct);
        gia_ct = (TextView) findViewById(R.id.gia_ct);

    }

    private void toolbarback() {
        setSupportActionBar(tb_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //gắn sự kiện
        tb_main.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
