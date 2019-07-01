package com.example.shop.enrty;

public class Detail {
    int iv;
    String name;

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;

    public Detail(int iv, String name, String title) {
        this.iv = iv;
        this.name = name;
        this.title = title;
    }
}
