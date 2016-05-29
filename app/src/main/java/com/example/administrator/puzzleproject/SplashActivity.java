package com.example.administrator.puzzleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yh.application.MyApplication;

public class SplashActivity extends AppCompatActivity {

    /**
     * 如果按返回键，就不跳跃到MainActivity
     */
    Boolean jump=true;

    private Button bt_splash_start;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();

        bt_splash_start = (Button)findViewById(R.id.bt_splash_start);
        myApplication=(MyApplication)getApplication();

        /**
         * myApplication.getStartGame()如果是true,隐藏开始游戏button，splash页面自动跳转
         */
        if(myApplication.getStartGame()){

            bt_splash_start.setVisibility(View.INVISIBLE);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);

                        if(jump){
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }



    }

    /**
     * 开始游戏按钮
     * @param v
     */
    public void startGame(View v){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        myApplication.setStartGame(true);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        jump=false;
    }
}
