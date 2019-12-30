package com.example.app.cofig;

import com.example.app.Entity.Paimai;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.lang.reflect.Array;

public class Preview {

    private int code;
    private String msg;
    private JsonObject data;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public JsonObject getData() {
        return data;
    }



}
