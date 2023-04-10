package com.example.rilafragments.APIs.ciudades;

public class Actividades {
	private String descripcion;
	private int precio;
	private String nombreActividad;
	private String urlActividad;

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setPrecio(int precio){
		this.precio = precio;
	}

	public int getPrecio(){
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

	@Override
 	public String toString(){
		return 
			"ActivitiesItem{" + 
			"descripcion = '" + descripcion + '\'' + 
			",precio = '" + precio + '\'' + 
			",nombreActividad = '" + nombreActividad + '\'' + 
			",urlActividad = '" + urlActividad + '\'' + 
			"}";
		}
}
