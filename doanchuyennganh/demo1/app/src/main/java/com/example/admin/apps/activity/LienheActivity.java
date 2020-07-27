package com.example.admin.apps.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.apps.R;
import com.example.admin.apps.model.Donhang;
import com.example.admin.apps.ultil.Checkconnection;
import com.example.admin.apps.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LienheActivity extends AppCompatActivity {
    private Toolbar tb_back_lh;
    ArrayList<Donhang> mangkh;

    TextView name;
    TextView phone_tk;
    TextView dress;
    String tenkh = "";
    String diachi = "";
    String sdt = "";
    Button btn_log;
    Button btn_lg;
    SharedPreferences preferences;
    boolean isCheckLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienhe);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isCheckLogin = preferences.getBoolean("isCheckLogin", false);
        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
            init();
            toolbarback();

            getDatakh();
            login();
//            calling();
        } else {
            Checkconnection.ShowToast_Short(getApplicationContext(), "bạn hãy kiểm tra lại mạng");
        }
        if(isCheckLogin==true){

            //da dn roi hien nut dang xuat
            btn_lg.setVisibility(View.GONE);
            btn_log.setVisibility(View.VISIBLE);
        } else {
            // chua dn hien nut dn
            btn_lg.setVisibility(View.VISIBLE);
            btn_log.setVisibility(View.GONE);
        }

        toolbarback();
    }


    private void login() {

        btn_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),DangkyActivity.class);
                startActivity(a);
                finish();
            }
        });
    }

    private void logout() {

        SharedPreferences.Editor editor = preferences.edit();
//        editor.putBoolean("isLogin", false);
        editor.clear();
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getDatakh() {
        if (preferences.getString("tenkhachhang", "").length() > 0 || preferences.getString("diachi", "").length() > 0
                || preferences.getString("sdt", "").length() > 0) {
            name.setText(preferences.getString("tenkhachhang", ""));
            phone_tk.setText(preferences.getString("sdt", ""));
            dress.setText(preferences.getString("diachi", ""));

        } else {

        }
//        RequestQueue requestQueue;
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanAccountDonHang, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response != null) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i)
//                            tenkh = jsonObject.getString("tenkhachhang");
//                            diachi = jsonObject.getString("diachi");
//                            sdt = jsonObject.getString("sdt");
//                            //thêm du lieu vao mang
////                            mangkhachhang.add(new UserLogin(email,matkhau));
//                            else {
//
//                            }
//                        }
//                        Log.i("ListUser", "List User: " + mangkhachhang.size());
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Checkconnection.ShowToast_Short(getApplicationContext(), "Fail REquest ");
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
    }

    private void init() {
        tb_back_lh = (Toolbar) findViewById(R.id.tb_back_lh);
        name = (TextView) findViewById(R.id.name_tk);
        phone_tk = (TextView) findViewById(R.id.phone_tk);
        dress = (TextView) findViewById(R.id.ddress);
        btn_log = (Button) findViewById(R.id.btn_logout);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        btn_lg = (Button) findViewById(R.id.btn_login);
    }

    private void toolbarback() {
        setSupportActionBar(tb_back_lh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //gắn sự kiện
        tb_back_lh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
