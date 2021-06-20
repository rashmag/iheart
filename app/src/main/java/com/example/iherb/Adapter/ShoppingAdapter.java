package com.example.iherb.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iherb.Item.ParceModel;
import com.example.iherb.Item.ShoppingModel;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import java.util.ArrayList;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.RecyclerViewHolder> {
    private View itemView;
    private ArrayList<ShoppingModel> shoppingModels;
    private MainActivity mainActivity;
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }
    public ShoppingAdapter(ArrayList<ShoppingModel> parceModels, MainActivity mainActivity) {
        this.shoppingModels = parceModels;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ShoppingModel shoppingModel = shoppingModels.get(position);
        Glide.with(mainActivity).load(shoppingModel.getUrlImage()).into(holder.imageViewShop);
        holder.nameShop.setText(shoppingModel.getName());
        holder.priceShop.setText(shoppingModel.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.cartProductFragment(shoppingModel.getUrlImage(),shoppingModel.getName(),
                        shoppingModel.getPrice());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingModels.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageViewShop;
            TextView priceShop,nameShop;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewShop = itemView.findViewById(R.id.imageViewShop);
            priceShop = itemView.findViewById(R.id.priceShop);
            nameShop = itemView.findViewById(R.id.nameShop);

        }
    }
}
