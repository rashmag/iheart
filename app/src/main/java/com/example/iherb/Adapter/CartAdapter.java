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
import com.example.iherb.Item.CartModelFrag;
import com.example.iherb.Item.ParceModel;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.RecyclerViewHolder> {
    private View itemView;
    private ArrayList<CartModelFrag> parceModels;
    private MainActivity mainActivity;

    public CartAdapter(ArrayList<CartModelFrag> parceModels, MainActivity mainActivity) {
        this.parceModels = parceModels;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public CartAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.RecyclerViewHolder holder, int position) {
        CartModelFrag cartModelFrag = parceModels.get(position);
        Glide.with(mainActivity).load(cartModelFrag.getImgUrl()).into(holder.cartImg);
        holder.nameCart.setText(cartModelFrag.getName());
        holder.priceCart.setText(cartModelFrag.getPrice());

    }

    @Override
    public int getItemCount() {
        return parceModels.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView cartImg;
        TextView nameCart,priceCart;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImg = itemView.findViewById(R.id.cartImg);
            priceCart = itemView.findViewById(R.id.priceCart);
            nameCart = itemView.findViewById(R.id.nameCart);
        }
    }
}