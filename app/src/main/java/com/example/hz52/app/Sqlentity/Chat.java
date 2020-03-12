package com.example.hz52.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Chat {

    @Id(autoincrement = true)//设置自增长
    @Index(unique = true)//设置唯一性
    private Long id;

    private String conversation;//会话

    private Long userId;//接收人员编号

    private Long sendId;//发送人员编号

    private String sendname;//发送人员编号

    private String sendsrc;//发送人员头像


    private Long data;//时间

    private String txt;//内容

    private int state;//状态

    @Generated(hash = 926689697)
    public Chat(Long id, String conversation, Long userId, Long sendId,
            String sendname, String sendsrc, Long data, String txt, int state) {
        this.id = id;
        this.conversation = conversation;
        this.userId = userId;
        this.sendId = sendId;
        this.sendname = sendname;
        this.sendsrc = sendsrc;
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

    public String getConversation() {
        return this.conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
