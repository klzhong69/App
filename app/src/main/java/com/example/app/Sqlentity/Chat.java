package com.example.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

@Entity
public class Chat {

    @Id(autoincrement = true)//设置自增长
    @Index(unique = true)//设置唯一性
    private Long id;

    private Long conversation;//会话

    private Long sendId;//发送人员编号

    private Long receiveId;//接受人员编号

    private String sendsrc;//发送人员头像

    private String receivesrc;//接受人员头像

    private Long data;//时间

    private String txt;//内容

    private int state;//状态

    @Generated(hash = 1781089175)
    public Chat(Long id, Long conversation, Long sendId, Long receiveId,
            String sendsrc, String receivesrc, Long data, String txt, int state) {
        this.id = id;
        this.conversation = conversation;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.sendsrc = sendsrc;
        this.receivesrc = receivesrc;
        this.data = data;
        this.txt = txt;
        this.state = state;
    }

    @Generated(hash = 519536279)
    public Chat() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversation() {
        return this.conversation;
    }

    public void setConversation(Long conversation) {
        this.conversation = conversation;
    }

    public Long getSendId() {
        return this.sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getReceiveId() {
        return this.receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public String getSendsrc() {
        return this.sendsrc;
    }

    public void setSendsrc(String sendsrc) {
        this.sendsrc = sendsrc;
    }

    public String getReceivesrc() {
        return this.receivesrc;
    }

    public void setReceivesrc(String receivesrc) {
        this.receivesrc = receivesrc;
    }

    public Long getData() {
        return this.data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }





  
   

    
}
