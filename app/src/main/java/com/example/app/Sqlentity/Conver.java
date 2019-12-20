package com.example.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Conver {

    @Id(autoincrement = true)//设置自增长
    private Long id;

    @Index(unique = true)//设置唯一性
    private Long conversations;//会话

    private Long sendId;//发送人员编号

    private String sendname;//接受人员姓名

    private String sendsrc;//发送人员头像

    private int type;//是否为挚友

    @Generated(hash = 1313486526)
    public Conver(Long id, Long conversations, Long sendId, String sendname,
            String sendsrc, int type) {
        this.id = id;
        this.conversations = conversations;
        this.sendId = sendId;
        this.sendname = sendname;
        this.sendsrc = sendsrc;
        this.type = type;
    }

    @Generated(hash = 2111202836)
    public Conver() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversations() {
        return this.conversations;
    }

    public void setConversations(Long conversations) {
        this.conversations = conversations;
    }

    public Long getSendId() {
        return this.sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public String getSendname() {
        return this.sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getSendsrc() {
        return this.sendsrc;
    }

    public void setSendsrc(String sendsrc) {
        this.sendsrc = sendsrc;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

   




   
}
