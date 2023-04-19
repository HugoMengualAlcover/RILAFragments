package com.example.rilafragments.APIs.continente;

import java.util.List;

public class Continente {
	private String id;
	private String nombreContinente;
	private List<Pais> paises;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNombreContinente(String nombreContinente){
		this.nombreContinente = nombreContinente;
	}

	public String getNombreContinente(){
		return nombreContinente;
	}

	public void setPaises(List<Pais> paises){
		this.paises = paises;
	}

	public List<Pais> getPaises(){
		return paises;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"_id = '" + id + '\'' + 
			",nombreContinente = '" + nombreContinente + '\'' + 
			",paises = '" + paises + '\'' + 
			"}";
		}
}