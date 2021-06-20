package com.example.iherb.Anketa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.iherb.CustomSpinner.CustomAdapter;
import com.example.iherb.R;

import java.util.ArrayList;
import java.util.List;

public class AnketaActivity extends AppCompatActivity {

    private Spinner spinner1,spinner2,spinner3;
    private CustomAdapter adapterDirection;
    private Integer elementSpinnerFacultetInt;
    String elementSpinnerFacultet;
    private Spinner spinnerDirection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anketa);

        init();

        final List<String> listFacultet = new ArrayList<String>() {{
            add("Информатики и 1");
            add("Информатики и 2");
            add("Информатики и 3");
        }};
        //1
        adapterDirection = new CustomAdapter(this, R.layout.item_spinner_title, R.layout.item_spinner_dropdown, listFacultet);
        spinner1.setAdapter(adapterDirection);
        //2
        adapterDirection = new CustomAdapter(this, R.layout.item_spinner_title, R.layout.item_spinner_dropdown, listFacultet);
        spinner2.setAdapter(adapterDirection);
        //3
        adapterDirection = new CustomAdapter(this, R.layout.item_spinner_title, R.layout.item_spinner_dropdown, listFacultet);
        spinner3.setAdapter(adapterDirection);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                elementSpinnerFacultet = spinner1.getSelectedItem().toString();
                elementSpinnerFacultetInt = selectedItemPosition;
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void init() {
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
    }
}