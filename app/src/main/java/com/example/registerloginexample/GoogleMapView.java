package com.example.registerloginexample;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static com.example.registerloginexample.Value.RESULT_GPS;


public class GoogleMapView extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;

EditText edt_add;
Button add_ok,btn_save;
Double w;
Double h;
    LatLng SEOUL = new LatLng(157.4,126.97);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_view);




        edt_add = findViewById(R.id.edt_add);
        add_ok = findViewById(R.id.btn_ok);
        btn_save = findViewById(R.id.btn_save);

        add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Location aa=findGeoPoint(GoogleMapView.this,edt_add.getText().toString());
                w = aa.getLatitude();
                h = aa.getLongitude();
                SEOUL = new LatLng(w,h);
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(GoogleMapView.this);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("resultgps",edt_add.getText().toString());
                setResult(RESULT_GPS,intent);
                finish();
            }
        });


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;



        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("입력 위치");
        mMap.addMarker(markerOptions);
        mMap.setMinZoomPreference(16);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(address, 5);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);

            }
        }
        return loc;
    }

}