package com.example.admin.doancn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;
import com.example.admin.doancn.Adapter.Tepkh_Adapter;
import com.example.admin.doancn.Api.CheckInternet;
import com.example.admin.doancn.Api.Server;
import com.example.admin.doancn.Model.KhachHangInfo;
import com.example.admin.doancn.Model.Tepkh;
import com.example.admin.doancn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Toolbar tb_bar;
    RecyclerView rc_recymain;
    NavigationView navi_main;
    ListView list;
    DrawerLayout dw;
    //    ArrayList<Loaisp> mangloaisp;
//    Loaisp_Adapter loaispAdapter;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";
    ArrayList<Tepkh> mangkh;
    Tepkh_Adapter tepkh_adapter;
//bien toan cuc getdata

    View footer;
    boolean isloading;
    boolean limitdata = false;
    myHandler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (CheckInternet.haveNetworkConnection(getApplicationContext())) {
            Actionbar();
//            getDatameu();
            getDatakh();
            Loadmore();
            Listmenu();
        } else {
            CheckInternet.ShowToast(getApplicationContext(), "Internet Fail");
            finish();
        }

    }

    private void Loadmore() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChititietkhachhangActivity.class);
                intent.putExtra("thongtinkhachhang", mangkh.get(position));
                startActivity(intent);
            }
        });
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstItem, int visibleItem, int totalItem) {
                if (firstItem + visibleItem == totalItem && totalItem != 0 && isloading == false && limitdata == false) {
                    isloading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private class ThreadData extends Thread {
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                ThreadData.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }

    public class myHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    list.addFooterView(footer);
                    break;
                case 1:
                    getDatakh();
                    isloading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void Listmenu() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (CheckInternet.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckInternet.ShowToast(getApplicationContext(), "wifi disconnet");
                        }
                        dw.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckInternet.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                            startActivity(intent);

                        } else {
                            CheckInternet.ShowToast(getApplicationContext(), "wifi disconnet");
                        }
                        dw.closeDrawer(GravityCompat.START);
                        break;

                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menusearch:
                Intent in = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    List<KhachHangInfo> listKH = new ArrayList<>();

    private void getDatakh() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaitepkh, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int ID = jsonObject.getInt("id");
                            String tenKH = jsonObject.getString("tenkhachhang");
                            int sdt = jsonObject.getInt("sdt");
                            String diachi = jsonObject.getString("diachi");
                            String tensp = jsonObject.getString("tensanpham");
                            int soluong = jsonObject.getInt("soluong");
                            int thanhtien = jsonObject.getInt("thantien");
                            listKH.add(new KhachHangInfo(id, tenKH, sdt, diachi, tensp, soluong, thanhtien));
                            tepkh_adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Actionbar() {
        setSupportActionBar(tb_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb_bar.setNavigationIcon(R.drawable.menu_ic);
        tb_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dw.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        tb_bar = (Toolbar) findViewById(R.id.tb_main);
        rc_recymain = (RecyclerView) findViewById(R.id.rc_main);
        navi_main = (NavigationView) findViewById(R.id.nv_main);
        list = (ListView) findViewById(R.id.lv_main);
        dw = (DrawerLayout) findViewById(R.id.dw_main);
        mangkh = new ArrayList<>();
        tepkh_adapter = new Tepkh_Adapter(getApplicationContext(), listKH);
        rc_recymain.setHasFixedSize(true);
        rc_recymain.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rc_recymain.setAdapter(tepkh_adapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer = inflater.inflate(R.layout.progressbar, null);
        myHandler = new myHandler();
    }

}
