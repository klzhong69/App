package com.example.hz52.app.Entity;

public class Record {
    private String amount;
    private int type;
    private String date;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Record(String amount, int type, String date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }
}
