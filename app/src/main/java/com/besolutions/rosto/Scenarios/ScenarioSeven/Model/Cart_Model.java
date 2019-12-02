package com.besolutions.rosto.Scenarios.ScenarioSeven.Model;

import android.widget.TextView;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cart_Model extends RealmObject {

    private String txtname, txtsize, txtquntity, txtprice;

    private int id;

    public Cart_Model(String txtname, String txtsize, String txtquntity, String txtprice, int id) {
        this.txtname = txtname;
        this.txtsize = txtsize;
        this.txtquntity = txtquntity;
        this.txtprice = txtprice;
        this.id = id;
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
                ", id=" + id +
                '}';
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
