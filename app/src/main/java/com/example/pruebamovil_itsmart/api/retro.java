package com.example.pruebamovil_itsmart.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retro {

    private static final String URL = "https://pruebatecnica-7fb2648c0c79.herokuapp.com/api/";
    private static Retrofit retro;
    public static Retrofit getClient(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
