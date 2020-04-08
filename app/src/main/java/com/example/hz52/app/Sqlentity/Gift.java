package com.example.hz52.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Gift {

    @Id//设置自增长
    @Index(unique = true)//设置唯一性
    private Long id;

    private String giftName;

    private String smallPicUrl;

    private String price;

    @Generated(hash = 949898346)
    public Gift(Long id, String giftName, String smallPicUrl, String price) {
        this.id = id;
        this.giftName = giftName;
        this.smallPicUrl = smallPicUrl;
        this.price = price;
    }

    @Generated(hash = 1473387888)
    public Gift() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getSmallPicUrl() {
        return this.smallPicUrl;
    }

    public void setSmallPicUrl(String smallPicUrl) {
        this.smallPicUrl = smallPicUrl;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
