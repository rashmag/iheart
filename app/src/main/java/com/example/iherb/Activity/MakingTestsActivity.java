package com.example.iherb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.iherb.Anketa.ResultAnaliz;
import com.example.iherb.R;

public class MakingTestsActivity extends AppCompatActivity {
    AppCompatButton getRecomBtn;
    EditText titleMakinEdit1,titleMakinEdit2,titleMakinEdit3,titleMakinEdit4,titleMakinEdit5,titleMakinEdit6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_tests);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        init();
        getRecomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakingTestsActivity.this, ResultAnaliz.class);
                intent.putExtra("answer1",titleMakinEdit1.getText().toString());
                intent.putExtra("answer2",titleMakinEdit2.getText().toString());
                intent.putExtra("answer3",titleMakinEdit3.getText().toString());
                intent.putExtra("answer4",titleMakinEdit4.getText().toString());
                intent.putExtra("answer5",titleMakinEdit5.getText().toString());
                intent.putExtra("answer6",titleMakinEdit6.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void init(){
        getRecomBtn = findViewById(R.id.getRecomBtn);
        titleMakinEdit1 = findViewById(R.id.titleMakinEdit1);
        titleMakinEdit2 = findViewById(R.id.titleMakinEdit2);
        titleMakinEdit3 = findViewById(R.id.titleMakinEdit3);
        titleMakinEdit4 = findViewById(R.id.titleMakinEdit4);
        titleMakinEdit5 = findViewById(R.id.titleMakinEdit5);
        titleMakinEdit6 = findViewById(R.id.titleMakinEdit6);
    }
}