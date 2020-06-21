package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model;//
//  ModelOrder.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 21, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelOrder{

	@SerializedName("address")
	private String address;
	@SerializedName("building")
	private String building;
	@SerializedName("created_by")
	private String createdBy;
	@SerializedName("createdAt")
	private String createdAt;
	@SerializedName("deleted")
	private String deleted;
	@SerializedName("flat")
	private String flat;
	@SerializedName("floor")
	private String floor;
	@SerializedName("id")
	private String id;
	@SerializedName("id_branch")
	private String idBranch;
	@SerializedName("id_customer")
	private String idCustomer;
	@SerializedName("id_payment_way")
	private Object idPaymentWay;
	@SerializedName("last_updated_by")
	private String lastUpdatedBy;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("longitude")
	private String longitude;
	@SerializedName("mobile")
	private String mobile;
	@SerializedName("name")
	private String name;
	@SerializedName("notes")
	private String notes;
	@SerializedName("shipping")
	private Object shipping;
	@SerializedName("status")
	private String status;
	@SerializedName("street")
	private String street;
	@SerializedName("total_price")
	private String totalPrice;
	@SerializedName("total_with_shipping")
	private String totalWithShipping;
	@SerializedName("updated_at")
	private String updatedAt;

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
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	public String getCreatedBy(){
		return this.createdBy;
	}
	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}
	public String getCreatedAt(){
		return this.createdAt;
	}
	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
	public String getDeleted(){
		return this.deleted;
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
	public void setIdBranch(String idBranch){
		this.idBranch = idBranch;
	}
	public String getIdBranch(){
		return this.idBranch;
	}
	public void setIdCustomer(String idCustomer){
		this.idCustomer = idCustomer;
	}
	public String getIdCustomer(){
		return this.idCustomer;
	}
	public void setIdPaymentWay(Object idPaymentWay){
		this.idPaymentWay = idPaymentWay;
	}
	public Object getIdPaymentWay(){
		return this.idPaymentWay;
	}
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedBy(){
		return this.lastUpdatedBy;
	}
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
	public String getLatitude(){
		return this.latitude;
	}
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
	public String getLongitude(){
		return this.longitude;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setNotes(String notes){
		this.notes = notes;
	}
	public String getNotes(){
		return this.notes;
	}
	public void setShipping(Object shipping){
		this.shipping = shipping;
	}
	public Object getShipping(){
		return this.shipping;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getStreet(){
		return this.street;
	}
	public void setTotalPrice(String totalPrice){
		this.totalPrice = totalPrice;
	}
	public String getTotalPrice(){
		return this.totalPrice;
	}
	public void setTotalWithShipping(String totalWithShipping){
		this.totalWithShipping = totalWithShipping;
	}
	public String getTotalWithShipping(){
		return this.totalWithShipping;
	}
	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}
	public String getUpdatedAt(){
		return this.updatedAt;
	}

    public ModelOrder() {
    }

    /**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelOrder(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = (String) jsonObject.opt("address");
		building = (String) jsonObject.opt("building");
		createdBy = (String) jsonObject.opt("created_by");
		createdAt = (String) jsonObject.opt("createdAt");
		deleted = (String) jsonObject.opt("deleted");
		flat = (String) jsonObject.opt("flat");
		floor = (String) jsonObject.opt("floor");
		id = (String) jsonObject.opt("id");
		idBranch = (String) jsonObject.opt("id_branch");
		idCustomer = (String) jsonObject.opt("id_customer");
		lastUpdatedBy = (String) jsonObject.opt("last_updated_by");
		latitude = (String) jsonObject.opt("latitude");
		longitude = (String) jsonObject.opt("longitude");
		mobile = (String) jsonObject.opt("mobile");
		name = (String) jsonObject.opt("name");
		notes = (String) jsonObject.opt("notes");
		status = (String) jsonObject.opt("status");
		street = (String) jsonObject.opt("street");
		totalPrice = (String) jsonObject.opt("total_price");
		totalWithShipping = (String) jsonObject.opt("total_with_shipping");
		updatedAt = (String) jsonObject.opt("updated_at");
		idPaymentWay = (String) jsonObject.opt("id_payment_way");
		shipping = (String) jsonObject.opt("shipping");
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
			jsonObject.put("created_by", createdBy);
			jsonObject.put("createdAt", createdAt);
			jsonObject.put("deleted", deleted);
			jsonObject.put("flat", flat);
			jsonObject.put("floor", floor);
			jsonObject.put("id", id);
			jsonObject.put("id_branch", idBranch);
			jsonObject.put("id_customer", idCustomer);
			jsonObject.put("id_payment_way", idPaymentWay);
			jsonObject.put("last_updated_by", lastUpdatedBy);
			jsonObject.put("latitude", latitude);
			jsonObject.put("longitude", longitude);
			jsonObject.put("mobile", mobile);
			jsonObject.put("name", name);
			jsonObject.put("notes", notes);
			jsonObject.put("shipping", shipping);
			jsonObject.put("status", status);
			jsonObject.put("street", street);
			jsonObject.put("total_price", totalPrice);
			jsonObject.put("total_with_shipping", totalWithShipping);
			jsonObject.put("updated_at", updatedAt);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}