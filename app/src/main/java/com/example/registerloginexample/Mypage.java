package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Mypage extends AppCompatActivity {
    Button btn_back;
    TextView userid,userpw,username,userage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        userid = findViewById(R.id.userid);
        userpw = findViewById(R.id.userpw);
        username = findViewById(R.id.username);
        userage = findViewById(R.id.userage);
        btn_back = findViewById(R.id.btn_back);
        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");


        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("1");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        String userID = jsonObject.getString("userID");
                        String userPassword = jsonObject.getString("userPassword");
                        String userName = jsonObject.getString("userName");
                        String userAge = jsonObject.getString("userAge");
                        //                   String userPW = jsonObject.getString("userPassword");
                        userid.setText(userID);
                        userpw.setText(userPassword);
                        username.setText(userName);
                        userage.setText(userAge);

                    }else{
                        Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        System.out.println("2");
        MypageRequest mypageRequest = new MypageRequest(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Mypage.this);
        queue.add(mypageRequest);



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });









    }
}
