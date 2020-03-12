package com.example.hz52.app.Entity;

public class Findgift {

    private String userima1;
    private String userima2;
    private String name1;
    private String name2;
    private String grade1;
    private String grade2;
    private String num;
    private String gift;
    private String roomid;

    public String getUserima1() {
        return userima1;
    }

    public void setUserima1(String userima1) {
        this.userima1 = userima1;
    }

    public String getUserima2() {
        return userima2;
    }

    public void setUserima2(String userima2) {
        this.userima2 = userima2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getGrade1() {
        return grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getGrade2() {
        return grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public Findgift(String userima1, String userima2, String name1, String name2, String grade1, String grade2, String num, String gift, String roomid) {
        this.userima1 = userima1;
        this.userima2 = userima2;
        this.name1 = name1;
        this.name2 = name2;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.num = num;
        this.gift = gift;
        this.roomid = roomid;
    }
}
