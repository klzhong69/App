package com.example.app.Entity;

public class Roomhead {

    private String usersrc;
    private String name;
    private String ima;
    private String msima;

    private Long uid;
    // 音量值
    private int audioVolum;
    // 音频 mute 状态
    private boolean audioMute;
    // 是否是自己
    private boolean isUserSelf;

    public String getUsersrc() {
        return usersrc;
    }

    public void setUsersrc(String usersrc) {
        this.usersrc = usersrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public String getMsima() {
        return msima;
    }

    public void setMsima(String msima) {
        this.msima = msima;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public int getAudioVolum() {
        return audioVolum;
    }

    public void setAudioVolum(int audioVolum) {
        this.audioVolum = audioVolum;
    }

    public boolean isAudioMute() {
        return audioMute;
    }

    public void setAudioMute(boolean audioMute) {
        this.audioMute = audioMute;
    }

    public boolean isUserSelf() {
        return isUserSelf;
    }

    public void setUserSelf(boolean userSelf) {
        isUserSelf = userSelf;
    }

    public Roomhead(String usersrc, String name, String ima, String msima, Long uid, int audioVolum, boolean audioMute, boolean isUserSelf) {
        this.usersrc = usersrc;
        this.name = name;
        this.ima = ima;
        this.msima = msima;
        this.uid = uid;
        this.audioVolum = audioVolum;
        this.audioMute = audioMute;
        this.isUserSelf = isUserSelf;
    }
}
