package com.example.projectnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    SupportMapFragment mapFragment;
    Spinner sp_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addControls();
        addEvents();
    }

    private void addEvents(){
        sp_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 1:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    case 2:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 3:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void addControls(){
        sp_spinner = findViewById(R.id.spinner);

        ArrayList<String> ds_StyleMap = new ArrayList<>();
        ds_StyleMap.add("Style 1");
        ds_StyleMap.add("Style 2");
        ds_StyleMap.add("Style 3");
        ds_StyleMap.add("Style 4");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,ds_StyleMap);
        sp_spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng latLng1 = new LatLng(20.988351048955234, 105.75044660681235);
        LatLng latLng2 = new LatLng(20.988351048955234, 105.75044660681235);
        LatLng latLng3 = new LatLng(20.988351048955234, 105.75044660681235);
        LatLng latLng4 = new LatLng(20.988351048955234, 105.75044660681235);
        LatLng latLng5 = new LatLng(20.988351048955234, 105.75044660681235);

        PolygonOptions polygonOptions = new PolygonOptions()
                .add(latLng1).add(latLng2).add(latLng3).add(latLng4).add(latLng5).add(latLng1)
                .strokeColor(Color.RED)
                .fillColor(Color.YELLOW)
                .strokeWidth(20);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1,15));
    }
}