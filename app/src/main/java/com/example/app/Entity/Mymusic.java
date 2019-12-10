package com.example.app.Entity;

public class Mymusic {

    private String ima;
    private String name;
    private String time;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Mymusic(String ima , String name, String time, String type) {
        this.ima = ima;
        this.name = name;
        this.time = time;
        this.type = type;
    }
}
