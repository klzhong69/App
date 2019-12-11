package com.example.app.Entity;

public class Friends {

    private String name;
    private String txt;
    private String imagesrc;
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }



    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }




    public Friends(String name, String txt, String imagesrc, String icon) {
        this.name = name;
        this.txt = txt;
        this.imagesrc = imagesrc;
        this.icon = icon;
    }

}
