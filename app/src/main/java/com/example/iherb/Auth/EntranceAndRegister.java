package com.example.iherb.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iherb.MainActivity;
import com.example.iherb.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EntranceAndRegister extends AppCompatActivity {
    private AppCompatButton registBtn;
    FirebaseAuth firebaseAuth;
    TextView titleEntrance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance_and_register);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        init();
        if (firebaseAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            updateUI(currentUser);
        }
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntranceAndRegister.this,RegisterActivity.class));
                overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
            }
        });
        titleEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntranceAndRegister.this,EntranceActivity.class));
                overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(EntranceAndRegister.this, MainActivity.class));
        } else {
            Toast.makeText(this, "Error Facebook", Toast.LENGTH_SHORT).show();
        }
    }
    private void init(){
        titleEntrance = findViewById(R.id.titleEntrance);
        registBtn = findViewById(R.id.registBtn);
    }
}