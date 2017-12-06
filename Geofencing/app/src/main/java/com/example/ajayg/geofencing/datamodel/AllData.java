package com.example.ajayg.geofencing.datamodel;

public class AllData {

    private String Date;

    private String Enter;

    private String Exit;

    private String Interval;

    public AllData() {
    }

    public AllData(String date, String enter, String exit, String interval) {
        Date = date;
        Enter = enter;
        Exit = exit;
        Interval = interval;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getEnter() {
        return Enter;
    }

    public void setEnter(String enter) {
        Enter = enter;
    }

    public String getExit() {
        return Exit;
    }

    public void setExit(String exit) {
        Exit = exit;
    }

    public String getInterval() {
        return Interval;
    }

    public void setInterval(String interval) {
        Interval = interval;
    }
}
