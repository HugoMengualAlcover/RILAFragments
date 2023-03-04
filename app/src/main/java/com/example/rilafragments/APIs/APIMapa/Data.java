package com.example.rilafragments.APIs.APIMapa;

import java.util.List;

public class Data{
	private List<GetPlacesItem> getPlaces;

	public void setGetPlaces(List<GetPlacesItem> getPlaces){
		this.getPlaces = getPlaces;
	}

	public List<GetPlacesItem> getGetPlaces(){
		return getPlaces;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"getPlaces = '" + getPlaces + '\'' + 
			"}";
		}
}