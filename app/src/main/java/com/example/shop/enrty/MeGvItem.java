package com.example.shop.enrty;

public class MeGvItem {
    int meImg;
    String meImgName;

    public MeGvItem(int meImg, String meImgName) {
        this.meImg = meImg;
        this.meImgName = meImgName;
    }

    public int getMeImg() {
        return meImg;
    }

    public void setMeImg(int meImg) {
        this.meImg = meImg;
    }

    public String getMeImgName() {
        return meImgName;
    }

    public void setMeImgName(String meImgName) {
        this.meImgName = meImgName;
    }
}
