package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model;//
//  ModelOrderProduct.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 21, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelOrderProduct{

	@SerializedName("price_per_item")
	private String pricePerItem;
	@SerializedName("product_image")
	private String productImage;
	@SerializedName("product_name")
	private String productName;
	@SerializedName("quantity")
	private String quantity;
	@SerializedName("size")
	private String size;

	public void setPricePerItem(String pricePerItem){
		this.pricePerItem = pricePerItem;
	}
	public String getPricePerItem(){
		return this.pricePerItem;
	}
	public void setProductImage(String productImage){
		this.productImage = productImage;
	}
	public String getProductImage(){
		return this.productImage;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	public String getProductName(){
		return this.productName;
	}
	public void setQuantity(String quantity){
		this.quantity = quantity;
	}
	public String getQuantity(){
		return this.quantity;
	}
	public void setSize(String size){
		this.size = size;
	}
	public String getSize(){
		return this.size;
	}

	public ModelOrderProduct() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelOrderProduct(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		pricePerItem = (String) jsonObject.opt("price_per_item");
		productImage = (String) jsonObject.opt("product_image");
		productName = (String) jsonObject.opt("product_name");
		quantity = (String) jsonObject.opt("quantity");
		size = (String) jsonObject.opt("size");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("price_per_item", pricePerItem);
			jsonObject.put("product_image", productImage);
			jsonObject.put("product_name", productName);
			jsonObject.put("quantity", quantity);
			jsonObject.put("size", size);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}