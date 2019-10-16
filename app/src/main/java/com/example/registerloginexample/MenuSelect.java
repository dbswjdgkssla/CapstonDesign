package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.registerloginexample.Value.RESULT_SELECT;

public class MenuSelect extends AppCompatActivity {
    Button btn_pc,btn_bread,btn_suit,btn_house,btn_engineering,btn_sport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_select);
        btn_bread = findViewById(R.id.btn_bread);
        btn_pc = findViewById(R.id.btn_pcroom);
        btn_sport = findViewById(R.id.btn_sport);
        btn_engineering = findViewById(R.id.btn_engineering);
        btn_suit = findViewById(R.id.btn_suit);
        btn_house = findViewById(R.id.btn_house);


        btn_bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intt("음식");

            }
        });

        btn_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intt("PC방");
            }
        });

        btn_engineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intt("전자제품");
            }
        });

        btn_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intt("스포츠");
            }
        });

        btn_suit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intt("의류");
            }
        });

        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intt("숙박");
            }
        });



    }
    public void intt(String name)
    {

        Intent intent = new Intent();
        intent.putExtra("result",name);
        setResult(RESULT_SELECT,intent);
        finish();
    }

}
