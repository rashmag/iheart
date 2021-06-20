package com.example.iherb.Anketa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iherb.MainActivity;
import com.example.iherb.R;

public class ResultAnaliz extends AppCompatActivity {
    TextView resultAnswer1,resultAnswer2,resultAnswer3,resultAnswer4,resultAnswer5,resultAnswer6;
    Double resultAnswerStr1,resultAnswerStr2,resultAnswerStr3,resultAnswerStr4,resultAnswerStr5,resultAnswerStr6;
    String test;
    AppCompatButton sendAnaliz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_analiz);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        init();

        Intent intent = getIntent();
        resultAnswerStr1 = Double.parseDouble(intent.getStringExtra("answer1"));
        resultAnswerStr2 = Double.parseDouble(intent.getStringExtra("answer2"));
        resultAnswerStr3 = Double.parseDouble(intent.getStringExtra("answer3"));
        resultAnswerStr4 = Double.parseDouble(intent.getStringExtra("answer4"));
        resultAnswerStr5 = Double.parseDouble(intent.getStringExtra("answer5"));
        resultAnswerStr6 = Double.parseDouble(intent.getStringExtra("answer6"));

        sendAnaliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultAnaliz.this, MainActivity.class));
            }
        });
            //Витамин D
            if(resultAnswerStr1 >= 100){
                resultAnswer1.setText("Избыток");
            }else if(resultAnswerStr1 >= 30 && resultAnswerStr1 < 100){
                resultAnswer1.setText("Норма");
            }else if(resultAnswerStr1 >= 25 && resultAnswerStr1 < 30){
                resultAnswer1.setText("Пить Витамин Б 1 неделю");
            }else if(resultAnswerStr1 >= 20 && resultAnswerStr1 < 25){
                resultAnswer1.setText("Пить Витамин Б 2 недели");
            }else if(resultAnswerStr1 >= 15 && resultAnswerStr1 < 20){
                resultAnswer1.setText("Пить Витамин Б 3 недели");
            }else if(resultAnswerStr1 < 15){
                resultAnswer1.setText("Пить Витамин Б 4 недели");
            }

            //Цинк
            if(resultAnswerStr2 >= 1130){
                resultAnswer2.setText("Избыток");
            }else if(resultAnswerStr2 >= 543 && resultAnswerStr2 < 1130){
                resultAnswer2.setText("Норма");
            }else if(resultAnswerStr2 >= 490 && resultAnswerStr2 < 543){
                resultAnswer2.setText("Пить Цинк 1 неделю");
            }else if(resultAnswerStr2 >= 430 && resultAnswerStr2 < 490){
                resultAnswer2.setText("Пить Цинк 2 недели");
            }else if(resultAnswerStr2 >= 380 && resultAnswerStr2 < 430){
                resultAnswer2.setText("Пить Цинк 3 недели");
            }else if(resultAnswerStr2 < 380){
                resultAnswer2.setText("Пить Цинк 4 недели");
            }
            //Витамин С
            if(resultAnswerStr3 >= 17.95){
                resultAnswer3.setText("Избыток");
            }else if(resultAnswerStr3 >= 1.05 && resultAnswerStr3 < 17.95){
                resultAnswer3.setText("Норма");
            }else if(resultAnswerStr3 >= 0.9 && resultAnswerStr3 < 1.05){
                resultAnswer3.setText("Пить Витамин С 1 неделю");
            }else if(resultAnswerStr3 >= 0.8 && resultAnswerStr3 < 0.9){
                resultAnswer3.setText("Пить Витамин С 2 недели");
            }else if(resultAnswerStr3 >= 0.7 && resultAnswerStr3 < 0.8){
                resultAnswer3.setText("Пить Витамин С 3 недели");
            }else if(resultAnswerStr3 < 0.7){
                resultAnswer3.setText("Пить Витамин С 4 недели");
            }
            //Железо
            if(resultAnswerStr6 >= 28){
                resultAnswer6.setText("Избыток");
            }else if(resultAnswerStr6 >= 11 && resultAnswerStr6 < 28){
                resultAnswer6.setText("Норма");
            }else if(resultAnswerStr6 >= 9 && resultAnswerStr6 < 11){
                resultAnswer6.setText("Пить Железо 1 неделю");
            }else if(resultAnswerStr6 >= 7 && resultAnswerStr6 < 9){
                resultAnswer6.setText("Пить Железо 2 недели");
            }else if(resultAnswerStr6 >= 6 && resultAnswerStr6 < 7){
                resultAnswer6.setText("Пить Железо 3 недели");
            }else if(resultAnswerStr6 < 6){
                resultAnswer6.setText("Пить Железо 4 недели");
            }

        //Кальций
        if(resultAnswerStr5 >= 2.5){
            resultAnswer5.setText("Избыток");
        }else if(resultAnswerStr5 >= 2.15 && resultAnswerStr5 < 2.5){
            resultAnswer5.setText("Норма");
        }else if(resultAnswerStr5 >= 1.8 && resultAnswerStr5 < 2.15){
            resultAnswer5.setText("Пить Витамин С 1 неделю");
        }else if(resultAnswerStr5 < 1.8){
            resultAnswer5.setText("Пить Витамин С 4 недели");
        }
        //Магний
        if(resultAnswerStr4 > 1.07){
            resultAnswer4.setText("Кальций повышен");
        }else if(resultAnswerStr4 >= 0.66 && resultAnswerStr4 <= 1.7){
            resultAnswer4.setText("Норма");
        }else if(resultAnswerStr4 >= 0.5 && resultAnswerStr4 <= 0.66){
            resultAnswer4.setText("Пить магний 2 недели");
        }else if(resultAnswerStr4 >= 0.4 && resultAnswerStr4 <= 0.5){
            resultAnswer4.setText("Пить магний 3 недели");
        }else if(resultAnswerStr4 < 0.4){
            resultAnswer4.setText("Пить магний 4 недели");
        }
    }

    private void init(){
        sendAnaliz = findViewById(R.id.sendAnaliz);
        resultAnswer1 = findViewById(R.id.resultAnswer1);
        resultAnswer2 = findViewById(R.id.resultAnswer2);
        resultAnswer3 = findViewById(R.id.resultAnswer3);
        resultAnswer4 = findViewById(R.id.resultAnswer4);
        resultAnswer5 = findViewById(R.id.resultAnswer5);
        resultAnswer6 = findViewById(R.id.resultAnswer6);
    }
}