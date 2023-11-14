package com.example.pruebamovil_itsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.pruebamovil_itsmart.api.api_inter;
import com.example.pruebamovil_itsmart.api.retro;
import com.example.pruebamovil_itsmart.databinding.ActivityFormBinding;
import com.example.pruebamovil_itsmart.models.ClsClientes;
import com.example.pruebamovil_itsmart.models.ClsMunicipios;
import com.example.pruebamovil_itsmart.models.ClsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding b;
    public static final String CLI_KEY = "cli";
    public List<String> list_municipios = new ArrayList<>();
    public List<String> list_estados = new ArrayList<>(Arrays.asList("Ciudad de México","Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila de Zaragoza","Colima","Chiapas","Chihuahua","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","México","Michoacán de Ocampo","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz de Ignacio de la Llave","Yucatán","Zacatecas"));
    api_inter api;
//    private GoogleMap mMap;
//    private static final LatLng MI_UBICACION = new LatLng(-34, 151);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        b = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Bundle extras = getIntent().getExtras();
        api = retro.getClient().create(api_inter.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b.Estado.setAdapter(adapter);

        b.btnGuardar.setOnClickListener(v -> {
            if (b.Nombre.getText().equals("") || b.Nombre.getText().equals(" ")){
                Toast.makeText(this, "Se necesita nombre", Toast.LENGTH_SHORT).show();
            } else if (validarTelefono(Integer.parseInt(b.Telefono.getText().toString())) != true) {
                Toast.makeText(this, "Se necesita Telefono valido", Toast.LENGTH_SHORT).show();
            } else if (validarEmail(b.Correo.getText().toString().trim()) != true) {
                Toast.makeText(this, "Se necesita Correo valido", Toast.LENGTH_SHORT).show();
            }else if (b.Colonia.getText().equals("") || b.Colonia.getText().equals(" ")){
                Toast.makeText(this, "Se necesita Colonia valida", Toast.LENGTH_SHORT).show();
            }else if (b.Calle.getText().equals("") || b.Calle.getText().equals(" ")){
                Toast.makeText(this, "Se necesita Calle valida", Toast.LENGTH_SHORT).show();
            } else if (b.Latitud.getText().toString().isEmpty() || b.Longitud.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita Coordenadas validas", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Datos validos", Toast.LENGTH_SHORT).show();

            }
        });

        if (extras == null){
            Toast.makeText(this, "NO Hay cliente", Toast.LENGTH_SHORT).show();
        }else {

            ClsClientes cliente = extras.getParcelable(CLI_KEY);

            Toast.makeText(this, "Hay cliente"+cliente.getEstado(), Toast.LENGTH_SHORT).show();
//            b.IdCliente.setText(cliente.getId_cliente());
            b.Nombre.setText(cliente.getNombre());
            b.Telefono.setText(cliente.getTelefono());
            b.Correo.setText(cliente.getEmail());
            b.Estado.setSelection(list_estados.indexOf(cliente.getEstado()));
            b.Colonia.setText(cliente.getColonia());
            b.Calle.setText(cliente.getCalle());
            b.Longitud.setText(String.valueOf(cliente.getLongitud()));
            b.Latitud.setText( String.valueOf(cliente.getLatitud()));
//            getMunicipios(cliente.getEstado());

//            b.Municipio.setSelection(list_municipios.indexOf(cliente.getMunicipio()));
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


    private boolean validarTelefono(int telefono) {
        String str_telefono = String.valueOf(telefono).trim();
        if (str_telefono.isEmpty()) {
            return false;
        } else if (str_telefono.length() != 10){
            return false;
        } else if (telefono <=0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean validarEmail(String email) {
        if (email.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        } else {
            return true;
        }
    }
    public void getMunicipios(String E){
        Call<ClsMunicipios> call_mun = api.MUNICIPIOS(E);
        call_mun.enqueue(new Callback<ClsMunicipios>() {
            @Override
            public void onResponse(Call<ClsMunicipios> call, Response<ClsMunicipios> response) {
                list_municipios = response.body().municipios;
            }
            @Override
            public void onFailure(Call<ClsMunicipios> call, Throwable t) {}
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_municipios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b.Municipio.setAdapter(adapter);
    }

}