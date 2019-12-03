package com.example.myapplication.utils;

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

    private int h1 =0;
    private int h2 =0;
    private int h3 =0;
    private int h4 =0;

    public int getH1() {
        return h1;
    }

    public void setH1(int h1) {
        this.h1 = h1;
    }

    public int getH2() {
        return h2;
    }

    public void setH2(int h2) {
        this.h2 = h2;
    }

    public int getH3() {
        return h3;
    }

    public void setH3(int h3) {
        this.h3 = h3;
    }

    public int getH4() {
        return h4;
    }

    public void setH4(int h4) {
        this.h4 = h4;
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
        setH1(0);
        setH2(0);
        setH3(0);
        setH4(0);
    }
}
