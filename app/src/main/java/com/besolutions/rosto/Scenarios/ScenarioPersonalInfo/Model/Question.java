package com.besolutions.rosto.Scenarios.ScenarioPersonalInfo.Model;//
//  Question.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 5, 2019

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Question{

	@SerializedName("description")
	private String description;
	@SerializedName("id")
	private String id;
	@SerializedName("title")
	private String title;

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
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}


	public Question() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Question(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		description = String.valueOf(jsonObject.opt("description"));
		id = String.valueOf(jsonObject.opt("id"));
		title = String.valueOf(jsonObject.opt("title"));
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
			jsonObject.put("title", title);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}