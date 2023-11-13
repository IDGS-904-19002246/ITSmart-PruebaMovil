package com.example.pruebamovil_itsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;


import com.example.pruebamovil_itsmart.databinding.ActivityFormBinding;
import com.example.pruebamovil_itsmart.models.ClsClientes;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding b;
    public static final String CLI_KEY = "cli";
//    private GoogleMap mMap;
//    private static final LatLng MI_UBICACION = new LatLng(-34, 151);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        b = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Bundle extras = getIntent().getExtras();

        if (extras == null){
            Toast.makeText(this, "NO Hay cliente", Toast.LENGTH_SHORT).show();
        }else {
            ClsClientes cliente = extras.getParcelable(CLI_KEY);
            Toast.makeText(this, "Hay cliente", Toast.LENGTH_SHORT).show();
        }

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
//                findFragmentById(R.id.mapFragment);
//
//        mapFragment.getMapAsync((OnMapReadyCallback) FormActivity.this);
    }


//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Añadir marcador en la ubicación y mover la cámara
//        Marker marker = mMap.addMarker(new MarkerOptions().position(MI_UBICACION).title("Mi Ubicación"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(MI_UBICACION));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MI_UBICACION, 12.0f)); // Ajusta el nivel de zoom según tus necesidades
//    }
}