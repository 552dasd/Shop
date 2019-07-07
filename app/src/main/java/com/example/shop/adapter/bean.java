package com.example.shop.adapter;

public class bean {
  private   int price;

    @Override
    public String toString() {
        return "bean{" +
                "price=" + price +
                '}';
    }

    public bean(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
