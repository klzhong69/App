package com.example.app.Entity;

public class Chats {

    private String userima;
    private String txt;
    private String time;
    private int type;

    public String getUserima() {
        return userima;
    }

    public void setUserima(String userima) {
        this.userima = userima;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Chats(String userima, String txt, String time, int type) {
        this.userima = userima;
        this.txt = txt;
        this.time = time;
        this.type = type;
    }
}
