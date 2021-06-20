package com.example.iherb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.iherb.R;

public class MakingTestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_tests);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));
    }
}