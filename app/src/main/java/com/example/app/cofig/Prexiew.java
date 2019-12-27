package com.example.app.cofig;

import com.example.app.Entity.Paimai;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;

public class Prexiew {

    private int code;
    private String msg;
    private JsonObject data;
    private JsonObject cdn;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public JsonObject getData() {
        return data;
    }

    public JsonObject getCdn() {
        return cdn;
    }


}
