package com.besolutions.rosto.Scenarios.ScenarioTwo.Model;//
//  UserFinalResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 18, 2019

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class UserFinalResponse {

	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;
	@SerializedName("user_data")
	private UserFinalResponseUserDatum userData;

	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setUserData(UserFinalResponseUserDatum userData){
		this.userData = userData;
	}
	public UserFinalResponseUserDatum getUserData(){
		return this.userData;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public UserFinalResponse (JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = (String) jsonObject.opt("message");
		status = jsonObject.optInt("status");
		userData = new UserFinalResponseUserDatum(jsonObject.optJSONObject("user_data"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			jsonObject.put("userData", userData.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}