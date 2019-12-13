package com.example.app.Entity;

public class Gifthead {

    private String userima;
    private String type;

    public String getUserima() {
        return userima;
    }

    public void setUserima(String userima) {
        this.userima = userima;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Gifthead(String userima, String type) {
        this.userima = userima;
        this.type = type;
    }
}
