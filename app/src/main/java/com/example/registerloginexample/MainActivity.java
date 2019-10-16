package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// 깃허브 테스트 중입니다.
public class MainActivity extends AppCompatActivity {
Button btn_item_upload, btn_mypage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_mypage = findViewById(R.id.btn_mypage);
btn_item_upload = findViewById(R.id.btn_item_upload);
        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Mypage.class);
                intent.putExtra("userID",userID);
                startActivity(intent);

            }
        });

btn_item_upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        OnButtonClick(RegisterItem.class,userID);
    }
});

    }




    private void OnButtonClick(Class A,String userID){
        Intent intent = new Intent(MainActivity.this,A);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }
}
