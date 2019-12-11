package com.example.app.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Message  {

    private String name;
    private String txt;
    private String time;
    private String sum;
    private String imagesrc;
    private String icon;

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




    public Message(String name, String txt, String time, String sum, String imagesrc, String icon) {
        this.name = name;
        this.txt = txt;
        this.time = time;
        this.sum = sum;
        this.imagesrc = imagesrc;
        this.icon = icon;
    }

}
