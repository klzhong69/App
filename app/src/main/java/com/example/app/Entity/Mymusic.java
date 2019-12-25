package com.example.app.Entity;

public class Mymusic {

    private Long id;
    private String name;
    private String time;
    private String type;
    private String txt;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Mymusic(Long id, String name, String time, String type, String txt, String url) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.type = type;
        this.txt = txt;
        this.url = url;
    }
}
