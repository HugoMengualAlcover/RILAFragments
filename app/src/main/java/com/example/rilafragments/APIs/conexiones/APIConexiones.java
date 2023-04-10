package com.example.rilafragments.APIs.conexiones;

import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.continente.Continente;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIConexiones {

    @GET("/ciudades/{id}")
    Call<Ciudad> getCiudades(@Path("id") String id);

    @GET("/continentes")
    Call<Continente> getContinentes();
}
