package com.example.shop.enrty;

import java.util.List;

public class UserShopping {

    /**
     * result : success
     * Commodity : [{"id":59,"commodityid":4,"userid":1,"name":"Horion 65S82 65英寸安卓智能全高清wifi网络平板液晶电视 黑色 带挂件","money":699900,"img":"http://nanhai655.cn:8080/img/title/4.jpg","count":3},{"id":60,"commodityid":5,"userid":1,"name":"Horion 65S82 65英寸安卓智能全高清wifi网络平板液晶电视 黑色 带底座+挂件","money":703900,"img":"http://nanhai655.cn:8080/img/title/5.jpg","count":2},{"id":61,"commodityid":54,"userid":1,"name":"海尔(Haier) LD65H9000H 65英寸4K超高清3D安卓WIFI智能LED液晶","money":1199900,"img":"http://nanhai655.cn:8080/img/title/4.jpg","count":1}]
     */

    private String result;
    private List<CommodityBean> Commodity;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<CommodityBean> getCommodity() {
        return Commodity;
    }

    public void setCommodity(List<CommodityBean> Commodity) {
        this.Commodity = Commodity;
    }

    public static class CommodityBean {
        /**
         * id : 59
         * commodityid : 4
         * userid : 1
         * name : Horion 65S82 65英寸安卓智能全高清wifi网络平板液晶电视 黑色 带挂件
         * money : 699900
         * img : http://nanhai655.cn:8080/img/title/4.jpg
         * count : 3
         */

        private int id;
        private int commodityid;
        private int userid;
        private String name;
        private int money;
        private String img;
        private int count;
        private boolean isCheck = false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(int commodityid) {
            this.commodityid = commodityid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
