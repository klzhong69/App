package com.example.app.Entity;

public class Listleader {

    private String lerder;
    private String userima;
    private String name;
    private String type;
    private String sum;

    public String getLerder() {
        return lerder;
    }

    public void setLerder(String lerder) {
        this.lerder = lerder;
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



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }


    public Listleader(String lerder, String userima, String name,String type, String sum) {
        this.lerder = lerder;
        this.userima = userima;
        this.name = name;
        this.type = type;
        this.sum = sum;
    }

}
