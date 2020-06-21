package com.besolutions.rosto.Scenarios.ScenarioHome.Model;//
//  Model_home.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 26, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_home{

	@SerializedName("catrgories")
	private Catrgory[] catrgories;
	@SerializedName("status")
	private int status;

	public void setCatrgories(Catrgory[] catrgories){
		this.catrgories = catrgories;
	}
	public Catrgory[] getCatrgories(){
		return this.catrgories;
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
	public Model_home(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray catrgoriesJsonArray = jsonObject.optJSONArray("catrgories");
		if(catrgoriesJsonArray != null){
			ArrayList<Catrgory> catrgoriesArrayList = new ArrayList<>();
			for (int i = 0; i < catrgoriesJsonArray.length(); i++) {
				JSONObject catrgoriesObject = catrgoriesJsonArray.optJSONObject(i);
				catrgoriesArrayList.add(new Catrgory(catrgoriesObject));
			}
			catrgories = (Catrgory[]) catrgoriesArrayList.toArray();
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
			if(catrgories != null && catrgories.length > 0){
				JSONArray catrgoriesJsonArray = new JSONArray();
				for(Catrgory catrgoriesElement : catrgories){
					catrgoriesJsonArray.put(catrgoriesElement.toJsonObject());
				}
				jsonObject.put("catrgories", catrgoriesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}