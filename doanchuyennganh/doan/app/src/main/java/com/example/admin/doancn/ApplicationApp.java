package com.example.admin.doancn;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ApplicationApp extends Application {
    private static Context instance = null;
//    public boolean isLogin;

    SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

//        isLogin = preferences.getBoolean("isLogin", false);


    }

    public static Context getInstance() {
        return instance;
    }
}
