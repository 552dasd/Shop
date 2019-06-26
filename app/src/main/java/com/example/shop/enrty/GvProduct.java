package com.example.shop.enrty;

public class GvProduct {
    int gvImgPro;
    String gvImgProName;
    String gvProPre;
    int gvProNewPrice;
    int gvProOldPrice;
    int btnGo;

    public GvProduct(int gvImgPro, String gvImgProName, String gvProPre, int gvProNewPrice, int gvProOldPrice) {
        this.gvImgPro = gvImgPro;
        this.gvImgProName = gvImgProName;
        this.gvProPre = gvProPre;
        this.gvProNewPrice = gvProNewPrice;
        this.gvProOldPrice = gvProOldPrice;
    }

    public GvProduct(int gvImgPro, String gvImgProName, String gvProPre, int gvProNewPrice, int gvProOldPrice, int btnGo) {
        this.gvImgPro = gvImgPro;
        this.gvImgProName = gvImgProName;
        this.gvProPre = gvProPre;
        this.gvProNewPrice = gvProNewPrice;
        this.gvProOldPrice = gvProOldPrice;
        this.btnGo = btnGo;
    }

    public int getGvImgPro() {
        return gvImgPro;
    }

    public void setGvImgPro(int gvImgPro) {
        this.gvImgPro = gvImgPro;
    }

    public String getGvImgProName() {
        return gvImgProName;
    }

    public void setGvImgProName(String gvImgProName) {
        this.gvImgProName = gvImgProName;
    }

    public String getGvProPre() {
        return gvProPre;
    }

    public void setGvProPre(String gvProPre) {
        this.gvProPre = gvProPre;
    }

    public int getGvProNewPrice() {
        return gvProNewPrice;
    }

    public void setGvProNewPrice(int gvProNewPrice) {
        this.gvProNewPrice = gvProNewPrice;
    }


    public int getGvProOldPrice() {
        return gvProOldPrice;
    }

    public void setGvProOldPrice(int gvProOldPrice) {
        this.gvProOldPrice = gvProOldPrice;
    }

    public int getBtnGo() {
        return btnGo;
    }

    public void setBtnGo(int btnGo) {
        this.btnGo = btnGo;
    }
}
