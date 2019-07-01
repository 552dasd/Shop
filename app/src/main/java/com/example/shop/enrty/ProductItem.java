package com.example.shop.enrty;

public class ProductItem {
    String imgProduct;
    int newPrice;
    int oldPrice;

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }



    public ProductItem(String imgProduct, int newPrice, int oldPrice) {
        this.imgProduct = imgProduct;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }



    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }


}
