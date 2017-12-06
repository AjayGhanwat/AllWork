package com.bridgelabz.cropper.model;

public class ImageData {

    private String location;
    private String ImageName;

    public ImageData() {
    }

    public ImageData(String location, String imageName) {
        this.location = location;
        ImageName = imageName;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

}
