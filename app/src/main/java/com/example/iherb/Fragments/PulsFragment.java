package com.example.iherb.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iherb.Adapter.RulesPriemAdapter;
import com.example.iherb.Adapter.RulesPriemDialogAdapter;
import com.example.iherb.BD.VitaminDataBase;
import com.example.iherb.BD.VitaminEntity;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PulsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AppCompatButton ruleBtn, fillBtn;
    private ConstraintLayout constLayoutIsNullBD;
    private TextView dataTextView;
    private ImageView btnDayAddVit1, btnDayAddVit2, btnDayAddVit3, btnDayAddVit4, btnDayAddVit5,
            btnDayAddVit6, btnDayAddVit7;
    private ArrayList<VitaminEntity> arrayList = new ArrayList<>();
    List<VitaminEntity> list;
    private String mParam1;
    private ImageView btnDay1, btnDay2, btnDay3, btnDay4, btnDay5, btnDay6, btnDay7;
    private boolean btnDayBol1, btnDayBol2, btnDayBol3, btnDayBol4, btnDayBol5, btnDayBol6, btnDayBol7;
    private boolean btnDayBolAddVit1, btnDayBolAddVit2, btnDayBolAddVit3, btnDayBolAddVit4, btnDayBolAddVit5, btnDayBolAddVit6, btnDayBolAddVit7;
    private String mParam2;
    RecyclerView recyclerView, recyclerViewRules;
    private RulesPriemDialogAdapter rulesPriemDialogAdapter;
    private SharedPreferences sharedPreferences;
    private RulesPriemAdapter rulesPriemAdapter;
    private VitaminDataBase userDB;
    private View v;
    private VitaminEntity UE = new VitaminEntity();
    private Dialog dialog, dialogAddVitamin;
    private Calendar calendar;
    private int toDay;
    private Integer toDayStr;

    public PulsFragment() {
    }

    public static PulsFragment newInstance(String param1, String param2) {
        PulsFragment fragment = new PulsFragment();
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
        v = inflater.inflate(R.layout.fragment_puls, container, false);
        init();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.mainColor));
        // Inflate the layout for this fragment
        sharedPreferences = getActivity().getSharedPreferences("FirstClickRulesBtn", Context.MODE_PRIVATE);
        userDB = Room.databaseBuilder(getActivity(), VitaminDataBase.class, "UserDB").allowMainThreadQueries().build();
        rulesPriemDialogAdapter = new RulesPriemDialogAdapter();
        calendar = Calendar.getInstance();
        toDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (toDay) {
            case Calendar.MONDAY:
                toDayStr = 2;
                querySelectDaysAsyncTask(2);
                break;
            case Calendar.TUESDAY:
                querySelectDaysAsyncTask(3);
                toDayStr = 3;
                break;
            case Calendar.WEDNESDAY:
                querySelectDaysAsyncTask(4);
                toDayStr = 4;
                break;
            case Calendar.THURSDAY:
                querySelectDaysAsyncTask(5);
                toDayStr = 5;
                break;
            case Calendar.FRIDAY:
                querySelectDaysAsyncTask(6);
                toDayStr = 6;
                break;
            case Calendar.SATURDAY:
                querySelectDaysAsyncTask(7);
                toDayStr = 7;
                break;
            case Calendar.SUNDAY:
                querySelectDaysAsyncTask(1);
                toDayStr = 1;
                break;
        }
        MainActivity mainActivity = (MainActivity)getActivity();
        rulesPriemAdapter = new RulesPriemAdapter(mainActivity,toDayStr,userDB);
        ruleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sharedPreferences.getBoolean("firstClickRulesBtn",false)) {
                    sharedPreferences.edit().putBoolean("firstClickRulesBtn", true).apply();
                }
                rulesPriemDialog();
                constLayoutIsNullBD.setVisibility(View.GONE);
            }
        });
        if(!sharedPreferences.getBoolean("firstClickRulesBtn",false)) {
            constLayoutIsNullBD.setVisibility(View.VISIBLE);
        }else{
            constLayoutIsNullBD.setVisibility(View.GONE);
        }
        fillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        queryAllGoalsAsyncTask();
        //recyclerViewRules
        recyclerViewRules.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewRules.setHasFixedSize(true);
        recyclerViewRules.setAdapter(rulesPriemAdapter);
        clickBtnDays();
        return v;
    }

    private void clickBtnDays() {
        btnDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(2);
            }
        });
        btnDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(3);
            }
        });
        btnDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(4);
            }
        });
        btnDay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(5);
            }
        });
        btnDay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(6);
            }
        });
        btnDay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(7);
            }
        });
        btnDay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySelectDaysAsyncTask(1);
            }
        });
    }


    private void rulesPriemDialog() {
        View vDialog = LayoutInflater.from(getActivity()).inflate(R.layout.rule_priem_dialog,
                null);
        final AppCompatButton ruleBtn = vDialog.findViewById(R.id.ruleBtn);
        final TextView selectShop = vDialog.findViewById(R.id.selectShop);
        final TextView cancelBtn = vDialog.findViewById(R.id.cancelBtn);
        final ConstraintLayout constLayoutRulesPriem = vDialog.findViewById(R.id.constLayoutRulesPriem);
        constLayoutRulesPriem.setVisibility(View.VISIBLE);
        //AddVitamin
        clickBtnDaysAddVitamin(vDialog);
        final AppCompatButton addBtnSave = vDialog.findViewById(R.id.addBtnSave);
        final ConstraintLayout constLayoutAddVitamin = vDialog.findViewById(R.id.constLayoutAddVitamin);
        constLayoutAddVitamin.setVisibility(View.GONE);
        recyclerView = vDialog.findViewById(R.id.recyclerViewDialogRule);
        final EditText nameVitamin = vDialog.findViewById(R.id.nameVitamin);
        final EditText countVitaminAdd = vDialog.findViewById(R.id.countVitaminAdd);
        final EditText timeAddVitamin = vDialog.findViewById(R.id.timeAddVitamin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rulesPriemDialogAdapter);

        btnDayAddVit1.setTag("false");
        btnDayAddVit2.setTag("false");
        btnDayAddVit3.setTag("false");
        btnDayAddVit4.setTag("false");
        btnDayAddVit5.setTag("false");
        btnDayAddVit6.setTag("false");
        btnDayAddVit7.setTag("false");
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(vDialog);
//                dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        addBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGoalsAsyncTask(nameVitamin.getText().toString(), timeAddVitamin.getText().toString(),
                        countVitaminAdd.getText().toString());
                constLayoutRulesPriem.setVisibility(View.VISIBLE);
                constLayoutAddVitamin.setVisibility(View.GONE);
            }
        });
        ruleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constLayoutRulesPriem.setVisibility(View.GONE);
                constLayoutAddVitamin.setVisibility(View.VISIBLE);
