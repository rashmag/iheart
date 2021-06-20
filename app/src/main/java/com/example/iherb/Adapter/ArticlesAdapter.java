package com.example.iherb.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iherb.Item.ParceModel;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.RecyclerViewHolder> {
    private View itemView;
    private ArrayList<ParceModel> parceModels;
    private MainActivity mainActivity;

    public ArticlesAdapter(ArrayList<ParceModel> parceModels, MainActivity mainActivity) {
        this.parceModels = parceModels;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ArticlesAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.RecyclerViewHolder holder, int position) {
        ParceModel parceModel = parceModels.get(position);
        holder.title.setText(parceModel.getTitle());
        holder.description.setText(parceModel.getDescription());
        Log.d("position2","position = " + position);
        holder.mainKnow.setVisibility(View.GONE);
        if(position == 0){
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,16,0,0);
            layoutParams.endToEnd = holder.constLayAllContent.getId();
            layoutParams.topToBottom = holder.mainKnow.getId();
            holder.constLayContent.setLayoutParams(layoutParams);
            holder.mainKnow.setVisibility(View.VISIBLE);
            Log.d("position2","position1 = " + position);
            holder.cardView.setElevation(0);
            holder.cardView.setBackground(mainActivity.getResources().getDrawable(android.R.color.transparent));
        }else if(position == 3 || position == 6 || position == 9 || position == 12 || position == 15
                || position == 18 || position == 21 || position == 24 || position == 27){
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,16,0,0);
            layoutParams.endToEnd = holder.constLayAllContent.getId();
            layoutParams.topToBottom = holder.mainKnow.getId();
            holder.constLayContent.setLayoutParams(layoutParams);
            holder.mainKnow.setVisibility(View.VISIBLE);
            Log.d("position2","position2 = " + position);
            holder.mainKnow.setText(parceModel.getTitle());
            holder.cardView.setElevation(0);
            holder.cardView.setBackground(mainActivity.getResources().getDrawable(android.R.color.transparent));
        }

    }

    @Override
    public int getItemCount() {
        return parceModels.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, description,mainKnow;
        CardView cardView;
        ConstraintLayout constLayContent,constLayAllContent;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleArticles);
            mainKnow = itemView.findViewById(R.id.mainKnow);
            cardView = itemView.findViewById(R.id.cardViewArticles);
            description = itemView.findViewById(R.id.descriptionArticles);
            constLayContent = itemView.findViewById(R.id.constLayContent);
            constLayAllContent = itemView.findViewById(R.id.constLayAllContent);
        }
    }
}
