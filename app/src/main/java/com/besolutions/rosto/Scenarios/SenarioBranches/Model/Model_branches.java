package com.besolutions.rosto.Scenarios.SenarioBranches.Model;//
//  Model_branches.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 26, 2019

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_branches{

	@SerializedName("branches")
	private Branch[] branches;
	@SerializedName("status")
	private int status;

	public void setBranches(Branch[] branches){
		this.branches = branches;
	}
	public Branch[] getBranches(){
		return this.branches;
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
	public Model_branches(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray branchesJsonArray = jsonObject.optJSONArray("branches");
		if(branchesJsonArray != null){
			ArrayList<Branch> branchesArrayList = new ArrayList<>();
			for (int i = 0; i < branchesJsonArray.length(); i++) {
				JSONObject branchesObject = branchesJsonArray.optJSONObject(i);
				branchesArrayList.add(new Branch(branchesObject));
			}
			branches = (Branch[]) branchesArrayList.toArray();
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
			if(branches != null && branches.length > 0){
				JSONArray branchesJsonArray = new JSONArray();
				for(Branch branchesElement : branches){
					branchesJsonArray.put(branchesElement.toJsonObject());
				}
				jsonObject.put("branches", branchesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}