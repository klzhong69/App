package com.example.app.Entity;

public class Roomtxt {

    private String text;
    private String name;
    private String grade;


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

    public Roomtxt(String name, String text, String grade) {
        this.name = name;
        this.text = text;
        this.grade = grade;
    }
}
