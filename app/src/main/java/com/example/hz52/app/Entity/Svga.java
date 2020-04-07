package com.example.hz52.app.Entity;

public class Svga {
    private String name;
    private String ima;
    private String imaforkey;
    private String txt;
    private String txtforkey;

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

    public String getImaforkey() {
        return imaforkey;
    }

    public void setImaforkey(String imaforkey) {
        this.imaforkey = imaforkey;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxtforkey() {
        return txtforkey;
    }

    public void setTxtforkey(String txtforkey) {
        this.txtforkey = txtforkey;
    }

    public Svga(String name, String ima, String imaforkey, String txt, String txtforkey) {
        this.name = name;
        this.ima = ima;
        this.imaforkey = imaforkey;
        this.txt = txt;
        this.txtforkey = txtforkey;
    }
}
