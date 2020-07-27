package com.example.admin.apps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.admin.apps.R;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;


public class chat_Activity extends AppCompatActivity {
    //đăng ký tk
    Toolbar tb_chating;
    EditText name_chat;
    Button signin_chat;
    ListView list_chat;
    //chating
    EditText name_chating;
    Button signin_chating;
    ListView list_chating;
    //mảng đang ký tk
    ArrayList<String>mangtk_chat;
    //mảng nội dung chat
    ArrayList<String>mangchating;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://20.0.25.143:3000");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mSocket.connect();
        mSocket.on("ketquadangky", onNewMessage_dangky);
        mSocket.on("server-gui-taikhoan", onNewMessage_dangsach);
        mSocket.on("server-gui-tin-chat", onNewMessage_dangsachtinchat);
        mangchating= new ArrayList<String>();
        init();
        toolbarback();
        click();
    }
    private Emitter.Listener onNewMessage_dangsachtinchat = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
//                    String username;
                    String noidung;
//                    String message;
                    try {
                        noidung = data.getString("tinchat");

                        mangchating.add(noidung);
                        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(),
                                android.R.layout.simple_list_item_1,mangchating);
                        list_chating.setAdapter(adapter2);
//                        Toast.makeText(getApplicationContext(),noidung.length()+"",Toast.LENGTH_SHORT).show();
//                        if(noidung == "true"){
//                            Toast.makeText(getApplicationContext(),"thanhcong",Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(getApplicationContext(),"thatbai",Toast.LENGTH_SHORT).show();
//                        }
//                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    private Emitter.Listener onNewMessage_dangsach = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
//                    String username;
                    JSONArray noidung;
//                    String message;
                    try {
                        noidung = data.getJSONArray("danhsach");
                        mangtk_chat = new ArrayList<String>();
                        for(int i=0;i<noidung.length();i++){
                            mangtk_chat.add(noidung.get(i).toString());
                        }
                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                                android.R.layout.simple_list_item_1,mangtk_chat);
                        list_chat.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(),noidung.length()+"",Toast.LENGTH_SHORT).show();
//                        if(noidung == "true"){
//                            Toast.makeText(getApplicationContext(),"thanhcong",Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(getApplicationContext(),"thatbai",Toast.LENGTH_SHORT).show();
//                        }
//                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    private Emitter.Listener onNewMessage_dangky = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
//                    String username;
                    String noidung;
//                    String message;
//                    JSONArray noidung;
                    try {
                        noidung = data.getString("noidung");

                        if(noidung == "true"){
                            Toast.makeText(getApplicationContext(),"thanhcong",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),noidung.length()+"",Toast.LENGTH_SHORT).show();
                        }
//                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };
    private void click() {
        signin_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.emit("client-gui-username", name_chat.getText().toString());
            }
        });
        signin_chating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.emit("client-gui-tinchat", name_chating.getText().toString());
            }
        });

    }

    private void init() {
        //dang ky tai khoan
        tb_chating= (Toolbar) findViewById(R.id.tb_chat);
        name_chat = (EditText) findViewById(R.id.name_chat);
        signin_chat = (Button) findViewById(R.id.signin_chat);
        list_chat = (ListView) findViewById(R.id.list_chat);
        //chating
        name_chating = (EditText) findViewById(R.id.name_chating);
        signin_chating = (Button) findViewById(R.id.signin_chating);
        list_chating = (ListView) findViewById(R.id.list_chating);
    }
    private void toolbarback() {
        setSupportActionBar(tb_chating);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb_chating.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}