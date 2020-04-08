package com.example.hz52.app.Entity;

public class Roomgift {

    private String id;
    private String ima;
    private String giftname;
    private String num;
    private String svgaUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSvgaUrl() {
        return svgaUrl;
    }

    public void setSvgaUrl(String svgaUrl) {
        this.svgaUrl = svgaUrl;
    }

    public Roomgift(String id, String ima, String giftname, String num, String svgaUrl) {
        this.id = id;
        this.ima = ima;
        this.giftname = giftname;
        this.num = num;
        this.svgaUrl = svgaUrl;
    }
}
