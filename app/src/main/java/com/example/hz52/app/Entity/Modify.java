package com.example.hz52.app.Entity;

public class Modify {

    private String imagesrc;
    private String type;

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Modify(String imagesrc, String type) {
        this.imagesrc = imagesrc;
        this.type = type;
    }
}
