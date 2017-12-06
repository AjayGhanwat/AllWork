package com.bridgelabz.retriverdata;

public class UserModul {

    String mTitle,mDesc;

    public UserModul(){

    }

    public UserModul(String mTitle, String mDesc) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }
}
