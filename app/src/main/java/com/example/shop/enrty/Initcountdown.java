package com.example.shop.enrty;

import java.util.List;

public class Initcountdown {


    /**
     * result : success
     * list : [{"id":1,"name":"限时抢购","time":"0天0小时0分钟0秒","second":"0"}]
     */

    private String result;
    private List<ListBean> list;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * name : 限时抢购
         * time : 0天0小时0分钟0秒
         * second : 0
         */

        private int id;
        private String name;
        private String time;
        private String second;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }
    }
}
