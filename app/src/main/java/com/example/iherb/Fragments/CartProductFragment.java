package com.example.iherb.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iherb.Item.CartModel;
import com.example.iherb.MainActivity;
import com.example.iherb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CartProductFragment extends Fragment {
    private TextView cartCount;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView backCart,cartIcon,imageViewProduct;
    private AppCompatButton addCart;
    private TextView nameCartProduct;
    ProgressBar progressBarCount;
    private String mParam1;
    private String mParam2;
    private View v;
    private String userId;
    String imgUrl, name, price;
    public CartProductFragment(String imgUrl, String name, String price) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cart, container, false);
        init();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        nameCartProduct.setText(name);
        Glide.with(getActivity()).load(imgUrl).into(imageViewProduct);
        nameCartProduct.setText(name);
        progressBarCount.setVisibility(View.VISIBLE);
        backCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.shopFragment();
            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                        .child("Cart").child(userId).child(name);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("imgUrl", imgUrl);
                    hashMap.put("name", name);
                    hashMap.put("price", price);
                    hashMap.put("id", userId);
//                                hashMap.put("search", userName.toLowerCase());
                    reference.setValue(hashMap);
            }
        });

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cart")
//                ;
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    if (getActivity() == null) {
//                        return;
//                    }
//                    if(snapshot1.child("id") != null) {
//                        Toast.makeText(getActivity(), "das = " +
//                                snapshot1.child("id").getValue(), Toast.LENGTH_SHORT).show();
//                        if (snapshot1.child("id").getValue().equals(userId)) {
//                            if (snapshot1.getChildrenCount() != 0) {
//                                progressBarCount.setVisibility(View.GONE);
//                                cartCount.setText(snapshot1.getChildrenCount() + "");
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.cartFragment();
            }
        });
        return v;
    }

    private void init() {
        addCart = v.findViewById(R.id.addCart);
        backCart = v.findViewById(R.id.backCart);
        progressBarCount = v.findViewById(R.id.progressBarCount);
        cartIcon = v.findViewById(R.id.cartIcon);
        cartCount = v.findViewById(R.id.cartCount);
        imageViewProduct = v.findViewById(R.id.imageViewProduct);
        nameCartProduct = v.findViewById(R.id.nameCartProduct);
    }
}