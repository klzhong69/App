package com.example.app.Entity;

public class Roomhead {

    private String usersrc;
    private String name;
    private String ima;
    private String msima;

    public String getUsersrc() {
        return usersrc;
    }

    public void setUsersrc(String usersrc) {
        this.usersrc = usersrc;
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

    public String getMsima() {
        return msima;
    }

    public void setMsima(String msima) {
        this.msima = msima;
    }

    public Roomhead(String usersrc, String name, String ima, String msima) {
        this.usersrc = usersrc;
        this.name = name;
        this.ima = ima;
        this.msima = msima;
    }
}
