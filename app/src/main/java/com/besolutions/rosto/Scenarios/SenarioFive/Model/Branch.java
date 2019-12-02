package com.besolutions.rosto.Scenarios.SenarioFive.Model;//
//  Branch.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 26, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Branch{

	@SerializedName("address")
	private String address;
	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}

	public Branch() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */



	public Branch(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = String.valueOf(jsonObject.opt("address"));
		id = String.valueOf(jsonObject.opt("id"));
		image = String.valueOf(jsonObject.opt("image"));
		name = String.valueOf(jsonObject.opt("name"));
		phone = String.valueOf(jsonObject.opt("phone"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("address", address);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}