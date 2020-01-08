package com.example.app.cofig;

import com.google.gson.JsonObject;

public class Mess {

    private int type;
    private Long sendTime;
    private JsonObject data;


    public int getType() {
        return type;
    }

    public Long getSendTime() {
        return sendTime;
    }


    public JsonObject getData() {
        return data;
    }


}
