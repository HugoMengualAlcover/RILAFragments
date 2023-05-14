package com.example.rilafragments.APIs.ciudades;

import com.example.rilafragments.APIs.continente.Continente;

import java.util.List;

public class ApiResponseCiudades {
    private List<Ciudad> data;

    public void setData(List<Ciudad> data){
        this.data = data;
    }

    public List<Ciudad> getData(){
        return data;
    }

    @Override
    public String toString(){
        return
                "ApiResponseContinentes{" +
                        "data = '" + data + '\'' +
                        "}";
    }
}
