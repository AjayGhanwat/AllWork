package com.bridgelabz.samplefirestore;

public class userData {

    private String Title;
    private String Desc;
    private String Key;
    private String Date;

    public userData() {
    }

    public userData(String title, String desc, String key,String date) {
        Title = title;
        Desc = desc;
        Key = key;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
