package com.example.admin.apps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.apps.R;
import com.example.admin.apps.adapter.Adaptersanpham;
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    TextView find;
    RecyclerView re_l;
    List<Sanpham> mangsanpham;
    Adaptersanpham adaptersanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        init();
        getdataspmoinhat();
    }

    private void init() {
        find = (EditText) findViewById(R.id.tiemkiem);
        re_l = (RecyclerView) findViewById(R.id.re_list);
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getApplicationContext());
//        re_l.setLayoutManager(mLayoutmanager);
////        re_l.setItemAnimator(new DefaultItemAnimator());
        re_l.setHasFixedSize(true);
        re_l.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        mangsanpham = new ArrayList<>();
        adaptersanpham = new Adaptersanpham(this, mangsanpham);
        re_l.setAdapter(adaptersanpham);


        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptersanpham.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if(find.length()==0){
////                    //đưa dữ liệu lên
////                    getdataspmoinhat();
////                }
                adaptersanpham.getFilter().filter(s);
            }
        });
    }

    private void getdataspmoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String Tensanpham = "";
                    Integer Giasanpham = 0;
                    String Hinhanhsanpham = "";
                    String Motasanpham = "";
                    int IDsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            IDsanpham = jsonObject.getInt("idsanpham");
                            mangsanpham.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));
                            adaptersanpham.notifyDataSetChanged();
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
}
