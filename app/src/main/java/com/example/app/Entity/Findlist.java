package com.example.app.Entity;

public class Findlist {

    private String name;
    private String txt;
    private String sum;
    private String imagesrc;
    private String icon;
    private String lab1;
    private String lab2;
    private String lab3;

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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
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

    public String getLab1() {
        return lab1;
    }

    public void setLab1(String lab1) {
        this.lab1 = lab1;
    }

    public String getLab2() {
        return lab2;
    }

    public void setLab2(String lab2) {
        this.lab2 = lab2;
    }

    public String getLab3() {
        return lab3;
    }

    public void setLab3(String lab3) {
        this.lab3 = lab3;
    }

    public Findlist(String name, String txt, String sum, String imagesrc, String icon, String lab1, String lab2, String lab3) {
        this.name = name;
        this.txt = txt;
        this.sum = sum;
        this.imagesrc = imagesrc;
        this.icon = icon;
        this.lab1 = lab1;
        this.lab2 = lab2;
        this.lab3 = lab3;
    }
}
