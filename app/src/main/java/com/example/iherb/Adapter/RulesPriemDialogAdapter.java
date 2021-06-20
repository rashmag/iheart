package com.example.iherb.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iherb.BD.VitaminEntity;
import com.example.iherb.R;

import java.util.ArrayList;

public class RulesPriemDialogAdapter extends RecyclerView.Adapter<RulesPriemDialogAdapter.RecyclerViewHolder> {
    private View itemView;
    ArrayList<VitaminEntity> arrayList = new ArrayList<>();
    @NonNull
    @Override
    public RulesPriemDialogAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.relues_dialog_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    public void setArrayList(ArrayList<VitaminEntity> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RulesPriemDialogAdapter.RecyclerViewHolder holder, int position) {
        VitaminEntity rulesPriemDialogAdapter = arrayList.get(position);
        holder.titleDialogRules.setText(rulesPriemDialogAdapter.getNameVitamin());
        holder.dataDialogRules.setText(rulesPriemDialogAdapter.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView titleDialogRules,dataDialogRules;
        ImageView pencilDialogRules;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            dataDialogRules = itemView.findViewById(R.id.dataDialogRules);
            titleDialogRules = itemView.findViewById(R.id.titleDialogRules);
            pencilDialogRules = itemView.findViewById(R.id.pencilDialogRules);
        }
    }
}
