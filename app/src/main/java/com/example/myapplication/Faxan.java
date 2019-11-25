package com.example.myapplication;

public class Faxan {

    private String name;
    private String txt;
    private String type;
    private int popul;
    private int collection;
    private String imagesrc;

    public Faxan(String name, String txt, String type, int popul, int collection, String imagesrc) {
        this.name = name;
        this.txt = txt;
        this.type = type;
        this.popul = popul;
        this.collection = collection;
        this.imagesrc = imagesrc;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPopul() {
        return popul;
    }

    public void setPopul(int popul) {
        this.popul = popul;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }
}
