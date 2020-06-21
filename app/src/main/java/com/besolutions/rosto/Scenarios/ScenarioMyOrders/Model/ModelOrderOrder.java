package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model;//
//  ModelOrderOrder.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 21, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelOrderOrder{

	@SerializedName("address")
	private String address;
	@SerializedName("building")
	private String building;
	@SerializedName("flat")
	private String flat;
	@SerializedName("floor")
	private String floor;
	@SerializedName("id")
	private String id;
	@SerializedName("notes")
	private String notes;
	@SerializedName("products")
	private ModelOrderProduct[] products;
	@SerializedName("street")
	private String street;

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setBuilding(String building){
		this.building = building;
	}
	public String getBuilding(){
		return this.building;
	}
	public void setFlat(String flat){
		this.flat = flat;
	}
	public String getFlat(){
		return this.flat;
	}
	public void setFloor(String floor){
		this.floor = floor;
	}
	public String getFloor(){
		return this.floor;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setNotes(String notes){
		this.notes = notes;
	}
	public String getNotes(){
		return this.notes;
	}
	public void setProducts(ModelOrderProduct[] products){
		this.products = products;
	}
	public ModelOrderProduct[] getProducts(){
		return this.products;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getStreet(){
		return this.street;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelOrderOrder(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = (String) jsonObject.opt("address");
		building = (String) jsonObject.opt("building");
		flat = (String) jsonObject.opt("flat");
		floor = (String) jsonObject.opt("floor");
		id = (String) jsonObject.opt("id");
		notes = (String) jsonObject.opt("notes");
		street = (String) jsonObject.opt("street");
		JSONArray productsJsonArray = jsonObject.optJSONArray("products");
		if(productsJsonArray != null){
			ArrayList<ModelOrderProduct> productsArrayList = new ArrayList<>();
			for (int i = 0; i < productsJsonArray.length(); i++) {
				JSONObject productsObject = productsJsonArray.optJSONObject(i);
				productsArrayList.add(new ModelOrderProduct(productsObject));
			}
			products = (ModelOrderProduct[]) productsArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("address", address);
			jsonObject.put("building", building);
			jsonObject.put("flat", flat);
			jsonObject.put("floor", floor);
			jsonObject.put("id", id);
			jsonObject.put("notes", notes);
			jsonObject.put("street", street);
			if(products != null && products.length > 0){
				JSONArray productsJsonArray = new JSONArray();
				for(ModelOrderProduct productsElement : products){
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