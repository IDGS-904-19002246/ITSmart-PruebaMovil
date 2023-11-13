package com.example.pruebamovil_itsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.pruebamovil_itsmart.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding b;
    private GoogleMap mMap;
    private static final LatLng MI_UBICACION = new LatLng(-34, 151);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        b = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
//                findFragmentById(R.id.mapFragment);
//
//        mapFragment.getMapAsync((OnMapReadyCallback) FormActivity.this);
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Añadir marcador en la ubicación y mover la cámara
        Marker marker = mMap.addMarker(new MarkerOptions().position(MI_UBICACION).title("Mi Ubicación"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MI_UBICACION));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MI_UBICACION, 12.0f)); // Ajusta el nivel de zoom según tus necesidades
    }
}