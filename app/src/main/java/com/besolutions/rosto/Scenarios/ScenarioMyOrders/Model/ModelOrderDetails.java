package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model;//
//  ModelOrderDetails.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 21, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelOrderDetails{

	@SerializedName("order")
	private ModelOrderOrder order;
	@SerializedName("status")
	private int status;

	public void setOrder(ModelOrderOrder order){
		this.order = order;
	}
	public ModelOrderOrder getOrder(){
		return this.order;
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
	public ModelOrderDetails(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		order = new ModelOrderOrder(jsonObject.optJSONObject("order"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("order", order.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}