package com.example.hz52.app.Entity;

public class Footprint {

    private String ima;
    private String name;
    private String time;
    private String txt;

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Footprint(String ima , String name, String time, String txt) {
        this.ima = ima;
        this.name = name;
        this.time = time;
        this.txt = txt;
    }
}
