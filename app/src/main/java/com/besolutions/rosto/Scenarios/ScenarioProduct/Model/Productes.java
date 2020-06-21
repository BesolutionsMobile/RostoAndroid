package com.besolutions.rosto.Scenarios.ScenarioProduct.Model;//
//  Product.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 27, 2019

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Productes {

	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;
	@SerializedName("price")
	private String price;
	@SerializedName("product_id")
	private String productId;

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
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setProductId(String productId){
		this.productId = productId;
	}
	public String getProductId(){
		return this.productId;
	}


	public Productes() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Productes(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		image = String.valueOf(jsonObject.opt("image"));
		name = String.valueOf(jsonObject.opt("name"));
		price = String.valueOf(jsonObject.opt("price"));
		productId = String.valueOf(jsonObject.opt("product_id"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			jsonObject.put("price", price);
			jsonObject.put("product_id", productId);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}