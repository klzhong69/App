package com.example.hz52.app.Entity;

public class Roomgift {

    private String ima;
    private String giftname;
    private String num;

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Roomgift(String ima, String giftname, String num) {
        this.ima = ima;
        this.giftname = giftname;
        this.num = num;
    }
}
