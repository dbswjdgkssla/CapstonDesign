package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static com.example.registerloginexample.Value.RESULT_EXPLANATION;


public class ItemExplanation extends AppCompatActivity {
Button btn_back,btn_send;
EditText ed_explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_explanation);

        btn_back = findViewById(R.id.btn_back);
        btn_send = findViewById(R.id.btn_send);
        ed_explanation = findViewById(R.id.ed_explanation);
        Intent intent = getIntent();
        String saveExplanation = intent.getStringExtra("Write_Explanation");
        ed_explanation.setText(saveExplanation);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String ment = ed_explanation.getText().toString();
                    intt(ment);
                }
        });


    }
    public void intt(String name)
    {

        Intent intent = new Intent();
        intent.putExtra("Explanation",name);
        setResult(RESULT_EXPLANATION,intent);
        finish();
    }
}
