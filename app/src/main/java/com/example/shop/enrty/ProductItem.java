package com.example.shop.enrty;

public class ProductItem {
    int imgProduct;
    int newPrice;

    public int getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

    int oldPrice;

    public ProductItem(int imgProduct, int newPrice, int oldPrice) {
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
