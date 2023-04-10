package com.example.rilafragments.APIs.continente;

import java.util.List;

public class Continente{
	private Id id;
	private String nombreContinente;
	private List<PaisesItem> paises;

	public void setId(Id id){
		this.id = id;
	}

	public Id getId(){
		return id;
	}

	public void setNombreContinente(String nombreContinente){
		this.nombreContinente = nombreContinente;
	}

	public String getNombreContinente(){
		return nombreContinente;
	}

	public void setPaises(List<PaisesItem> paises){
		this.paises = paises;
	}

	public List<PaisesItem> getPaises(){
		return paises;
	}

	@Override
 	public String toString(){
		return 
			"Continente{" + 
			"_id = '" + id + '\'' + 
			",nombreContinente = '" + nombreContinente + '\'' + 
			",paises = '" + paises + '\'' + 
			"}";
		}
}