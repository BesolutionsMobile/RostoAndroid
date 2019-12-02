package com.besolutions.rosto.Scenarios.ScenarioSeven.Model;//
//  Price.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 27, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Price_Details {

	@SerializedName("id_size")
	private String idSize;
	@SerializedName("price")
	private String price;
	@SerializedName("size")
	private String size;

	public void setIdSize(String idSize){
		this.idSize = idSize;
	}
	public String getIdSize(){
		return this.idSize;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setSize(String size){
		this.size = size;
	}
	public String getSize(){
		return this.size;
	}


	public Price_Details() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Price_Details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		idSize = String.valueOf(jsonObject.opt("id_size"));
		price = String.valueOf(jsonObject.opt("price"));
		size = String.valueOf(jsonObject.opt("size"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id_size", idSize);
			jsonObject.put("price", price);
			jsonObject.put("size", size);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}