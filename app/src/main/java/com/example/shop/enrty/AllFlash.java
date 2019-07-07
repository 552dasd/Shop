package com.example.shop.enrty;


import java.util.List;

public class AllFlash {


    /**
     * result : success
     * Flash : [{"id":1,"img":"http://nanhai655.cn:8080/img/title/1.jpg","money":2499},{"id":2,"img":"http://nanhai655.cn:8080/img/title/2.jpg","money":2199},{"id":3,"img":"http://nanhai655.cn:8080/img/title/3.png","money":1699},{"id":4,"img":"http://nanhai655.cn:8080/img/title/4.jpg","money":999},{"id":5,"img":"http://nanhai655.cn:8080/img/title/5.jpg","money":1499},{"id":6,"img":"http://nanhai655.cn:8080/img/title/6.jpg","money":1999},{"id":7,"img":"http://nanhai655.cn:8080/img/title/a.jpg","money":2999},{"id":8,"img":"http://nanhai655.cn:8080/img/title/a.jpg","money":null}]
     */

    private String result;
    private List<FlashBean> Flash;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<FlashBean> getFlash() {
        return Flash;
    }

    public void setFlash(List<FlashBean> Flash) {
        this.Flash = Flash;
    }

    public static class FlashBean {
        /**
         * id : 1
         * img : http://nanhai655.cn:8080/img/title/1.jpg
         * money : 2499
         */

        private int id;
        private String img;
        private int money;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
