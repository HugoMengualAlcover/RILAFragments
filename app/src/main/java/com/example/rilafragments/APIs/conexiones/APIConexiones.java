package com.example.rilafragments.APIs.conexiones;

import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.continente.Continente;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIConexiones {

    @GET("/ciudades")
    Call<Ciudad> getCiudades();

    @GET("/continentes")
    Call<Continente> getContinentes();
}
