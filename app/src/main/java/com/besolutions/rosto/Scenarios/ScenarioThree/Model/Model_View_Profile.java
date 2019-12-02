package com.besolutions.rosto.Scenarios.ScenarioThree.Model;//
//  Model_View_Profile.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 28, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_View_Profile{

	@SerializedName("status")
	private int status;
	@SerializedName("user")
	private User user;

	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Model_View_Profile(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		user = new User(jsonObject.optJSONObject("user"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("user", user.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}