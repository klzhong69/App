package com.example.app.Entity;

public class Playlist {

    private String name;
    private String sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Playlist(String name, String sum) {
        this.name = name;
        this.sum = sum;
    }
}
