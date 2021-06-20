package com.example.iherb.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iherb.Auth.EntranceAndRegister;
import com.example.iherb.MainActivity;
import com.example.iherb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView outProfile;
    ProgressBar progressBarProfile;
    String userId;
    View v;

    private TextView nameUser,emailUser,personInfo,resultAnket,resultAnaliz
            ,historyZakaz;
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;
    public UserFragment() {
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        v = inflater.inflate(R.layout.fragment_user, container, false);
        init();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        progressBarProfile.setVisibility(View.VISIBLE);
        mainActivity = (MainActivity)getActivity();
        outProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), EntranceAndRegister.class));
                getActivity().overridePendingTransition(R.anim.animation_between_activity_show, R.anim.animation_between_activity_hide);
            }
        });
        personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.stepFragment2();
            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    if (getActivity() == null) {
                        return;
                    }
                    if(snapshot1.child("id") != null){
                        if(snapshot1.child("id").getValue().equals(userId)){
                            if(snapshot1.child("userName") != null){
                                nameUser.setText(snapshot1.child("userName").getValue()+"");
                            }
                            if(snapshot1.child("email") != null){
                                progressBarProfile.setVisibility(View.GONE);
                                emailUser.setText(snapshot1.child("email").getValue()+"");
                            }
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
    private void init() {
        nameUser = v.findViewById(R.id.nameUser);
        emailUser = v.findViewById(R.id.emailUser);
        personInfo = v.findViewById(R.id.personInfo);
        resultAnket = v.findViewById(R.id.resultAnket);
        resultAnaliz = v.findViewById(R.id.resultAnaliz);
        progressBarProfile = v.findViewById(R.id.progressBarProfile);
        historyZakaz = v.findViewById(R.id.historyZakaz);
        outProfile = v.findViewById(R.id.outProfile);
    }
}