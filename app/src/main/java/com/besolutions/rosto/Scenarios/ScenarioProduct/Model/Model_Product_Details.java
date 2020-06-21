package com.besolutions.rosto.Scenarios.ScenarioProduct.Model;//
//  Model_Product_Details.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 27, 2019

import org.json.*;
import java.util.*;

import com.google.gson.annotations.SerializedName;


public class Model_Product_Details{

	@SerializedName("prices")
	private Price_Details[] prices;
	@SerializedName("product")
	private Productes_Details product;
	@SerializedName("status")
	private int status;

	public void setPrices(Price_Details[] prices){
		this.prices = prices;
	}
	public Price_Details[] getPrices(){
		return this.prices;
	}
	public void setProduct(Productes_Details product){
		this.product = product;
	}
	public Productes_Details getProduct(){
		return this.product;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Model_Product_Details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		product = new Productes_Details(jsonObject.optJSONObject("product"));
		JSONArray pricesJsonArray = jsonObject.optJSONArray("prices");
		if(pricesJsonArray != null){
			ArrayList<Price_Details> pricesArrayList = new ArrayList<>();
			for (int i = 0; i < pricesJsonArray.length(); i++) {
				JSONObject pricesObject = pricesJsonArray.optJSONObject(i);
				pricesArrayList.add(new Price_Details(pricesObject));
			}
			prices = (Price_Details[]) pricesArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("product", product.toJsonObject());
			if(prices != null && prices.length > 0){
				JSONArray pricesJsonArray = new JSONArray();
				for(Price_Details pricesElement : prices){
					pricesJsonArray.put(pricesElement.toJsonObject());
				}
				jsonObject.put("prices", pricesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}