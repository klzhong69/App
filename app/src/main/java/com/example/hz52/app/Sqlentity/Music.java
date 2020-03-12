package com.example.hz52.app.Sqlentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Music {

    @Id(autoincrement = true)//设置自增长
    @Index(unique = true)//设置唯一性
    private Long id;



    private String name;
    private String time;
    @Generated(hash = 461341878)
    public Music(Long id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }
    @Generated(hash = 1263212761)
    public Music() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
  
}
