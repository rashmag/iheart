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
import android.widget.Toast;

import com.example.iherb.Anketa.Anketa;
import com.example.iherb.MainActivity;
import com.example.iherb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText nameAndSureName,dayBirthday,monthBirthday,yearBirthday,email,password;
    AppCompatButton registBtn;
    TextView titleEntrance;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        firebaseAuth = FirebaseAuth.getInstance();
        init();

        FirebaseApp.initializeApp(this);
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(nameAndSureName.getText().toString())) {
                    nameAndSureName.setError("Укажите Имя");
                } else if (TextUtils.isEmpty(dayBirthday.getText().toString())) {
                    dayBirthday.setError("Укажите день рождения");
                }else if (TextUtils.isEmpty(monthBirthday.getText().toString())) {
                    monthBirthday.setError("Укажите месяц рождения");
                }else if (TextUtils.isEmpty(yearBirthday.getText().toString())) {
                    yearBirthday.setError("Укажите год рождения");
                } else if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Укажите email");
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Укажите пароль");
                } else {
                    createUser(nameAndSureName.getText().toString(),dayBirthday.getText().
                            toString(),monthBirthday.getText().toString(),yearBirthday.getText()
                            .toString(),email.getText().toString(),
                            password.getText().toString());
                }
            }
        });

        titleEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,EntranceActivity.class));
                overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
            }
        });
    }
    private void createUser(String name, String day, String month, String year, String email,
                            String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            // Sign in success, update UI with the signed-in user's information
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                                    .child("Users").child(userId);
                            if (task.isSuccessful()) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("userName", name);
                                hashMap.put("day", day);
                                hashMap.put("month", month);
                                hashMap.put("year", year);
                                hashMap.put("email", email);
                                hashMap.put("id", userId);
//                                hashMap.put("search", userName.toLowerCase());
                                reference.setValue(hashMap);
                            }
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(RegisterActivity.this, Anketa.class));
                            overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    private void init(){
        registBtn = findViewById(R.id.registBtn);
        nameAndSureName = findViewById(R.id.nameAndSureName);
        dayBirthday = findViewById(R.id.dayBirthday);
        monthBirthday = findViewById(R.id.monthBirthday);
        email = findViewById(R.id.email);
        titleEntrance = findViewById(R.id.titleEntrance);
        password = findViewById(R.id.password);
        yearBirthday = findViewById(R.id.yearBirthday);
    }
}