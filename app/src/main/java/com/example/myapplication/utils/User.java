package com.example.myapplication.utils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private Long memberId;//用户ID
    private int memberSex;//性别
    private String memberNickname;//昵称
    private String memberIcon;//头像地址链接

    @Generated(hash = 786268235)
    public User(Long id, Long memberId, int memberSex, String memberNickname,
            String memberIcon) {
        this.id = id;
        this.memberId = memberId;
        this.memberSex = memberSex;
        this.memberNickname = memberNickname;
        this.memberIcon = memberIcon;
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
    public Long getMemberId() {
        return this.memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public int getMemberSex() {
        return this.memberSex;
    }
    public void setMemberSex(int memberSex) {
        this.memberSex = memberSex;
    }
    public String getMemberNickname() {
        return this.memberNickname;
    }
    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }
    public String getMemberIcon() {
        return this.memberIcon;
    }
    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }
    
}