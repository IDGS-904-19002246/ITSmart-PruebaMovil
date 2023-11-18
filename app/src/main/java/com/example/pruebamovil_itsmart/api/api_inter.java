package com.example.pruebamovil_itsmart.api;

import com.example.pruebamovil_itsmart.models.ClsClientes;
import com.example.pruebamovil_itsmart.models.ClsCoordenadas;
import com.example.pruebamovil_itsmart.models.ClsUbicacion;
import com.example.pruebamovil_itsmart.models.ClsEstados;
import com.example.pruebamovil_itsmart.models.ClsMunicipios;
import com.example.pruebamovil_itsmart.models.ClsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface api_inter {
    @GET("clientes")
    Call<ClsResponse> TODOS();

    @POST("clientes")
    Call<String>INSERTAR(
            @Body ClsClientes clsClientes
    );
    @PUT("clientes/{id}")
    Call<String>EDITAR(
            @Path("id") int clienteId,
            @Body ClsClientes clsClientes
    );
    @DELETE("clientes/{Id}")
    Call<String>BORRAR(
            @Path("Id") int Id
    );
    //---------------------------------------------------------------------------
    @GET("services")
    Call<ClsEstados> ESTADOS();

    @GET("services/{e}")
    Call<ClsMunicipios> MUNICIPIOS(@Path("e") String estado);

    @POST("services")
    Call<ClsCoordenadas>COORDENADAS(
            @Body ClsUbicacion clsUbicacion
    );

}
