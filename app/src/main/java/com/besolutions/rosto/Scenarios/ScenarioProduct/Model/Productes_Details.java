package com.besolutions.rosto.Scenarios.ScenarioProduct.Model;//
//  Product.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 27, 2019

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Productes_Details {

	@SerializedName("description")
	private String description;
	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;

	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
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

	public Productes_Details() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Productes_Details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		description = String.valueOf(jsonObject.opt("description"));
		id = String.valueOf(jsonObject.opt("id"));
		image = String.valueOf(jsonObject.opt("image"));
		name = String.valueOf(jsonObject.opt("name"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("description", description);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}