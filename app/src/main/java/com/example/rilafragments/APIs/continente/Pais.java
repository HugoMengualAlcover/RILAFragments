package com.example.rilafragments.APIs.continente;

public class Pais {
	private String bandera;
	private String nombre;

	public void setBandera(String bandera){
		this.bandera = bandera;
	}

	public String getBandera(){
		return bandera;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}

	@Override
 	public String toString(){
		return 
			"PaisesItem{" + 
			"bandera = '" + bandera + '\'' + 
			",nombre = '" + nombre + '\'' + 
			"}";
		}
}
