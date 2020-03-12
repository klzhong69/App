package com.example.hz52.app.Entity;

public class Findmake {

    private String userid;
    private String userima;
    private String gender;
    private String grade;
    private String name;
    private String txt;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserima() {
        return userima;
    }

    public void setUserima(String userima) {
        this.userima = userima;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public Findmake(String userid, String userima, String gender, String grade, String name, String txt) {
        this.userid = userid;
        this.userima = userima;
        this.gender = gender;
        this.grade = grade;
        this.name = name;
        this.txt = txt;
    }
}
