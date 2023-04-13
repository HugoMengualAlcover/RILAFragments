package com.example.rilafragments.APIs.continente;

public class Pais {
	private String bandera;
	private String nombre;
	private String[] ciudades;

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

	public void setCiudades(String[] ciudades) {this.ciudades = ciudades;}

	public String[] getCiudades() {return ciudades;}

	@Override
 	public String toString(){
		return 
			"PaisesItem{" + 
			"bandera = '" + bandera + '\'' + 
			",nombre = '" + nombre + '\'' + 
			"}";
		}
}
