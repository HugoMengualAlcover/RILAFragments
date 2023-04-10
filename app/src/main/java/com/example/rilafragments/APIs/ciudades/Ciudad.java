package com.example.rilafragments.APIs.ciudades;

import java.util.List;

public class Ciudad{
	private String descripcion;
	private List<Actividades> activities;
	private String urlCiudad;
	private Id id;
	private String nombre;
	private String pais;

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setActivities(List<Actividades> activities){
		this.activities = activities;
	}

	public List<Actividades> getActivities(){
		return activities;
	}

	public void setUrlCiudad(String urlCiudad){
		this.urlCiudad = urlCiudad;
	}

	public String getUrlCiudad(){
		return urlCiudad;
	}

	public void setId(Id id){
		this.id = id;
	}

	public Id getId(){
		return id;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}

	public void setPais(String pais){
		this.pais = pais;
	}

	public String getPais(){
		return pais;
	}

	@Override
 	public String toString(){
		return 
			"Ciudad{" + 
			"descripcion = '" + descripcion + '\'' + 
			",activities = '" + activities + '\'' + 
			",urlCiudad = '" + urlCiudad + '\'' + 
			",_id = '" + id + '\'' + 
			",nombre = '" + nombre + '\'' + 
			",pais = '" + pais + '\'' + 
			"}";
		}
}