package com.besolutions.rosto.Scenarios.ScenarioProduct.Model;//
//  Model_Product.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 27, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_Product{

	@SerializedName("products")
	private Productes[] productes;
	@SerializedName("status")
	private int status;

	public void setProductes(Productes[] productes){
		this.productes = productes;
	}
	public Productes[] getProductes(){
		return this.productes;
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
	public Model_Product(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray productsJsonArray = jsonObject.optJSONArray("products");
		if(productsJsonArray != null){
			ArrayList<Productes> productsArrayList = new ArrayList<>();
			for (int i = 0; i < productsJsonArray.length(); i++) {
				JSONObject productsObject = productsJsonArray.optJSONObject(i);
				productsArrayList.add(new Productes(productsObject));
			}
			productes = (Productes[]) productsArrayList.toArray();
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
			if(productes != null && productes.length > 0){
				JSONArray productsJsonArray = new JSONArray();
				for(Productes productsElement : productes){
					productsJsonArray.put(productsElement.toJsonObject());
				}
				jsonObject.put("products", productsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}