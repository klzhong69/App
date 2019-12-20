package com.example.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id(autoincrement = true)//设置自增长
    private Long id;

    @Index(unique = true)//设置唯一性
    private Long userId;//人员编号

    private String name;//人员姓名

    private String usersrc;//人员头像

    private int state;//是否为当前账户

    @Generated(hash = 63559966)
    public User(Long id, Long userId, String name, String usersrc, int state) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.usersrc = usersrc;
        this.state = state;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsersrc() {
        return this.usersrc;
    }

    public void setUsersrc(String usersrc) {
        this.usersrc = usersrc;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

   

    



}
