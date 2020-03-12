package com.example.hz52.app.Entity;

public class Roomtxt {

    private String text;
    private String name;
    private String grade;
    private String txt;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Roomtxt(String text, String name, String grade, String txt) {
        this.text = text;
        this.name = name;
        this.grade = grade;
        this.txt = txt;
    }
}
