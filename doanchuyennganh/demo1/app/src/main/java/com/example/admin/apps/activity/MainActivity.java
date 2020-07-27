package com.example.admin.apps.activity;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.apps.R;

import com.example.admin.apps.adapter.LaptopAdapter;
import com.example.admin.apps.adapter.LoaispAdapter;
import com.example.admin.apps.adapter.SanphamAdapter;
import com.example.admin.apps.adapter.SanphamLapTopAdapter;
import com.example.admin.apps.model.Giohang;
import com.example.admin.apps.model.Loaisp;
import com.example.admin.apps.model.Sanpham;
import com.example.admin.apps.ultil.Checkconnection;
import com.example.admin.apps.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    //    RecyclerView recyleview;
    NavigationView navigationView;
    ListView List_manghinhchinh;
    DrawerLayout D_layout;

    LinearLayout click;
    LinearLayout xemthemdt;
    RecyclerView re_dienthoai;
    RecyclerView re_laptop;
    //mảng loại sản phẩm
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";
    //mảng sản phẩm
    ArrayList<Sanpham> listDienThoai;
    ArrayList<Sanpham> listLaptop;

    SanphamLapTopAdapter laptopAdapter;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> manggiohang;
    //tạo biến cục bộ  getdataspmoinhat
    int ID = 0;
    String Tensanpham = "";
    Integer Giasanpham = 0;
    String Hinhanhsanpham = "";
    String Motasanpham = "";
    int IDsanpham = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
            event();
            advertisement();
            getdataloaisp();
            getdataspmoinhat();
            listviewsp();
//
            chosese();
        } else {
            Checkconnection.ShowToast_Short(getApplicationContext(), "bạn hãy kiểm tra lại mạng ");
            finish();
        }
        advertisement();
        mangloaisp.add(new Loaisp(0, "Trang chủ", "https://cdn2.iconfinder.com/data/icons/line-color-mix-vol-5/128/Line_Mix_Vol.7-99-512.png"));
        mangloaisp.add(new Loaisp(0, "Điện Thoại", "https://cdn.tgdd.vn/Products/Images/42/114111/iphone-x-256gb-h2-400x460-400x460.png"));
        mangloaisp.add(new Loaisp(0, "LapTop", "https://cdn.tgdd.vn/Products/Images/44/184384/apple-macbook-pro-touch-mr9q2sa-a-2018-thumb-1-600x600.jpg"));
        mangloaisp.add(new Loaisp(0, "Tài Khoản", "https://cdn2.iconfinder.com/data/icons/business-271/135/19-128.png"));
        mangloaisp.add(new Loaisp(0, "Liên Hệ", "https://cdn0.iconfinder.com/data/icons/seo-and-internet-marketing/70/SEO_and_Internet_Marketing-96-128.png"));
    }
//click xem thêm laptop
    private void chosese() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LaptopActivity.class);
                startActivity(intent);
            }
        });
        xemthemdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dien_thoaiActivity.class);
                startActivity(intent);
            }
        });
    }
//click xem thêm dienthoai



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
                break;
            case R.id.menusearch:
                Intent in = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //màng hình của các menu
    private void listviewsp() {
        List_manghinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i) {
                    case 0:
                        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "bạn hãy kiễm tra lại kết nối",Toast.LENGTH_SHORT).show();

                        }
                        D_layout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, dien_thoaiActivity.class);
                            intent.putExtra("idloaisanpham", mangloaisp.get(i).getId());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "bạn hãy kiễm tra lại kết nối",Toast.LENGTH_SHORT).show();
                        }
                        D_layout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                            intent.putExtra("idloaisanpham", mangloaisp.get(i).getId());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "bạn hãy kiễm tra lại kết nối",Toast.LENGTH_SHORT).show();
                        }
                        D_layout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, LienheActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "bạn hãy kiễm tra lại kết nối",Toast.LENGTH_SHORT).show();
                        }
                        D_layout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (Checkconnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, ThongtinActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "bạn hãy kiễm tra lại kết nối",Toast.LENGTH_SHORT).show();
                        }
                        D_layout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }


    private void getdataspmoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            IDsanpham = jsonObject.getInt("idsanpham");
                            if (IDsanpham == 1) {
                                listDienThoai.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));
                                sanphamAdapter.notifyDataSetChanged();
                            } else {
                                listLaptop.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));
                                sanphamAdapter.notifyDataSetChanged();
                            }

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

    private void getdataloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //   RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("");
                            tenloaisp = jsonObject.getString("tenloaisp");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                            mangloaisp.add(new Loaisp(id, tenloaisp, hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //check kết nối menu
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void advertisement() {


        ArrayList<String> mangquangcao = new ArrayList<>();
        //banner quan cao
        mangquangcao.add("http://sonycenter.sony.com.vn/Data/Sites/1/News/372/1200x400.jpg");
        mangquangcao.add("https://phonegallery.gr/images/companies/1/BANNER(1200X400)_s9_ENG_PHONEGALLERY.jpg?1520600122871");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void event() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D_layout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        xemthemdt = (LinearLayout) findViewById(R.id.xemthemdt);
        click = (LinearLayout) findViewById(R.id.click);
        toolbar = (Toolbar) findViewById(R.id.manghinhchinh_toolbar);
        viewFlipper = (ViewFlipper) findViewById(R.id.vFliper);
//        recyleview = (RecyclerView) findViewById(R.id.recyle_view);
        navigationView = (NavigationView) findViewById(R.id.n_view);
        List_manghinhchinh = (ListView) findViewById(R.id.List_manghinhchinh);
        re_dienthoai = (RecyclerView) findViewById(R.id.re_dienthoai);
        re_laptop = (RecyclerView) findViewById(R.id.re_laptop);
        D_layout = (DrawerLayout) findViewById(R.id.D_layout);
        mangloaisp = new ArrayList<>();
        loaispAdapter = new LoaispAdapter(mangloaisp, getApplicationContext());
        List_manghinhchinh.setAdapter(loaispAdapter);
        listDienThoai = new ArrayList<>();
        listLaptop = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(), listDienThoai);
        re_dienthoai.setHasFixedSize(true);
//        re_dienthoai.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        re_dienthoai.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        re_dienthoai.setAdapter(sanphamAdapter);
        //laptop
        laptopAdapter = new SanphamLapTopAdapter(getApplicationContext(), listLaptop);
        re_laptop.setHasFixedSize(true);
        re_laptop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        re_laptop.setAdapter(laptopAdapter);

        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String chuoi) {
//        Log.d("ketqua",chuoi);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
