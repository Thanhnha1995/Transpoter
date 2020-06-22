package com.example.admin.apps.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.apps.R;

public class Splash_activity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_activity);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try
//                {
//                    //thời gian chờ load màng hình
//                    Thread.sleep(5000);
//                }catch (Exception e){
//
//                }finally {
//                    //chờ xong load qua login
//                    Intent trangchu = new Intent(Splash_activity.this, MainActivity.class);
//                    startActivity(trangchu);
//                    finish();
//                }
//            }
//        });
//        thread.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash_activity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Splash", "lan2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Splash", "end");
    }
}
