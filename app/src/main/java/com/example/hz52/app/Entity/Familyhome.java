package com.example.hz52.app.Entity;

public class Familyhome {

    private String name;
    private String imagesrc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public Familyhome(String name, String imagesrc) {
        this.name = name;
        this.imagesrc = imagesrc;
    }
}
