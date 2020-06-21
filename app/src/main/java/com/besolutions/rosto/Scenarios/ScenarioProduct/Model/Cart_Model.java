package com.besolutions.rosto.Scenarios.ScenarioProduct.Model;

import io.realm.RealmObject;

public class Cart_Model extends RealmObject {

    private String txtname;
    private String txtsize;
    private String txtquntity;
    private String txtprice;
    private String idproduct;
    private String size_id;

    private int id;

    public Cart_Model(String txtname, String txtsize, String txtquntity, String txtprice,String idproduct,String size_id, int id) {
        this.txtname = txtname;
        this.txtsize = txtsize;
        this.txtquntity = txtquntity;
        this.txtprice = txtprice;
        this.id = id;
        this.idproduct = idproduct;
        this.size_id = size_id;
    }

    public Cart_Model() {
    }

    @Override
    public String toString() {
        return "Cart_Model{" +
                "txtname='" + txtname + '\'' +
                ", txtsize='" + txtsize + '\'' +
                ", txtquntity='" + txtquntity + '\'' +
                ", txtprice='" + txtprice + '\'' +
                ", idproduct='" + idproduct + '\'' +
                ", size_id='" + size_id + '\'' +
                ", id=" + id +
                '}';
    }

    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public String getTxtname() {
        return txtname;
    }

    public void setTxtname(String txtname) {
        this.txtname = txtname;
    }

    public String getTxtsize() {
        return txtsize;
    }

    public void setTxtsize(String txtsize) {
        this.txtsize = txtsize;
    }

    public String getTxtquntity() {
        return txtquntity;
    }

    public void setTxtquntity(String txtquntity) {
        this.txtquntity = txtquntity;
    }

    public String getTxtprice() {
        return txtprice;
    }

    public void setTxtprice(String txtprice) {
        this.txtprice = txtprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
