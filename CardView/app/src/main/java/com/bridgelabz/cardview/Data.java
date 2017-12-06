package com.bridgelabz.cardview;

import java.util.ArrayList;

public class Data {

    private String Title;
    private String Desc;
    private String Key;
    private int Color;
    private String Date;


    public Data() {
    }

    public Data(String title, String desc, String key, int color, String date) {
        Title = title;
        Desc = desc;
        Key = key;
        Color = color;
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

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
