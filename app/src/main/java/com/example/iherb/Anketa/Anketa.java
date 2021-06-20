package com.example.iherb.Anketa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.iherb.MainActivity;
import com.example.iherb.R;

public class Anketa extends AppCompatActivity {
    AppCompatButton saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anketa2);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Anketa.this, MainActivity.class));
            }
        });
    }

}