//                View vDialogAddVitamin = LayoutInflater.from(getActivity()).inflate(R.layout.add_vitamin_dialog,
//                        null);
//                final TextView cancelBtn = vDialogAddVitamin.findViewById(R.id.cancelBtn);
//                cancelBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogAddVitamin.dismiss();
//                    }
//                });
//                dialogAddVitamin = new Dialog(getActivity());
//                dialogAddVitamin.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialogAddVitamin.setContentView(vDialogAddVitamin);
//                dialogAddVitamin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.dismiss();
//                dialogAddVitamin.show();
            }
        });
        dialog.show();
    }

    private void clickBtnDaysAddVitamin(View vDialog) {
        btnDayAddVit1 = vDialog.findViewById(R.id.btnDayAddVit1);
        btnDayAddVit2 = vDialog.findViewById(R.id.btnDayAddVit2);
        btnDayAddVit3 = vDialog.findViewById(R.id.btnDayAddVit3);
        btnDayAddVit4 = vDialog.findViewById(R.id.btnDayAddVit4);
        btnDayAddVit5 = vDialog.findViewById(R.id.btnDayAddVit5);
        btnDayAddVit6 = vDialog.findViewById(R.id.btnDayAddVit6);
        btnDayAddVit7 = vDialog.findViewById(R.id.btnDayAddVit7);
        btnDayAddVit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit1) {
                    btnDayAddVit1.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit1.setTag("true");
                    btnDayBolAddVit1 = true;
                } else {
                    btnDayAddVit1.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit1.setTag("false");
                    btnDayBolAddVit1 = false;
                }
            }
        });
        btnDayAddVit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit2) {
                    btnDayAddVit2.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit2.setTag("true");
                    btnDayBolAddVit2 = true;
                } else {
                    btnDayAddVit2.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit2.setTag("false");
                    btnDayBolAddVit2 = false;
                }
            }
        });
        btnDayAddVit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit3) {
                    btnDayAddVit3.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit3.setTag("true");
                    btnDayBolAddVit3 = true;
                } else {
                    btnDayAddVit3.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit3.setTag("false");
                    btnDayBolAddVit3 = false;
                }
            }
        });
        btnDayAddVit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit4) {
                    btnDayAddVit4.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit4.setTag("true");
                    btnDayBolAddVit4 = true;
                } else {
                    btnDayAddVit4.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit4.setTag("false");
                    btnDayBolAddVit4 = false;
                }
            }
        });
        btnDayAddVit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit5) {
                    btnDayAddVit5.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit5.setTag("true");
                    btnDayBolAddVit5 = true;
                } else {
                    btnDayAddVit5.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit5.setTag("false");
                    btnDayBolAddVit5 = false;
                }
            }
        });
        btnDayAddVit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit6) {
                    btnDayAddVit6.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit6.setTag("true");
                    btnDayBolAddVit6 = true;
                } else {
                    btnDayAddVit6.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit6.setTag("false");
                    btnDayBolAddVit6 = false;
                }
            }
        });
        btnDayAddVit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnDayBolAddVit7) {
                    btnDayAddVit7.setImageResource(R.drawable.check_dialog_add_vitamin);
                    btnDayAddVit7.setTag("true");
                    btnDayBolAddVit7 = true;
                } else {
                    btnDayAddVit7.setImageResource(R.drawable.uncheck_dialog_add_vitamin);
                    btnDayAddVit7.setTag("false");
                    btnDayBolAddVit7 = false;
                }
            }
        });
    }

    //Добавляем цель
    private void addGoalsAsyncTask(String nameVitamin, String timeAddVitamin,
                                   String countVitaminAdd) {
        if (btnDayAddVit1.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 2);
            new addGoals().execute(vitaminEntity);
        }
        if (btnDayAddVit2.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 3);
            new addGoals().execute(vitaminEntity);
        }
        if (btnDayAddVit3.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 4);
            new addGoals().execute(vitaminEntity);
        }
        if (btnDayAddVit4.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 5);
            new addGoals().execute(vitaminEntity);
        }
        if (btnDayAddVit5.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 6);
            new addGoals().execute(vitaminEntity);
        }
        if (btnDayAddVit6.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 7);
            new addGoals().execute(vitaminEntity);
        }
        if (btnDayAddVit7.getTag().equals("true")) {
            VitaminEntity vitaminEntity = new VitaminEntity(UE.getId(), nameVitamin,
                    timeAddVitamin, countVitaminAdd, 1);
            new addGoals().execute(vitaminEntity);
        }
    }

    //Запускаем Асинхронный процесс добаления целей в БД
    private class addGoals extends AsyncTask<VitaminEntity, Void, Void> {
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
                rulesPriemAdapter.setArrayList(list);
                recyclerViewRules.setVisibility(View.VISIBLE);
            }else{
                recyclerViewRules.setVisibility(View.GONE);
            }
        }
    }

    //Вытаскиваем все данные из БД
    private void queryAllGoalsAsyncTask() {
        new queryAllGoals().execute();
//        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    //Запускаем Асинхронный процесс вытаскивания всех целей из БД
    private class queryAllGoals extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            arrayList = (ArrayList<VitaminEntity>) userDB.getUserDao().getAllUser();
            list = userDB.getUserDao().getDay(1);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (arrayList.size() != 0) {

                rulesPriemDialogAdapter.setArrayList(arrayList);
                rulesPriemAdapter.setArrayList(arrayList);
            }
        }
    }


    private void init() {
        constLayoutIsNullBD = v.findViewById(R.id.constLayoutIsNullBD);
        recyclerViewRules = v.findViewById(R.id.recyclerViewRules);
        dataTextView = v.findViewById(R.id.dataTextView);
        ruleBtn = v.findViewById(R.id.ruleBtn);
        fillBtn = v.findViewById(R.id.fillBtn);
        btnDay1 = v.findViewById(R.id.btnDay1);
        btnDay2 = v.findViewById(R.id.btnDay2);
        btnDay3 = v.findViewById(R.id.btnDay3);
        btnDay4 = v.findViewById(R.id.btnDay4);
        btnDay5 = v.findViewById(R.id.btnDay5);
        btnDay6 = v.findViewById(R.id.btnDay6);
        btnDay7 = v.findViewById(R.id.btnDay7);
    }
}