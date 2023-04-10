package com.example.rilafragments.APIs.ciudades;

public class Id{
	private String oid;

	public void setOid(String oid){
		this.oid = oid;
	}

	public String getOid(){
		return oid;
	}

	@Override
 	public String toString(){
		return 
			"Id{" + 
			"$oid = '" + oid + '\'' + 
			"}";
		}
}
