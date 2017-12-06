package com.bridgelabz.fundoopay.home.model;

public class notifications {


    private String notificationTitle;
    private String notificationDate;
    private String notificationTime;

    public notifications() {
    }

    public notifications(String notificationTitle, String notificationDate, String notificationTime) {
        this.notificationTitle = notificationTitle;
        this.notificationDate = notificationDate;
        this.notificationTime = notificationTime;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}
