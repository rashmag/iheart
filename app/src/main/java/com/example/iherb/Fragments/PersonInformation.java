package com.example.iherb.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.iherb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PersonInformation extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AppCompatButton editInfoUser;
    private String mParam1;
    private EditText nameAndSureName,dayBirthday,monthBirthday,yearBirthday;
    private String mParam2;
    View v;
    private String userId;

    public PersonInformation() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_person_information, container, false);
        init();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        editInfoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(userId);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("userName", nameAndSureName);
                    hashMap.put("day", dayBirthday);
                    hashMap.put("month", monthBirthday);
                    hashMap.put("year", yearBirthday);
                    hashMap.put("id", userId);
//                                hashMap.put("search", userName.toLowerCase());
                    reference.updateChildren(hashMap);
                }
        });

        return v;
    }

    private void init() {
        editInfoUser = v.findViewById(R.id.editInfoUser);
        nameAndSureName = v.findViewById(R.id.nameAndSureName);
        dayBirthday = v.findViewById(R.id.dayBirthday);
        monthBirthday = v.findViewById(R.id.monthBirthday);
        yearBirthday = v.findViewById(R.id.yearBirthday);
    }
}