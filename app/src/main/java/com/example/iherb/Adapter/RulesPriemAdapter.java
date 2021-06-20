package com.example.iherb.Adapter;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.iherb.BD.VitaminDataBase;
import com.example.iherb.BD.VitaminEntity;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import java.util.ArrayList;
import java.util.List;

public class RulesPriemAdapter extends RecyclerView.Adapter<RulesPriemAdapter.RecyclerViewHolder> {
    private View itemView;
    ArrayList<VitaminEntity> arrayList = new ArrayList<>();
    List<VitaminEntity> list = new ArrayList<>();
    MainActivity mainActivity;
    Integer toDayStr;
    private VitaminDataBase userDB;
    int position2;

    @NonNull
    @Override
    public RulesPriemAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.relues_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    public void setArrayList(List<VitaminEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RulesPriemAdapter(MainActivity mainActivity, Integer toDayStr,VitaminDataBase userDB) {
        this.mainActivity = mainActivity;
        this.toDayStr = toDayStr;
        this.userDB = userDB;
    }

    @Override
    public void onBindViewHolder(@NonNull RulesPriemAdapter.RecyclerViewHolder holder, int position) {
        final boolean[] check = {false};
        VitaminEntity rulesPriemDialogAdapter = list.get(position);
        holder.titleDialogRules.setText(rulesPriemDialogAdapter.getNameVitamin());
        holder.dataDialogRules.setText(rulesPriemDialogAdapter.getTime());
        holder.numberDialogRules.setText(position + 1 + "");
        if (rulesPriemDialogAdapter.getCheck()) {
            holder.checkRelues.setTag("true");
            holder.checkRelues.setImageResource(R.drawable.relues_item_check);
        }
        if (list.size() == 1 && position == 0) {
            holder.cardViewReluesItem.setBackground(mainActivity.getResources()
                    .getDrawable(R.drawable.form_relues_item));
            holder.constLayoutReluesItem.setBackground(mainActivity.getResources()
                    .getDrawable(R.drawable.form_relues_item));
        } else if (position == 0) {
            holder.cardViewReluesItem.setBackground(mainActivity.getResources()
                    .getDrawable(R.drawable.form_relues_item_top));
            holder.constLayoutReluesItem.setBackground(mainActivity.getResources()
                    .getDrawable(R.drawable.form_relues_item_top));
        }
        if (list.size() == 2 && position == 1) {
            holder.cardViewReluesItem.setBackground(mainActivity.getResources()
                    .getDrawable(R.drawable.form_relues_item_bottom));
            holder.constLayoutReluesItem.setBackground(mainActivity.getResources()
                    .getDrawable(R.drawable.form_relues_item_bottom));
        }
        position2 = position + 1;
        if (list.size() > 1) {
            if (list.size() == position2) {
                holder.cardViewReluesItem.setBackground(mainActivity.getResources()
                        .getDrawable(R.drawable.form_relues_item_bottom));
                holder.constLayoutReluesItem.setBackground(mainActivity.getResources()
                        .getDrawable(R.drawable.form_relues_item_bottom));
            }
        }

        holder.checkRelues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check[0]) {
                    addCheckAsyncTask(true);
                    holder.checkRelues.setImageResource(R.drawable.relues_item_check);
                    check[0] = true;
                }
//                else{
//                    holder.checkRelues.setImageResource(R.drawable.relues_item_uncheck);
//                    check[0] = false;
//                }
            }
        });
    }

    //Добавляем цель
    private void addCheckAsyncTask(boolean check) {
        VitaminEntity vitaminEntity = new VitaminEntity();
        vitaminEntity.setCheck(check);
        new addCheck().execute(vitaminEntity);
    }

    //Запускаем Асинхронный процесс добаления целей в БД
    private class addCheck extends AsyncTask<VitaminEntity, Void, Void> {
        @Override
        protected Void doInBackground(VitaminEntity... userEntities) {
            userDB.getUserDao().addUser(userEntities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            querySelectDaysAsyncTask(toDayStr);
        }
    }

    //Вытаскиваем бд по дням
    private void querySelectDaysAsyncTask(Integer i) {
        new querySelectDays().execute(i);
//        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    //Запускаем Асинхронный процесс вытаскивания всех целей из БД
    private class querySelectDays extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            list = userDB.getUserDao().getDay(integers[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (list.size() != 0) {
//                rulesPriemDialogAdapter.setArrayList(list);
                setArrayList(list);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView titleDialogRules, dataDialogRules, numberDialogRules;
        ImageView checkRelues;
        ConstraintLayout constLayoutReluesItem;
        CardView cardViewReluesItem;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            dataDialogRules = itemView.findViewById(R.id.dataDialogRules);
            titleDialogRules = itemView.findViewById(R.id.titleDialogRules);
            numberDialogRules = itemView.findViewById(R.id.numberDialogRules);
            checkRelues = itemView.findViewById(R.id.checkRelues);
            constLayoutReluesItem = itemView.findViewById(R.id.constLayoutReluesItem);
            cardViewReluesItem = itemView.findViewById(R.id.cardViewReluesItem);
        }
    }
}
