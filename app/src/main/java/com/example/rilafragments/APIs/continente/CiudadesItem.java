package com.example.rilafragments.APIs.continente;

import com.google.gson.annotations.SerializedName;

public class CiudadesItem{
	@SerializedName("_id")
	private String id;
	private String nombre;
	private String ciudadId;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCiudadId(String ciudadId){
		this.ciudadId = ciudadId;
	}

	public String getCiudadId(){
		return ciudadId;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}
}
