package com.example.admin.doancn.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.admin.doancn.Api.CheckInternet;
import com.example.admin.doancn.Api.Server;
import com.example.admin.doancn.ApplicationApp;
import com.example.admin.doancn.Model.Tepkh;
import com.example.admin.doancn.Model.UserLogin;
import com.example.admin.doancn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DangkyActivity extends AppCompatActivity {

    public static final String PREFERENCES_FILE_NAME = "login";
    ArrayList<UserLogin> mangkhachhang = new ArrayList<>();
    SharedPreferences preferences;
    //login
    LinearLayout ll_signin;
    Button btn_backLogin;
    EditText et_usernameLogin;
    EditText et_passLogin;
    CheckBox chk_saveLogin;
    Button btn_signup;
    Button btn_login;
    Button btn_forgetpass;

    //----------------
    //layout đăng ký
    LinearLayout ll_dk;
    Button btn_backSignup;
    EditText et_tkSignup;
    EditText et_email;
    EditText et_diachi;
    EditText et_passSignup;
    EditText et_pwtrue;
    EditText et_phone;
    Button btn_dk;
    //--------------------
    //layout reset

    LinearLayout ll_reset;
    Button btn_BackQuenpass;
    EditText edt_mailReset;
    EditText edt_passR;
    EditText edt_passNew;
    EditText edt_passReset;
    Button btn_Reset;
    //    boolean isLogin;

    //bien khoi tao
    String email = "";
    String matkhau = "";
    String tenkh = "";
    String diachi = "";
    String sdt = "";

    String Gmail = "";
    String Pass = "";
    ApplicationApp applicationApp;
    boolean isCheckLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isCheckLogin = preferences.getBoolean("isCheckLogin", false);

        init();

        if (CheckInternet.haveNetworkConnection(getApplicationContext())) {


        } else {
            CheckInternet.ShowToast(getApplicationContext(), "bạn vui lòng kết nối mạng");
        }
        clickControl(0);
    }

    private void init() {
        ll_signin = (LinearLayout) findViewById(R.id.ll_signin);

        btn_login = (Button) findViewById(R.id.btn_login);

        et_usernameLogin = (EditText) findViewById(R.id.et_usernameLogin);

        et_passLogin = (EditText) findViewById(R.id.et_passLogin);

        btn_login.setOnClickListener(clickListener);

    }

    private void clickControl(int mode) {//mode:0 page login, =1: page register, =2: page reset
        if (mode == 0) {
            ll_signin.setVisibility(View.VISIBLE);
        }
    }


    //login
    private void getdataUser() {
        //dọc dữ liệu
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //goi lại đường dẫn
        String duongdanlaptop = Server.duongdanlogin;
        //doc het tac ca du lieu
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdanlaptop, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Tepkh> thongtinkhList = new ArrayList<>();
                //tạo biến

                if (response != null) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            email = jsonObject.getString("dangnhap");
                            matkhau = jsonObject.getString("matkhau");

                            //thêm du lieu vao mang
//                            mangkhachhang.add(new UserLogin(email,matkhau));
                            if (et_usernameLogin.getText().toString().equals(email) && et_passLogin.getText().toString().equals(matkhau)) {
                                Log.i("LoginSucess", "Login Sucess!");
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {

                            }
                        }
                        Log.i("ListUser", "List User: " + mangkhachhang.size());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    CheckInternet.ShowToast(getApplicationContext(), "Fail REquest ");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("FailConnect", "Fail to connect");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:

                    getdataUser();

                    Log.i("LoginApp", "Login App: " + isCheckLogin);
                    break;

                            }

        }
    };

}
