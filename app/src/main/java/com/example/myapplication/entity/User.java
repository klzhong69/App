package com.example.myapplication.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 本地数据库账户实体类
 */
@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;
    private Long memberId;//用户ID
    private int memberSex;//性别
    private String memberNickname;//昵称
    private String memberIcon;//头像地址链接
    private Boolean state;//是否为当前账户

    @Generated(hash = 1411810760)
    public User(Long id, Long memberId, int memberSex, String memberNickname,
            String memberIcon, Boolean state) {
        this.id = id;
        this.memberId = memberId;
        this.memberSex = memberSex;
        this.memberNickname = memberNickname;
        this.memberIcon = memberIcon;
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
    public Boolean getState() {
        return this.state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }


    
}