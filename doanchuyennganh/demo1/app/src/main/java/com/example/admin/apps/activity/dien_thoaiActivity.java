package com.example.admin.apps.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.apps.R;
import com.example.admin.apps.adapter.DienthoaiAdapter;
import com.example.admin.apps.model.Giohang;
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.ultil.Checkconnection;
import com.example.admin.apps.ultil.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dien_thoaiActivity extends AppCompatActivity {
    //kahi báo thuộc tính
    private Toolbar toolbardt;
    private ListView lvdt;
    private DienthoaiAdapter dienthoaiAdapter;
    ArrayList<Sanpham> mangdt;
    int iddt = 0;
    int page  = 1;
    View footer;
    boolean isloading;
    boolean limitdata = false;
    myHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthoai);
        init();

        if(Checkconnection.haveNetworkConnection(getApplicationContext())){
            init();
            getidloaisp();
            toolbarback();
            getdatadt(page);
            Loadmoredatadt();

        }else {
            Checkconnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiêm tra kết nối internet");
            finish();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
            case R.id.menusearch:
                Intent in = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Loadmoredatadt() {
        lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChitietsanphamActivity.class);
                intent.putExtra("thongtinsanpham",mangdt.get(position));
                startActivity(intent);
            }
        });
        lvdt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem == totalItemCount && totalItemCount != 1 && isloading == false && limitdata== false){
                    isloading = true;
                    ThreadData thread= new ThreadData();
                    thread.start();
                }
            }
        });
    }

    private void getdatadt(int Page) {
        //dọc dữ liệu
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());
        //goi lại đường dẫn
        String duongdan = Server.Duongdandienthoai+String.valueOf(Page);
        //doc het tac ca du lieu
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //tạo biến
                int id = 0;
                String Tendt = "";
                int Giadt = 0;
                String Hinhanhdt = "";
                String Motadt = "";
                int idsanphamdt = 0;
                if(response !=null && response.length() !=2 ){
                    lvdt.removeFooterView(footer);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=  jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tendt =jsonObject.getString("tensp");
                            Giadt = jsonObject.getInt("giasp");
                            Hinhanhdt =jsonObject.getString("hinhanhsp");
                            Motadt =jsonObject.getString("motasp");
                            idsanphamdt = jsonObject.getInt("idsanpham");
                            //thêm du lieu vao mang
                            mangdt.add(new Sanpham(id,Tendt,Giadt,Hinhanhdt,Motadt,idsanphamdt));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitdata = true;
                    lvdt.removeFooterView(footer);
                    Checkconnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void toolbarback() {
        setSupportActionBar(toolbardt);
        //tạo nút home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //gắn sự kiện
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getidloaisp() {
        iddt =  getIntent().getIntExtra("idloaisanpham",-1);
//        Log.d("gia tri loai san pham",iddt+"");
    }

    private void init() {
        toolbardt = (Toolbar) findViewById(R.id.tb_dienthoai);
        lvdt = (ListView) findViewById(R.id.ls_spdt);
        //cap phat bo nho
        mangdt = new ArrayList<>();
        dienthoaiAdapter = new DienthoaiAdapter(getApplicationContext(),mangdt);
        lvdt.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer = inflater.inflate(R.layout.progressbar,null);
        myHandler = new myHandler();
    }
    public  class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvdt.addFooterView(footer);
                    break;
                case 1:
                    getdatadt(++page);
                 isloading=false;
                 break;
            }
            super.handleMessage(msg);
        }
    }
    public  class ThreadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }
}
