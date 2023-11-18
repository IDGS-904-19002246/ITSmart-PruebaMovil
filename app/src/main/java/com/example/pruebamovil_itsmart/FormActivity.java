package com.example.pruebamovil_itsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.pruebamovil_itsmart.api.api_inter;
import com.example.pruebamovil_itsmart.api.retro;
import com.example.pruebamovil_itsmart.databinding.ActivityFormBinding;
import com.example.pruebamovil_itsmart.models.ClsClientes;
import com.example.pruebamovil_itsmart.models.ClsCoordenadas;
import com.example.pruebamovil_itsmart.models.ClsUbicacion;
import com.example.pruebamovil_itsmart.models.ClsMunicipios;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//--------------------------------------
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FormActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityFormBinding b;
    public static final String CLI_KEY = "cli";
    public ClsClientes cliente;
    public List<String> list_municipios = new ArrayList<>();
    public List<String> list_estados = new ArrayList<>(Arrays.asList("Ciudad de México","Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila de Zaragoza","Colima","Chiapas","Chihuahua","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","México","Michoacán de Ocampo","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz de Ignacio de la Llave","Yucatán","Zacatecas"));
    public String str_estado, str_municipio;
    api_inter api;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        b = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Bundle extras = getIntent().getExtras();
        api = retro.getClient().create(api_inter.class);

        ArrayAdapter<String> adapterEstados = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_estados);
        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b.Estado.setAdapter(adapterEstados);
        b.Estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_estado = (String) b.Estado.getItemAtPosition(position);
                getMunicipios(str_estado);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter<String> adapterMunicipios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_municipios);
        adapterMunicipios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b.Municipio.setAdapter(adapterMunicipios);
        b.Municipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_municipio = (String) b.Municipio.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

