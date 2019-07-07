package com.example.shop.enrty;

import java.util.List;

public class Bean {

    private List<UserListBean> UserList;

    public List<UserListBean> getUserList() {
        return UserList;
    }

    public void setUserList(List<UserListBean> UserList) {
        this.UserList = UserList;
    }

    public static class UserListBean {
        /**
         * id : 1
         * commodityid : 14
         * userid : 1
         * name : 创客（CKCOM）C1 联通4G手机 5.5英寸 指纹识别 1300万+500万像素 白色
         * money : 99900
         * img : http://nanhai655.cn:8080/img/title/1.jpg
         * count : 2
         */

        private int id;
        private int commodityid;
        private int userid;
        private String name;
        private int money;
        private String img;
        private int count;

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
