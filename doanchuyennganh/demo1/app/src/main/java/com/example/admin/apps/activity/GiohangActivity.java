package com.example.admin.apps.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.apps.ApplicationApp;
import com.example.admin.apps.R;
import com.example.admin.apps.adapter.GiohangAdapter;
import com.example.admin.apps.model.Giohang;
import com.example.admin.apps.ultil.Checkconnection;
import com.example.admin.apps.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class GiohangActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btn_thanhtoan;
    Button btn_tieptuc;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    ImageView imageView_trash;
    //    boolean isCheckLogin;
    ApplicationApp applicationApp;
    private boolean isCheckLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        applicationApp = (ApplicationApp) this.getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isCheckLogin = sharedPreferences.getBoolean("isCheckLogin", false);
        init();
        toolbarback();
        Getdata();
        Eventgiohang();
        EventBt_muahang();
        Delete_sp();
    }

    private void Delete_sp() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("bạn đang xóa món hàng");
                builder.setMessage("bạn chắc chắn muốn xóa món hàng ");
                builder.setPositiveButton("Có/Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.manggiohang.size() <= 0) {
                            txtthongbao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            Eventgiohang();
                            if (MainActivity.manggiohang.size() <= 0) {
                                txtthongbao.setVisibility(View.VISIBLE);
                            } else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                Eventgiohang();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không/No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        Eventgiohang();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void EventBt_muahang() {
        btn_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //isCheckLogin = false chua login, =true da login

                if (MainActivity.manggiohang.size() > 0) {
                    if (isCheckLogin) {
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdanpostdonhang, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String madonhang) {
                                if (madonhang.equals("1")) {
                                    Log.i("RequestSuccess", "Request Success");
                                    MainActivity.manggiohang.clear();
                                    Toast.makeText(getApplicationContext(), "Bạn đã mua hàng thành công",Toast.LENGTH_SHORT).show();
                                    tongtien = 0;
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.i("RequestSuccess", "Request Fail");
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                JSONArray jsonArray = new JSONArray();
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("tenkhachhang", sharedPreferences.getString("tenkhachhang", "AAA"));
                                    jsonObject.put("sdt", sharedPreferences.getString("diachi", "BBB"));
                                    jsonObject.put("diachi", sharedPreferences.getString("sdt", "0123"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                                    try {
//                                        jsonObject.put("tenkhachhang", "qqqqq");
//                                        jsonObject.put("sdt", "1234567890");
//                                        jsonObject.put("diachi", "123 hhhh");
                                        jsonObject.put("tensanpham", MainActivity.manggiohang.get(i).tensp);
                                        jsonObject.put("soluong", MainActivity.manggiohang.get(i).soluongsp);
                                        jsonObject.put("thantien", tongtien);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArray.put(jsonObject);
                                }

                                HashMap<String, String> hashMap = new HashMap<>();
//                                hashMap.put("tenkhachhang", "sdfsdfsdf");
//                                hashMap.put("sdt", "1234567890");
//                                hashMap.put("diachi", "123 hhhh");
                                hashMap.put("json", jsonArray.toString());

                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    } else {
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("isCkeckBuy", true);// kiem tra khach hang bam vao nut mua khi chua login
//                        editor.commit();

                        Checkconnection.ShowToast_Short(getApplicationContext(), "vui log dang nhap de mua hang");
                        Intent intent = new Intent(getApplicationContext(), DangkyActivity.class);
                        startActivity(intent);
                        finish();

                    }
                } else {
                    Checkconnection.ShowToast_Short(getApplicationContext(), "vui lòng kiểm tra lại");
                }
            }
        });
    }

    private static long tongtien = 0;

    public static void Eventgiohang() {

        for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + "Đồng");
    }

    private void Getdata() {
        if (MainActivity.manggiohang.size() <= 0) {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        } else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void toolbarback() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        lvgiohang = (ListView) findViewById(R.id.lsv_giohang);
        txtthongbao = (TextView) findViewById(R.id.txt_thongbaogh);
        txttongtien = (TextView) findViewById(R.id.GT_giohang);
        btn_thanhtoan = (Button) findViewById(R.id.bt_giohang);
        btn_tieptuc = (Button) findViewById(R.id.bt_muahang);
        toolbargiohang = findViewById(R.id.tb_giohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);

    }
}
