package com.example.rilafragments.APIs.ciudades;

import java.io.Serializable;
import java.util.List;

public class Ciudad implements Serializable {
	private String descripcion;
	private List<Actividad> activities;
	private String urlCiudad;
	private Id id;
	private String name;
	private String pais;

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setActivities(List<Actividad> activities){
		this.activities = activities;
	}

	public List<Actividad> getActivities(){
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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
			",nombre = '" + name + '\'' +
			",pais = '" + pais + '\'' + 
			"}";
		}
}