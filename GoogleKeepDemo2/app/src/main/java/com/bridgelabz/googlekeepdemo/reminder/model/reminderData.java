package com.bridgelabz.googlekeepdemo.reminder.model;

public class reminderData {

    String Title;
    String Note;
    String id;
    String location;

    public reminderData() {
    }

    public reminderData(String title, String note, String id,  String location) {
        Title = title;
        Note = note;
        this.id = id;
        this.location = location;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
