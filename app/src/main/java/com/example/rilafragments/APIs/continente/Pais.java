package com.example.rilafragments.APIs.continente;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pais {
	@SerializedName("_id")
	private String id;
	private String nombre;
	private String bandera;
	private ArrayList<CiudadesItem> ciudades;

	public void setBandera(String bandera){
		this.bandera = bandera;
	}

	public String getBandera(){
		return bandera;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}

	public void setCiudades(ArrayList<CiudadesItem> ciudades){
		this.ciudades = ciudades;
	}

	public ArrayList<CiudadesItem> getCiudades(){
		return ciudades;
	}

	@Override
 	public String toString(){
		return 
			"PaisesItem{" + 
			"bandera = '" + bandera + '\'' + 
			",_id = '" + id + '\'' + 
			",nombre = '" + nombre + '\'' + 
			",ciudades = '" + ciudades + '\'' + 
			"}";
		}
}