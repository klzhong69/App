package com.example.app.Entity;

public class Switch {

    private String userima;
    private String name;
    private String id;
    private String type;

    public String getUserima() {
        return userima;
    }

    public void setUserima(String userima) {
        this.userima = userima;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Switch(String userima, String name, String id, String type) {
        this.userima = userima;
        this.name = name;
        this.id = id;
        this.type = type;
    }
}
