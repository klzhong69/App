package com.example.hz52.app.Entity;

public class Mygold {

    private String folds;
    private String amount;

    public String getFolds() {
        return folds;
    }

    public void setFolds(String folds) {
        this.folds = folds;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Mygold(String folds , String amount) {
        this.folds = folds;
        this.amount = amount;
    }
}
