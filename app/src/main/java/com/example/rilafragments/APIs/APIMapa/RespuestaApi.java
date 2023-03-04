package com.example.rilafragments.APIs.APIMapa;

public class RespuestaApi{
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"RespuestaApi{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}
