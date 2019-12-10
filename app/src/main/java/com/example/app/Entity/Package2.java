package com.example.app.Entity;

public class Package2 {

    private String ima;
    private String name;
    private String time;

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

    public Package2(String ima , String name,String time) {
        this.ima = ima;
        this.name = name;
        this.time = time;

    }
}
