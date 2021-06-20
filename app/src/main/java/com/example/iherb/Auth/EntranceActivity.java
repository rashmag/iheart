package com.example.iherb.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iherb.MainActivity;
import com.example.iherb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EntranceActivity extends AppCompatActivity {
    EditText email,password;
    TextView titleRegist;
    AppCompatButton entranceBtn;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        firebaseAuth = FirebaseAuth.getInstance();
        init();

        titleRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntranceActivity.this,RegisterActivity.class));
                overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
            }
        });
        entranceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Укажите email");
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Укажите пароль");
                }else {
                    signIn(email.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(EntranceActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            startActivity(new Intent(EntranceActivity.this, MainActivity.class));
                            overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("TAG", "signInWithEmail:failure2" + task.getException(), task.getException());
//                            updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
    }

    private void init(){
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        entranceBtn = findViewById(R.id.entranceBtn);
        titleRegist = findViewById(R.id.titleRegist);
    }
}