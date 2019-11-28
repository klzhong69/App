package com.example.myapplication.utils;

import android.widget.ImageView;
import android.widget.TextView;

public class BaseEntity {

    private String text;
    private String name;
    private String ima;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public BaseEntity(String name, String text, String ima) {
        this.name = name;
        this.text = text;
        this.ima = ima;
    }
}
