package com.example.rilafragments.APIs.continente;

import java.util.List;

public class ApiResponseContinentes {
	private List<Continente> data;

	public void setData(List<Continente> data){
		this.data = data;
	}

	public List<Continente> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ApiResponseContinentes{" +
			"data = '" + data + '\'' + 
			"}";
		}
}