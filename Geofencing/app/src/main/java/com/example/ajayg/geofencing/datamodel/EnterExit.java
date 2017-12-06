package com.example.ajayg.geofencing.datamodel;

public class EnterExit {

    public String Enter;

    public String Exit;

    public String Interval;

    public String Date;

    public EnterExit() {
    }

    public EnterExit(String enter, String exit, String interval, String date) {
        Enter = enter;
        Exit = exit;
        Interval = interval;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
