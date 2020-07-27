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
import android.widget.EditText;
import android.widget.ListView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.apps.R;
import com.example.admin.apps.adapter.LaptopAdapter;
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.ultil.Checkconnection;
import com.example.admin.apps.ultil.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    //kahi báo thuộc tính
    private Toolbar toolbarlaptop;
    private ListView lvlaptop;
    private LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> manglaptop;
    int idlaptop = 0;
    int page = 1;

    View footer;
    boolean isloading;
    boolean limitdata = false;
    myHandler myHandler;

    EditText hang;
    EditText gt;

    int id = 0;
    String Tenlaptop = "";
    int Gialaptop = 0;
    String Hinhanhlaptop = "";
    String Motalaptop = "";
    int idsanphamlaptop = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
            init();
            getidloailaptop();
            toolbarback();
            getdatalaptop(page);
            loadmorelaptop();

        } else {
            Checkconnection.ShowToast_Short(getApplicationContext(), "bạn hãy kiểm tra lại mạng");
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
    private void loadmorelaptop() {
        lvlaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChitietlaptopActivity.class);
                intent.putExtra("thongtinsanpham",manglaptop.get(position));
                startActivity(intent);
            }
        });
        lvlaptop.setOnScrollListener(new AbsListView.OnScrollListener() {
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
    private class ThreadData extends Thread{
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
    private void init() {
        //tìm kiếm hãng và giá
        hang = (EditText) findViewById(R.id.hangLpt);
        gt = ( EditText) findViewById(R.id.hangdt);
        toolbarlaptop = findViewById(R.id.tb_laptop);
        lvlaptop =(ListView) findViewById(R.id.ls_spltop);
        manglaptop = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(getApplicationContext(), manglaptop);
        lvlaptop.setAdapter(laptopAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer = inflater.inflate(R.layout.progressbar,null);
        myHandler = new myHandler();
    }

    public class myHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvlaptop.addFooterView(footer);
                    break;
                case 1:
                    getdatalaptop(++page);
                    isloading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    private void getidloailaptop() {
        idlaptop = getIntent().getIntExtra("idloaisanpham", -1);
//        Log.d("laptop", +idlaptop + "");
    }


    private void getdatalaptop(int Page) {
        //dọc dữ liệu
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //goi lại đường dẫn
        String duongdanlaptop = Server.Duongdanlaptop + String.valueOf(Page);
        //doc het tac ca du lieu
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdanlaptop, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //tạo biến

                if (response != null && response.length() != 2) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tenlaptop = jsonObject.getString("tensp");
                            Gialaptop = jsonObject.getInt("giasp");
                            Hinhanhlaptop = jsonObject.getString("hinhanhsp");
                            Motalaptop = jsonObject.getString("motasp");
                            idsanphamlaptop = jsonObject.getInt("idsanpham");
                            //thêm du lieu vao mang
                            manglaptop.add(new Sanpham(id, Tenlaptop, Gialaptop, Hinhanhlaptop, Motalaptop, idsanphamlaptop));
                            laptopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitdata = true;
                    lvlaptop.removeFooterView(footer);
                    Checkconnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu ");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(idlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void toolbarback() {
        setSupportActionBar(toolbarlaptop);
        //tạo nút home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //gắn sự kiện
        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
