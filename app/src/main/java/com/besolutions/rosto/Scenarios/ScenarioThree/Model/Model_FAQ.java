package com.besolutions.rosto.Scenarios.ScenarioThree.Model;//
//  Model_FAQ.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 5, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_FAQ{

	@SerializedName("questions")
	private Question[] questions;
	@SerializedName("status")
	private int status;

	public void setQuestions(Question[] questions){
		this.questions = questions;
	}
	public Question[] getQuestions(){
		return this.questions;
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
	public Model_FAQ(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray questionsJsonArray = jsonObject.optJSONArray("questions");
		if(questionsJsonArray != null){
			ArrayList<Question> questionsArrayList = new ArrayList<>();
			for (int i = 0; i < questionsJsonArray.length(); i++) {
				JSONObject questionsObject = questionsJsonArray.optJSONObject(i);
				questionsArrayList.add(new Question(questionsObject));
			}
			questions = (Question[]) questionsArrayList.toArray();
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
			if(questions != null && questions.length > 0){
				JSONArray questionsJsonArray = new JSONArray();
				for(Question questionsElement : questions){
					questionsJsonArray.put(questionsElement.toJsonObject());
				}
				jsonObject.put("questions", questionsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}