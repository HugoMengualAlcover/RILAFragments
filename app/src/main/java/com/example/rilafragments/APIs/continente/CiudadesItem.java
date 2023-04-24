package com.example.rilafragments.APIs.continente;

public class CiudadesItem{
	private String id;
	private String ciudadId;
	private String nombre;

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
