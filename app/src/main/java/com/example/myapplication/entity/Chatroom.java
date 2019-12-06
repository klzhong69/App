package com.example.myapplication.entity;

/**
 * 聊天室坑位实体类.
 */
public class Chatroom {

    private String usersrc;
    private String name;
    private String ima;

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

    public Chatroom(String usersrc, String name, String ima) {
        this.usersrc = usersrc;
        this.name = name;
        this.ima = ima;
    }
}
