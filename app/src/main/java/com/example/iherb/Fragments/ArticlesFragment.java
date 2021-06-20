package com.example.iherb.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.iherb.Activity.MakingTestsActivity;
import com.example.iherb.Activity.PassTestActivity;
import com.example.iherb.Adapter.ArticlesAdapter;
import com.example.iherb.Item.ParceModel;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ArticlesFragment extends Fragment {
    private TextView nameUser;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GregorianCalendar date;
    private String mParam1;
    private String mParam2;
    private ArrayList<ParceModel> parceModels;
    private RecyclerView recyclerView;
    private View view;
    private ArticlesAdapter articlesAdapter;
    private ProgressBar progressBar;
    AppCompatButton stepBtn,passTestBtn,makeTestsBtn;

    public ArticlesFragment() {
    }
    public static ArticlesFragment newInstance(String param1, String param2) {
        ArticlesFragment fragment = new ArticlesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //Чекаем вид приветствия пользователя по относительно времени
    private void timeHello() {
        date = new GregorianCalendar();

        int hours = date.get(Calendar.HOUR_OF_DAY); //date.getHours();
        int min = date.get(Calendar.MINUTE); //date.getMinutes();
        int sec = date.get(Calendar.SECOND); //date.getSeconds();
        //Утро
        GregorianCalendar morningStart = new GregorianCalendar();
        morningStart.set(Calendar.HOUR_OF_DAY, 3);
        morningStart.set(Calendar.MINUTE, 59);

        GregorianCalendar morningEnd = new GregorianCalendar();
        morningEnd.set(Calendar.HOUR_OF_DAY, 12);
        morningEnd.set(Calendar.MINUTE, 0);
        morningEnd.set(Calendar.SECOND, 0);

        //День
        GregorianCalendar dayStart = new GregorianCalendar();
        dayStart.set(Calendar.HOUR_OF_DAY, 11);
        dayStart.set(Calendar.MINUTE, 59);

        GregorianCalendar dayEnd = new GregorianCalendar();
        dayEnd.set(Calendar.HOUR_OF_DAY, 18);
        dayEnd.set(Calendar.MINUTE, 0);
        dayEnd.set(Calendar.SECOND, 0);

        //Вечер
        GregorianCalendar eveningStart = new GregorianCalendar();
        eveningStart.set(Calendar.HOUR_OF_DAY, 17);
        eveningStart.set(Calendar.MINUTE, 59);

        GregorianCalendar eveningEnd = new GregorianCalendar();
        eveningEnd.set(Calendar.HOUR_OF_DAY, 24);
        eveningEnd.set(Calendar.MINUTE, 0);
        eveningEnd.set(Calendar.SECOND, 0);
        //Ночь
        GregorianCalendar nightStart = new GregorianCalendar();
        nightStart.set(Calendar.HOUR_OF_DAY, 0);
        nightStart.set(Calendar.MINUTE, 0);

        GregorianCalendar nightEnd = new GregorianCalendar();
        nightEnd.set(Calendar.HOUR_OF_DAY, 4);
        nightEnd.set(Calendar.MINUTE, 0);
        nightEnd.set(Calendar.SECOND, 0);
//        myNameStr = sharedPreferencesUserFragment.getString("name", "");
//        if (myNameStr.length() > 0) {
//            if (date.after(morningStart) & date.before(morningEnd)) {
//                nameUser.setText("Доброе утро, " + myNameStr + "!");
//            } else if (date.after(dayStart) & date.before(dayEnd)) {
//                nameUser.setText("Добрый день, " + myNameStr + "!");
//            } else if (date.after(eveningStart) & date.before(eveningEnd)) {
//                nameUser.setText("Добрый вечер, " + myNameStr + "!");
//            } else if (date.after(nightStart) & date.before(nightEnd)) {
//                nameUser.setText("Доброй ночи, " + myNameStr + "!");
//            } else {
//                nameUser.setText("Доброй ночи, " + myNameStr + "!");
//            }
//        } else {
            if (date.after(morningStart) & date.before(morningEnd)) {
                nameUser.setText("Доброе утро!");
            } else if (date.after(dayStart) & date.before(dayEnd)) {
                nameUser.setText("Добрый день!");
            } else if (date.after(eveningStart) & date.before(eveningEnd)) {
                nameUser.setText("Добрый вечер!");
            } else if (date.after(nightStart) & date.before(nightEnd)) {
                nameUser.setText("Доброй ночи!");
            } else {
                nameUser.setText("Доброй ночи!");
            }
//        }


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
        view = inflater.inflate(R.layout.fragment_articles, container, false);
        init();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        parceModels = new ArrayList<>();
        MainActivity mainActivity = (MainActivity)getActivity();
        articlesAdapter = new ArticlesAdapter(parceModels,mainActivity);
        recyclerView.setAdapter(articlesAdapter);
        //Приветствие пользователя
        timeHello();
        Content content = new Content();
        content.execute();
//        recyclerView.setNestedScrollingEnabled(false);
        stepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.stepFragment();
            }
        });
        passTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PassTestActivity.class));
            }
        });

        makeTestsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MakingTestsActivity.class));
            }
        });
        return view;
    }

    private class Content extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                    android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            articlesAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //Парсим категорию "Важно знать"
                String urlMainKnow = "https://ru.iherb.com/blog/";
                Document docMainKnow = Jsoup.connect(urlMainKnow).get();
                Elements dataMainKnow = docMainKnow.select("div.article-preview-container");

                for(int i = 0; i < 3; i++){
                    //Важно знать
                    String titleUrl = dataMainKnow
                            .select("div.content")
                            .select("h5")
                            .eq(i)
                            .text();

                    String descriptionUrl = dataMainKnow
                            .select("div.content")
                            .select("h3")
                            .eq(i)
                            .text();
                    parceModels.add(new ParceModel(titleUrl,descriptionUrl));
                    Log.d("testUrl", "titleUrl = " + titleUrl + " descriptionUrl = " +
                            descriptionUrl);
                }

                //Парсим категории
                String urlMain = "https://ru.iherb.com/blog/";
                Document docMain = Jsoup.connect(urlMain).get();
                Elements dataMain = docMain.select("ul.nav").eq(0).select("li");
                int sizeAll = dataMain.size();
                Log.d("size1", dataMain.size()+" size");
                for(int i = 0; i < sizeAll; i++){
                    String srcCatigory = dataMain
                            .select("a")
                            .eq(i)
                            .attr("href");

                    String url = "https://ru.iherb.com"+srcCatigory;
                    Document doc = Jsoup.connect(url).get();
                    Elements data = doc.select("div.article-preview-container");

                    for(int i1 = 0; i1 < 3; i1++) {
                        String titleUrl = data
                                .select("div.content")
                                .select("h5")
                                .eq(i1)
                                .text();

                        String descriptionUrl = data
                                .select("div.content")
                                .select("h3")
                                .eq(i1)
                                .text();
                        parceModels.add(new ParceModel(titleUrl, descriptionUrl));
                    }

                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    private void init(){
        stepBtn = view.findViewById(R.id.stepBtn);
        passTestBtn = view.findViewById(R.id.passTestBtn);
        makeTestsBtn = view.findViewById(R.id.makeTestsBtn);
        nameUser = view.findViewById(R.id.nameUser);
        recyclerView = view.findViewById(R.id.recyclerViewArticles);
        progressBar = view.findViewById(R.id.progressBarArticles);
    }
}