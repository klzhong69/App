package com.example.app.Entity;

public class Familysea {

    private String name;
    private String id;
    private String type;
    private String icon;
    private String imagesrc;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public Familysea(String name, String id, String type, String icon, String imagesrc) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.icon = icon;
        this.imagesrc = imagesrc;
    }
}
