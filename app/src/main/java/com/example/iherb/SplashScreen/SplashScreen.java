package com.example.iherb.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.iherb.Auth.EntranceAndRegister;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setStatusBarColor(getResources().getColor(R.color.mainColor));


        //Запускаем асинхронную задачу на 300 миллисекунд
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreen.this, EntranceAndRegister.class));
                    overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
                }
            }
        };
        thread.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}