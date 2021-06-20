package com.example.iherb.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iherb.Adapter.ShoppingAdapter;
import com.example.iherb.Item.ShoppingModel;
import com.example.iherb.MainActivity;
import com.example.iherb.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ShoppingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerViewShop;
    private ProgressBar progressBar;
    private String mParam1;
    private View v;
    MainActivity mainActivity;
    private String mParam2;
    private ArrayList<ShoppingModel> shoppingModels = new ArrayList<>();
    ShoppingAdapter shoppingAdapter;

    public ShoppingFragment() {
    }

    public static ShoppingFragment newInstance(String param1, String param2) {
        ShoppingFragment fragment = new ShoppingFragment();
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
        v = inflater.inflate(R.layout.fragment_shopping, container, false);
        init();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewShop.setLayoutManager(linearLayoutManager);
        recyclerViewShop.setHasFixedSize(true);
        mainActivity = (MainActivity)getActivity();
        shoppingAdapter = new ShoppingAdapter(shoppingModels,mainActivity);
        recyclerViewShop.setAdapter(shoppingAdapter);

        Content content = new Content();
        content.execute();
        return v;
    }

    private void init() {
        progressBar = v.findViewById(R.id.progressBarShop);
        recyclerViewShop = v.findViewById(R.id.recyclerViewShop);
    }

    private class Content extends AsyncTask<Void,Void,Void> {
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
            shoppingAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //Парсим категорию "Важно знать"
                String urlMainKnow = "https://ru.iherb.com/c/supplements";
                Document docMainKnow = Jsoup.connect(urlMainKnow).get();
                Elements dataMainKnow = docMainKnow.select("div.product-title");
                Elements dataMainKnow2 = docMainKnow.select("div.product-price");
                Elements dataMainKnow3 = docMainKnow.select("span.product-image");
                for(int i = 0; i < dataMainKnow.size(); i++){
                    String imgUrl = dataMainKnow3
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String price = dataMainKnow2
                            .select("span.price")
                            .eq(i)
                            .text();
                    //Важно знать
                    String nameUrl = dataMainKnow
                            .select("bdi")
                            .eq(i)
                            .text();
                    Log.d("testtest","price = " + price + " nameUrl =" +
                            nameUrl + " imgUrl = " + imgUrl);
                    if(!imgUrl.equals("")) {
                        Log.d("testtest","i = " + i) ;
                        shoppingModels.add(new ShoppingModel(imgUrl, price, nameUrl));
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}