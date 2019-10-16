package com.example.registerloginexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import static com.example.registerloginexample.Value.CODE_GALLERY_REQUEST;
import static com.example.registerloginexample.Value.RESULT_EXPLANATION;
import static com.example.registerloginexample.Value.RESULT_GPS;
import static com.example.registerloginexample.Value.RESULT_SELECT;


public class RegisterItem extends AppCompatActivity {

    Button btn_back, btn_kind, btn_explain, btn_GPS;
    ImageView btn_img_select;
    TextView kind_result, GPS_result, btn_upload, tv;
    String Explanation,base_url;
    EditText et_price,et_title;
    Bitmap bitamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_item);
        btn_upload = findViewById(R.id.btn_upload);
        et_price = findViewById(R.id.et_price);
        et_title = findViewById(R.id.et_title);
        btn_img_select = findViewById(R.id.btn_img_select);
        btn_back = findViewById(R.id.btn_back);
        btn_kind = findViewById(R.id.btn_kind);
        btn_explain = findViewById(R.id.btn_explain);
        kind_result = findViewById(R.id.kind_result);
        GPS_result = findViewById(R.id.GPS_result);
        btn_GPS = findViewById(R.id.btn_GPS);

        tv = findViewById(R.id.tv);

        base_url= "http://eqrw105.cafe24.com/seongminserver/upload.php";


        btn_img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RegisterItem.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = et_title.getText().toString();
                final String kat = kind_result.getText().toString();
                final String explanation = Explanation;
                final String pos = GPS_result.getText().toString();
                final Intent intent = getIntent();
                final String userID = intent.getStringExtra("userID");
                final int price = Integer.parseInt(et_price.getText().toString());


                //POST
                StringRequest stringRequest = new StringRequest(Request.Method.POST,base_url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "등록 성공", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new Hashtable<>();
                        String imageData = imageToString(bitamp);
                        params.put("image",imageData);
                        params.put("userID",userID);
                        params.put("pos",pos);
                        params.put("kat",kat);
                        params.put("title",title);
                        params.put("price",price+"");
                        params.put("explanation",explanation);
                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterItem.this);
                requestQueue.add(stringRequest);


            }
        });



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_kind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterItem.this, MenuSelect.class);
                startActivityForResult(intent, RESULT_SELECT);
            }
        });

        btn_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterItem.this, ItemExplanation.class);
                intent.putExtra("Write_Explanation", Explanation);
                startActivityForResult(intent, RESULT_EXPLANATION);

            }
        });
        btn_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterItem.this, GoogleMapView.class);
                startActivityForResult(intent, RESULT_GPS);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_GALLERY_REQUEST&& data!= null) {
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitamp = BitmapFactory.decodeStream(inputStream);
                btn_img_select.setImageBitmap(bitamp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        switch (requestCode) {
            case RESULT_SELECT: {
                String resultMsg = data.getStringExtra("result");

                kind_result.setText(resultMsg);
                kind_result.setVisibility(View.VISIBLE);

                return;
            }
            case RESULT_EXPLANATION: {
                Explanation = data.getStringExtra("Explanation");
            }
            case RESULT_GPS: {
                String resultgps = data.getStringExtra("resultgps");
                GPS_result.setText(resultgps);
                GPS_result.setVisibility(View.VISIBLE);
            }



        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CODE_GALLERY_REQUEST){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),CODE_GALLERY_REQUEST);
            }
            else {
                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);


    }
private String imageToString(Bitmap bitmap){
   ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
   byte[] imageBytes = outputStream.toByteArray();

   String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
   return encodedImage;
}

}