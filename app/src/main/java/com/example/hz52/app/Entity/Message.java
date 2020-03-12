package com.example.hz52.app.Entity;

public class Message  {

    private Long userid;
    private String name;
    private String txt;
    private String time;
    private int sum;
    private String imagesrc;
    private String icon;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
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

    public Message(Long userid, String name, String txt, String time, int sum, String imagesrc, String icon) {
        this.userid = userid;
        this.name = name;
        this.txt = txt;
        this.time = time;
        this.sum = sum;
        this.imagesrc = imagesrc;
        this.icon = icon;
    }
}
