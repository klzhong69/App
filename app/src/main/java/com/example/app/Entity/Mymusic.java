package com.example.app.Entity;

public class Mymusic {

    private String name;
    private String time;
    private String type;
    private String txt;

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

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }


    public Mymusic(String name, String time, String type, String txt) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.txt = txt;
    }
}
