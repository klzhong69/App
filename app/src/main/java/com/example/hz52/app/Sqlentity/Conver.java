package com.example.hz52.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Conver {

    @Id//设置自增长
    @Index(unique = true)//设置唯一性
    private Long sendId;//发送人员编号

    private Long userId;//接收人员编号

    private String sendname;//发送人员姓名

    private String sendsrc;//发送人员头像

    private int type;//是否为挚友

    private Long data;//时间

    private String txt;//内容

    private int sum;//未读数量

    @Generated(hash = 593796514)
    public Conver(Long sendId, Long userId, String sendname, String sendsrc,
            int type, Long data, String txt, int sum) {
        this.sendId = sendId;
        this.userId = userId;
        this.sendname = sendname;
        this.sendsrc = sendsrc;
        this.type = type;
        this.data = data;
        this.txt = txt;
        this.sum = sum;
    }

    @Generated(hash = 2111202836)
    public Conver() {
    }

    public Long getSendId() {
        return this.sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public int getSum() {
        return this.sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

   
   
    
}
