package com.example.hz52.app.Entity;

public class Page {

    private String txt;
    private String imagesrc;

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

    public Page(String txt, String imagesrc) {
        this.imagesrc = imagesrc;
        this.txt = txt;
    }
}
