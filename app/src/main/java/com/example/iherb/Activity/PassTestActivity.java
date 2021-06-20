package com.example.iherb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.iherb.R;

public class PassTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_test);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));
    }


}