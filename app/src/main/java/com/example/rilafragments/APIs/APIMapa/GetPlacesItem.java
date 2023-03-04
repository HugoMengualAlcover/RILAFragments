package com.example.rilafragments.APIs.APIMapa;

import java.util.List;

public class GetPlacesItem{
	private String country;
	private int distance;
	private Object lng;
	private String name;
	private String id;
	private List<String> categories;
	private Object jsonMemberAbstract;
	private Object lat;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setDistance(int distance){
		this.distance = distance;
	}

	public int getDistance(){
		return distance;
	}

	public void setLng(Object lng){
		this.lng = lng;
	}

	public Object getLng(){
		return lng;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategories(List<String> categories){
		this.categories = categories;
	}

	public List<String> getCategories(){
		return categories;
	}

	public void setJsonMemberAbstract(Object jsonMemberAbstract){
		this.jsonMemberAbstract = jsonMemberAbstract;
	}

	public Object getJsonMemberAbstract(){
		return jsonMemberAbstract;
	}

	public void setLat(Object lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"GetPlacesItem{" + 
			"country = '" + country + '\'' + 
			",distance = '" + distance + '\'' + 
			",lng = '" + lng + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",categories = '" + categories + '\'' + 
			",abstract = '" + jsonMemberAbstract + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}