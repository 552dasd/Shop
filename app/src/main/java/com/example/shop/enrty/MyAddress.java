package com.example.shop.enrty;

import java.util.List;

public class MyAddress {
    /**
     * UserList : {"status":"1","info":"OK","infocode":"10000","province":"局域网","city":[],"adcode":[],"rectangle":[]}
     */

    private UserListBean UserList;

    public UserListBean getUserList() {
        return UserList;
    }

    public void setUserList(UserListBean UserList) {
        this.UserList = UserList;
    }

    public static class UserListBean {
        /**
         * status : 1
         * info : OK
         * infocode : 10000
         * province : 局域网
         * city : []
         * adcode : []
         * rectangle : []
         */

        private String status;
        private String info;
        private String infocode;
        private String province;
        private List<?> city;
        private List<?> adcode;
        private List<?> rectangle;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfocode() {
            return infocode;
        }

        public void setInfocode(String infocode) {
            this.infocode = infocode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<?> getCity() {
            return city;
        }

        public void setCity(List<?> city) {
            this.city = city;
        }

        public List<?> getAdcode() {
            return adcode;
        }

        public void setAdcode(List<?> adcode) {
            this.adcode = adcode;
        }

        public List<?> getRectangle() {
            return rectangle;
        }

        public void setRectangle(List<?> rectangle) {
            this.rectangle = rectangle;
        }
    }
}
