package com.example.hz52.app.cofig;

import com.google.gson.JsonObject;

public class Mess {

    private int type;
    private Long sendTime;
    private JsonObject data;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
