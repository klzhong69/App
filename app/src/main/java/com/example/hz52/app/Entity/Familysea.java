package com.example.hz52.app.Entity;

public class Familysea {

    private String name;
    private String id;
    private String type;

    private String imagesrc;
    private String like;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public Familysea(String name, String id, String type,  String imagesrc, String like) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.imagesrc = imagesrc;
        this.like = like;
    }
}
