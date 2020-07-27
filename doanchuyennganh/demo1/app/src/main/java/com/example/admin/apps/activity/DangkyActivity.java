package com.example.admin.apps.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.model.Thongtinkh;
import com.example.admin.apps.model.UserLogin;
import com.example.admin.apps.ultil.Checkconnection;
import com.example.admin.apps.ultil.Server;

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
        applicationApp = (ApplicationApp) this.getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isCheckLogin = preferences.getBoolean("isCheckLogin", false);

        init();
//        btn_backSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
            btn_dk();

        } else {
            Checkconnection.ShowToast_Short(getApplicationContext(), "bạn vui lòng kết nối mạng");
        }
        clickControl(0);
    }

    //đăng ký
    private void btn_dk() {
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = et_tkSignup.getText().toString().trim();
                final String sdt = et_phone.getText().toString().trim();
                final String email = et_email.getText().toString().trim();
                final String diachi = et_diachi.getText().toString().trim();
                final String paswword = et_passSignup.getText().toString().trim();
                final String password_true = et_pwtrue.getText().toString().trim();

                if (ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0 && paswword.length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    if (paswword.equals(password_true)) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdanthongtinkh, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String madonhang) {
                                Log.d("iddonhang", madonhang);
                                if (Integer.parseInt(madonhang) > 0) {
                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest request = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
//                                        if(response.equals("")){
                                            MainActivity.manggiohang.clear();
                                            Checkconnection.ShowToast_Short(getApplicationContext(), "bạn đã them du lieu gio hàng");
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            Checkconnection.ShowToast_Short(getApplicationContext(), "mời bạn tiếp tục mua hàng");
//                                        }else {
////                                            Checkconnection.ShowToast_Short(getApplicationContext(),"giỏ hang ban mua đã lỗi vui lòng mua lại");
////
////                                        }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
//                                            for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
//                                                JSONObject jsonObject = new JSONObject();
//                                                try {
//                                                    jsonObject.put("madonhang", madonhang);
//                                                    jsonObject.put("masanpham", MainActivity.manggiohang.get(i).getIdsp());
//                                                    jsonObject.put("tensanpham", MainActivity.manggiohang.get(i).getTensp());
//                                                    jsonObject.put("giasanpham", MainActivity.manggiohang.get(i).getGiasp());
//                                                    jsonObject.put("soluongsanpham", MainActivity.manggiohang.get(i).getSoluongsp());
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                                jsonArray.put(jsonObject);
//                                            }
                                            HashMap<String, String> hashMap = new HashMap<String, String>();
                                            hashMap.put("json", jsonArray.toString());
                                            return hashMap;
                                        }
                                    };
                                    queue.add(request);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("tenkhachhang", ten);
                                hashMap.put("email", email);
                                hashMap.put("diachi", diachi);
                                hashMap.put("matkhau", paswword);
                                hashMap.put("sdt", sdt);
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    } else {
                        Checkconnection.ShowToast_Short(getApplicationContext(), "vui lòng kiểm tra lại");
                    }
                }
            }
        });
    }

    private void init() {
        ll_signin = (LinearLayout) findViewById(R.id.ll_signin);
        btn_backLogin = (Button) findViewById(R.id.btn_backLogin);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_forgetpass = (Button) findViewById(R.id.btn_forgetpass);
        et_usernameLogin = (EditText) findViewById(R.id.et_usernameLogin);

        et_passLogin = (EditText) findViewById(R.id.et_passLogin);
        chk_saveLogin = (CheckBox) findViewById(R.id.chk_saveLogin);
        //layout đăng ký-------------------
        et_diachi = (EditText) findViewById(R.id.et_diachi);
        ll_dk = (LinearLayout) findViewById(R.id.ll_dk);
        btn_backSignup = (Button) findViewById(R.id.btn_backSignup);
        et_tkSignup = (EditText) findViewById(R.id.et_tkSignup);
        et_email = (EditText) findViewById(R.id.et_email);
        et_passSignup = (EditText) findViewById(R.id.et_passSignup);
        et_pwtrue = (EditText) findViewById(R.id.et_pwtrue);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_dk = (Button) findViewById(R.id.btn_dk);
        //------------------
        //layout reset
//        edt_passNew = (EditText) findViewById(R.id.edt_passNew);
        edt_passR = (EditText) findViewById(R.id.edt_passR);
        ll_reset = (LinearLayout) findViewById(R.id.ll_reset);
        btn_BackQuenpass = (Button) findViewById(R.id.btn_BackQuenpass);
        edt_mailReset = (EditText) findViewById(R.id.edt_mailReset);
        edt_passReset = (EditText) findViewById(R.id.edt_passReset);
        btn_Reset = (Button) findViewById(R.id.btn_Reset);

        //
        //click able
        btn_backLogin.setOnClickListener(clickListener);
        btn_backSignup.setOnClickListener(clickListener);
        btn_BackQuenpass.setOnClickListener(clickListener);
        btn_signup.setOnClickListener(clickListener);
        btn_login.setOnClickListener(clickListener);
        btn_forgetpass.setOnClickListener(clickListener);
        btn_dk.setOnClickListener(clickListener);
        btn_Reset.setOnClickListener(clickListener);
    }

    private void clickControl(int mode) {//mode:0 page login, =1: page register, =2: page reset
        if (mode == 0) {
            ll_signin.setVisibility(View.VISIBLE);
            ll_dk.setVisibility(View.GONE);
            ll_reset.setVisibility(View.GONE);
        } else if (mode == 1) {
            ll_signin.setVisibility(View.GONE);
            ll_dk.setVisibility(View.VISIBLE);
            ll_reset.setVisibility(View.GONE);
        } else if (mode == 2) {
            ll_signin.setVisibility(View.GONE);
            ll_dk.setVisibility(View.GONE);
            ll_reset.setVisibility(View.VISIBLE);
        }
    }
    //reset

    //login
    private void getdataUser() {
        //dọc dữ liệu
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //goi lại đường dẫn
        String duongdanlaptop = Server.Duongdangettk_kh;
        //doc het tac ca du lieu
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdanlaptop, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Thongtinkh> thongtinkhList = new ArrayList<>();
                //tạo biến

                if (response != null) {
//                    if(preferences.getBoolean("isCkeckBuy", false)){
//
//                    }
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            email = jsonObject.getString("email");
                            matkhau = jsonObject.getString("matkhau");
                            tenkh = jsonObject.getString("tenkhachhang");
                            diachi = jsonObject.getString("diachi");
                            sdt = jsonObject.getString("sdt");
                            //thêm du lieu vao mang
//                            mangkhachhang.add(new UserLogin(email,matkhau));
                            if (et_usernameLogin.getText().toString().equals(email) && et_passLogin.getText().toString().equals(matkhau)) {
                                Log.i("LoginSucess", "Login Sucess!");
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putBoolean("isCheckLogin", true);
                                editor.putString("email", email);
                                editor.putString("matkhau", matkhau);
                                editor.putString("tenkhachhang", tenkh);
                                editor.putString("diachi", diachi);
                                editor.putString("sdt", sdt);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
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
                    Checkconnection.ShowToast_Short(getApplicationContext(), "Fail REquest ");
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
//                param.put("idsanpham", String.valueOf(idlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_backLogin:
                    //

//                    if (isCheckLogin) {
//                        Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putBoolean("isCheckLogin", false);
//                        editor.commit();
                    //hien thong bao, ban chua dang nhap
                    Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                    startActivity(intent);
                    finish();
//                    }
                    Log.i("LoginApp", "Login App: " + isCheckLogin);
                    break;
                case R.id.btn_backSignup:
                    clickControl(0);
                    break;
                case R.id.btn_BackQuenpass:
                    clickControl(0);
                    break;
                case R.id.btn_signup:
                    clickControl(1);
                    break;
                case R.id.btn_login:
                    getdataUser();
                    break;
                case R.id.btn_forgetpass:
                    clickControl(2);
                    break;
                case R.id.btn_dk:
                    break;
                case R.id.btn_Reset:
                    final String emailReset = edt_mailReset.getText().toString();
//                    String passOld = edt_passR.getText().toString();
//                    String passNew = edt_passNew.getText().toString();
                    final String passConfrim = edt_passReset.getText().toString();
//                    if (preferences.getString("email", "").length() > 0 && preferences.getString("matkhau", "").length() > 0) {
                    if ((emailReset.length() > 0) && passConfrim.length() > 0) {
//                            if(edt_passR.equals(passConfrim)){
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        //goi lại đường dẫn
                        String duongdantk = Server.Duongdanresettk_kh;
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdantk, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                Log.i("RequestPass", "Respon :" + response);
                                if (response.contains("1")) {
                                    Log.i("RequestPass", "Update MK thanh cong!");
                                    clickControl(0);
                                } else {
                                    Log.i("RequestPass", "Update MK that bai!");
                                }

//                                        JSONArray jsonArray = null;
//                                        try {
//                                            jsonArray = new JSONArray();
//                                            for (int i = 0; i < jsonArray.length(); i++){
//                                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                                Gmail =jsonObject.getString("email");
//                                                Pass = jsonObject.getString("matkhau");
//                                                if (edt_mailReset.getText().toString().equals(email) && edt_passR.getText().toString().equals(matkhau)){
//                                                    Log.i("ResetSucess", "Reset Sucess!");
//                                                    SharedPreferences.Editor editor = preferences.edit();
//                                                    editor.putString("email", email);
//                                                    editor.putString("matkhau", matkhau);
//                                                    editor.commit();
//                                                    Intent intent = new Intent(getApplicationContext(), DangkyActivity.class);
//                                                    startActivity(intent);
//                                                    finish();
//                                                }else {
//                                                    Checkconnection.ShowToast_Short(getApplicationContext(),"nhap lai");
//                                                }
//                                            }
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> param = new HashMap<String, String>();
                                param.put("email", emailReset);
                                param.put("matkhau", passConfrim);
                                return param;
                            }
                        };
                        requestQueue.add(stringRequest);
                        // gui ham post mat khau moi len cho server
                        // sau khi gui len thanh cong
                        // thi trong respon goi lenh update mat khau
                        // thanh cong thi trong respon cua lenh uodate xuat thong bao cho khach hang biet
//                            } else {
//                                // show toast mat khau moi chua trung nhau
//                            }
//                            //
                    } else {
                        // show toast email hoac mat khau cu cua ban nhap khong chinh xac
                        Checkconnection.ShowToast_Short(getApplicationContext(), "email/mật khẩu không hợp lệ");
                    }


                    break;

            }
        }
    };

}
