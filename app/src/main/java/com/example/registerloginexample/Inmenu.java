package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.TextView;

public class Inmenu extends AppCompatActivity {
    TextView TxTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmenu);
        TxTitle = findViewById(R.id.TxTitle);

        Intent intent = getIntent();
        //String userID = intent.getStringExtra("userID");
        String ment = intent.getStringExtra("ment");

        TxTitle.setText(ment);
    }
}
