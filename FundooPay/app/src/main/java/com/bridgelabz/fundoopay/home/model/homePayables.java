package com.bridgelabz.fundoopay.home.model;

public class homePayables {

    private String murchandName;
    private String shopeName;
    private String mobileNumber;
    private String murchandAddress;
    private String totalPrice;


    public homePayables() {
    }

    public homePayables(String murchandName, String shopeName, String mobileNumber, String murchandAddress, String totalPrice) {
        this.murchandName = murchandName;
        this.shopeName = shopeName;
        this.mobileNumber = mobileNumber;
        this.murchandAddress = murchandAddress;
        this.totalPrice = totalPrice;
    }


    public String getMurchandName() {
        return murchandName;
    }

    public void setMurchandName(String murchandName) {
        this.murchandName = murchandName;
    }

    public String getShopeName() {
        return shopeName;
    }

    public void setShopeName(String shopeName) {
        this.shopeName = shopeName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMurchandAddress() {
        return murchandAddress;
    }

    public void setMurchandAddress(String murchandAddress) {
        this.murchandAddress = murchandAddress;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
