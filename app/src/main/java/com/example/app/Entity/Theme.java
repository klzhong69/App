package com.example.app.Entity;

public class Theme {

    private String theme;
    private String name;
    private String type;
    private int state;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Theme(String theme, String name, String type, int state) {
        this.theme = theme;
        this.name = name;
        this.type = type;
        this.state = state;
    }
}
