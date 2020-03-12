package com.example.hz52.app.Entity;

public class Paimai {

    private String id;
    private String userima;
    private String name;
    private String grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Paimai(String id, String userima, String name, String grade) {
        this.id = id;
        this.userima = userima;
        this.name = name;
        this.grade = grade;
    }
}