//      --------------------------------------------------------------------------
        b.btnGuardar.setOnClickListener(v -> {
            if (b.Nombre.getText().toString().isEmpty() || b.Nombre.getText().toString().equals(" ")){
                Toast.makeText(this, "Se necesita nombre", Toast.LENGTH_SHORT).show();
            } else if (b.Telefono.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita Telefono valido", Toast.LENGTH_SHORT).show();
            } else if (b.Telefono.getText().toString().trim().length() != 10) {
                Toast.makeText(this, "Se necesita Telefono valido", Toast.LENGTH_SHORT).show();
            } else if (validarEmail(b.Correo.getText().toString().trim()) != true) {
                Toast.makeText(this, "Se necesita Correo valido", Toast.LENGTH_SHORT).show();
            }else if (b.Colonia.getText().toString().isEmpty() || b.Colonia.getText().toString().equals(" ")){
                Toast.makeText(this, "Se necesita Colonia valida", Toast.LENGTH_SHORT).show();
            }else if (b.Calle.getText().toString().isEmpty() || b.Calle.getText().toString().equals(" ")){
                Toast.makeText(this, "Se necesita Calle valida", Toast.LENGTH_SHORT).show();
            } else if (b.Latitud.getText().toString().isEmpty() || b.Longitud.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita Coordenadas validas", Toast.LENGTH_SHORT).show();
            } else if (b.Cp.getText().toString().length() !=5 ) {
                Toast.makeText(this, "Se necesita Codigo Postal valido", Toast.LENGTH_SHORT).show();
            } else {
                if (extras == null){
                    insertar(new ClsClientes(
                            0,
                            b.Nombre.getText().toString(),
                            b.Telefono.getText().toString(),
                            b.Correo.getText().toString(),
                            str_estado,
                            str_estado,
                            b.Colonia.getText().toString(),
                            b.Calle.getText().toString(),
                            b.Latitud.getText().toString(),
                            b.Longitud.getText().toString(),
                            new Date(),
                            new Date(),
                            Integer.parseInt(b.Cp.getText().toString())
                    ));
                }else{
                    ClsClientes c = new ClsClientes(
                            cliente.getId_cliente(),
                            b.Nombre.getText().toString(),
                            b.Telefono.getText().toString(),
                            b.Correo.getText().toString(),
                            str_estado,
                            str_estado,
                            b.Colonia.getText().toString(),
                            b.Calle.getText().toString(),
                            b.Latitud.getText().toString(),
                            b.Longitud.getText().toString(),
                            new Date(),
                            new Date(),
                            Integer.parseInt(b.Cp.getText().toString()
                            ));
                    actualizar(c, cliente.getId_cliente());
                }
            }
        });


        if (extras != null){
            cliente = extras.getParcelable(CLI_KEY);

            b.Nombre.setText(cliente.getNombre());
            b.Telefono.setText(String.valueOf(cliente.getTelefono()));
            b.Correo.setText(cliente.getEmail());
            b.Estado.setSelection(list_estados.indexOf(cliente.getEstado()));
            b.Colonia.setText(cliente.getColonia());    
            b.Calle.setText(cliente.getCalle());
            b.Longitud.setText(String.valueOf(cliente.getLongitud()));
            b.Latitud.setText(String.valueOf(cliente.getLatitud()));
            b.Cp.setText(String.valueOf(cliente.getCp()));

            getMunicipios(cliente.getEstado());
            b.Municipio.setSelection(list_municipios.indexOf(cliente.getMunicipio()));


            SupportMapFragment mapFragment = new SupportMapFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mapContainer, mapFragment).commit();
            mapFragment.getMapAsync(googleMap -> {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.getUiSettings().setZoomControlsEnabled(true);

                LatLng ubicacion = new LatLng(
                        Double.parseDouble(cliente.getLatitud()),
                        Double.parseDouble(cliente.getLongitud()));
                MarkerOptions markerOptions = new MarkerOptions().position(ubicacion).title("Ubicacion");
                Marker marker = googleMap.addMarker(markerOptions);

                // Mover la cámara y hacer zoom al marcador
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 12.0f));
            });
        }else{
            getMunicipios("Ciudad de México");
            SupportMapFragment mapFragment = new SupportMapFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mapContainer, mapFragment).commit();
            mapFragment.getMapAsync(googleMap -> {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.getUiSettings().setZoomControlsEnabled(true);

                LatLng ubicacion = new LatLng(0, 0); // Latitud y longitud de ejemplo
                MarkerOptions markerOptions = new MarkerOptions().position(ubicacion).title("Marcador de Ejemplo");
                Marker marker = googleMap.addMarker(markerOptions);

                // Mover la cámara y hacer zoom al marcador
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 12.0f));
            });
        }

        b.Colonia.addTextChangedListener(new MyTextWatcher());
        b.Calle.addTextChangedListener(new MyTextWatcher());
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
                if(response.body() == null){
                    list_municipios = new ArrayList<>(Arrays.asList("Hermosillo","Sonora","Celaya","Hermosillo","Leon"));
                }else{
                    list_municipios = response.body().municipios;
                }
                ArrayAdapter<String> adapterMunicipios = new ArrayAdapter<>(FormActivity.this, android.R.layout.simple_spinner_item, list_municipios);
                adapterMunicipios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                b.Municipio.setAdapter(adapterMunicipios);
            }
            @Override
            public void onFailure(Call<ClsMunicipios> call, Throwable t) {}
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_municipios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b.Municipio.setAdapter(adapter);
    }

    public void insertar( ClsClientes clsClientes){
        Call<String> call = api.INSERTAR(clsClientes);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {}
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
        Toast.makeText(FormActivity.this, "Cliente Añadido", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void actualizar(ClsClientes clsClientes, int id){
        Call<String> call = api.EDITAR(id, clsClientes);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {}
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
        Toast.makeText(FormActivity.this, "Cliente Actualizado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            mMap = googleMap;
        }
    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {

            String Col = b.Colonia.getText().toString();
            String Cal = b.Calle.getText().toString();
            if (!Col.isEmpty() && !Cal.isEmpty() && !str_estado.isEmpty() && !str_municipio.isEmpty()) {
                Call<ClsCoordenadas> call = api.COORDENADAS(new ClsUbicacion(str_estado,str_municipio,Col,Cal));
                call.enqueue(new Callback<ClsCoordenadas>() {
                    @Override
                    public void onResponse(Call<ClsCoordenadas> call, Response<ClsCoordenadas> response) {
                        b.Latitud.setText(String.valueOf(response.body().latitud));
                        b.Longitud.setText(String.valueOf(response.body().longitud));

                        SupportMapFragment mapFragment = new SupportMapFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.mapContainer, mapFragment).commit();
                        mapFragment.getMapAsync(googleMap -> {
                            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            googleMap.getUiSettings().setZoomControlsEnabled(true);

                            LatLng ubicacion = new LatLng(
                                    Double.parseDouble(String.valueOf(response.body().latitud)),
                                    Double.parseDouble(String.valueOf(response.body().longitud)));
                            MarkerOptions markerOptions = new MarkerOptions().position(ubicacion).title("Ubicacion");
                            Marker marker = googleMap.addMarker(markerOptions);

                            // Mover la cámara y hacer zoom al marcador
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 12.0f));
                        });
                    }
                    @Override
                    public void onFailure(Call<ClsCoordenadas> call, Throwable t) {}
                });
            }
        }
    }
}