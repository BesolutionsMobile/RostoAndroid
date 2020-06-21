package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model;

public class Model_My_Orders_Details {

    private String txtPrice;
    private String txtCount;
    private String txtType;
    private String txtTitle;

    public Model_My_Orders_Details(String txtPrice, String txtCount, String txtType, String txtTitle) {
        this.txtPrice = txtPrice;
        this.txtCount = txtCount;
        this.txtType = txtType;
        this.txtTitle = txtTitle;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    public String getTxtCount() {
        return txtCount;
    }

    public void setTxtCount(String txtCount) {
        this.txtCount = txtCount;
    }

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }
}
