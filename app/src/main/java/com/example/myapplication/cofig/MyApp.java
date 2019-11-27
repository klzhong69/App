package com.example.myapplication.cofig;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {

    private Map<Integer,Integer> scores = new HashMap<>();

    public Map<Integer,Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Integer,Integer> scores) {
        this.scores = scores;
    }

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    private int set = 0;

    public int getScoret() {
        return set;
    }

    public void setScoret(int set) {
        this.set = set;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        setScore(0); //初始化全局变量
        setScoret(0);
    }
}
