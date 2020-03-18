package com.example.hz52.app.Entity;

public class Modify {

    private int id;
    private String imagesrc;
    private String type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Modify(int id, String imagesrc, String type) {
        this.id = id;
        this.imagesrc = imagesrc;
        this.type = type;
    }
}
