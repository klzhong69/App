package com.example.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Account {


    @Index(unique = true)//设置唯一性
    private int state;
    private String name;
    private int type;
    @Generated(hash = 2013620623)
    public Account(int state, String name, int type) {
        this.state = state;
        this.name = name;
        this.type = type;
    }
    @Generated(hash = 882125521)
    public Account() {
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }


}
