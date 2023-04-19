package com.example.rilafragments.APIs.conexiones;

import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.continente.Continente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIConexiones {

    @GET("/ciudades/{id}")
    Call<Ciudad> getCiudad(@Path("id") String id);

    @GET("/continentes")
    Call<ArrayList<Continente>> getContinentes();
}
