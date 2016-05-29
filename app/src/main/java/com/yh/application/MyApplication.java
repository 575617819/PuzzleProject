package com.yh.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MyApplication extends Application {

    private SharedPreferences sp;
    private boolean bt_splash_start;

    @Override
    public void onCreate() {
        super.onCreate();

        sp = getSharedPreferences("information", MODE_PRIVATE);
        bt_splash_start = sp.getBoolean("bt_splash_start", false);
    }

    public boolean getStartGame(){
        return bt_splash_start;
    }

    public void setStartGame(Boolean bt_splash_start){
        this.bt_splash_start = bt_splash_start;
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("bt_splash_start",bt_splash_start);
        edit.commit();
    }
}
