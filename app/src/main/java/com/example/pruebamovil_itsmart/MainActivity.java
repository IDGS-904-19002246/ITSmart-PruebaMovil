package com.example.pruebamovil_itsmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


import com.example.pruebamovil_itsmart.api.api_inter;
import com.example.pruebamovil_itsmart.api.retro;
import com.example.pruebamovil_itsmart.databinding.ActivityMainBinding;

import com.example.pruebamovil_itsmart.adaptadors.AdaClientes;
import com.example.pruebamovil_itsmart.models.ClsClientes;
import com.example.pruebamovil_itsmart.models.ClsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding b;
//    private RecyclerView recycler;
    //    --------------------------------------------------------------------
    private List<ClsClientes> lista;
    private AdaClientes adaptador;
    static int id = 0;
    api_inter api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        lista = new ArrayList<>();
//        datos_basura();

        b.ADD.setOnClickListener(v -> {
            id = 0;
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        });
        //    --------------------------------------------------------------------

        api = retro.getClient().create(api_inter.class);
        Call<ClsResponse> call = api.TODOS();


        adaptador = new AdaClientes(MainActivity.this,lista);
        b.recycler.setAdapter(adaptador);
        call.enqueue(new Callback<ClsResponse>() {
            @Override
            public void onResponse(Call<ClsResponse> call, Response<ClsResponse> response) {
                lista = response.body().getClientes();
                adaptador = new AdaClientes(MainActivity.this,lista);
                b.recycler.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<ClsResponse> call, Throwable t) {

            }
        });


    }

//    public void datos_basura() {
//        lista.add(new ClsClientes(1, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(2, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(3, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(4, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(5, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(6, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(7, "juan", "martinez", "lopez"));
//        lista.add(new ClsClientes(8, "juan", "martinez", "lopez"));
//
//    }
}