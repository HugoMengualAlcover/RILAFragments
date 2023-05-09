package com.example.rilafragments.APIs.ciudades;

import java.io.Serializable;

public class Actividad implements Serializable {
	private String descripcion;
	private float precio;
	private String nombreActividad;
	private String urlActividad;
	private String id;

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setPrecio(float precio){
		this.precio = precio;
	}

	public float getPrecio(){
		return precio;
	}

	public void setNombreActividad(String nombreActividad){
		this.nombreActividad = nombreActividad;
	}

	public String getNombreActividad(){
		return nombreActividad;
	}

	public void setUrlActividad(String urlActividad){
		this.urlActividad = urlActividad;
	}

	public String getUrlActividad(){
		return urlActividad;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Actividad{" + 
			"descripcion = '" + descripcion + '\'' + 
			",precio = '" + precio + '\'' + 
			",nombreActividad = '" + nombreActividad + '\'' + 
			",urlActividad = '" + urlActividad + '\'' + 
			",_id = '" + id + '\'' + 
			"}";
		}
}
