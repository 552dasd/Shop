package com.example.shop.enrty;

import java.util.List;

public class Init {

    /**
     * result : success
     * list : [{"id":1,"new1":"超级手机上线啦~快来抢购吧~","new2":"大量笔记本现货白菜价，点击查看详情","new3":"新品上线，快来围观呀","carouselName1":"秒杀","carouselName2":"抢购","carouselName3":"鲜果计划","carouselName4":"超值有礼","carouselImage1":"http://nanhai655.cn:8080/img/title/a.jpg","carouselImage2":"http://nanhai655.cn:8080/img/title/b.jpg","carouselImage3":"http://nanhai655.cn:8080/img/title/c.jpg","carouselImage4":"http://nanhai655.cn:8080/img/title/d.jpg","bigImage1":"http://nanhai655.cn:8080/img/title/e.jpg","bigImage2":"http://nanhai655.cn:8080/img/title/f.jpg","bigImage3":"http://nanhai655.cn:8080/img/title/g.jpg"}]
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
         * new1 : 超级手机上线啦~快来抢购吧~
         * new2 : 大量笔记本现货白菜价，点击查看详情
         * new3 : 新品上线，快来围观呀
         * carouselName1 : 秒杀
         * carouselName2 : 抢购
         * carouselName3 : 鲜果计划
         * carouselName4 : 超值有礼
         * carouselImage1 : http://nanhai655.cn:8080/img/title/a.jpg
         * carouselImage2 : http://nanhai655.cn:8080/img/title/b.jpg
         * carouselImage3 : http://nanhai655.cn:8080/img/title/c.jpg
         * carouselImage4 : http://nanhai655.cn:8080/img/title/d.jpg
         * bigImage1 : http://nanhai655.cn:8080/img/title/e.jpg
         * bigImage2 : http://nanhai655.cn:8080/img/title/f.jpg
         * bigImage3 : http://nanhai655.cn:8080/img/title/g.jpg
         */

        private int id;
        private String new1;
        private String new2;
        private String new3;
        private String carouselName1;
        private String carouselName2;
        private String carouselName3;
        private String carouselName4;
        private String carouselImage1;
        private String carouselImage2;
        private String carouselImage3;
        private String carouselImage4;
        private String bigImage1;
        private String bigImage2;
        private String bigImage3;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNew1() {
            return new1;
        }

        public void setNew1(String new1) {
            this.new1 = new1;
        }

        public String getNew2() {
            return new2;
        }

        public void setNew2(String new2) {
            this.new2 = new2;
        }

        public String getNew3() {
            return new3;
        }

        public void setNew3(String new3) {
            this.new3 = new3;
        }

        public String getCarouselName1() {
            return carouselName1;
        }

        public void setCarouselName1(String carouselName1) {
            this.carouselName1 = carouselName1;
        }

        public String getCarouselName2() {
            return carouselName2;
        }

        public void setCarouselName2(String carouselName2) {
            this.carouselName2 = carouselName2;
        }

        public String getCarouselName3() {
            return carouselName3;
        }

        public void setCarouselName3(String carouselName3) {
            this.carouselName3 = carouselName3;
        }

        public String getCarouselName4() {
            return carouselName4;
        }

        public void setCarouselName4(String carouselName4) {
            this.carouselName4 = carouselName4;
        }

        public String getCarouselImage1() {
            return carouselImage1;
        }

        public void setCarouselImage1(String carouselImage1) {
            this.carouselImage1 = carouselImage1;
        }

        public String getCarouselImage2() {
            return carouselImage2;
        }

        public void setCarouselImage2(String carouselImage2) {
            this.carouselImage2 = carouselImage2;
        }

        public String getCarouselImage3() {
            return carouselImage3;
        }

        public void setCarouselImage3(String carouselImage3) {
            this.carouselImage3 = carouselImage3;
        }

        public String getCarouselImage4() {
            return carouselImage4;
        }

        public void setCarouselImage4(String carouselImage4) {
            this.carouselImage4 = carouselImage4;
        }

        public String getBigImage1() {
            return bigImage1;
        }

        public void setBigImage1(String bigImage1) {
            this.bigImage1 = bigImage1;
        }

        public String getBigImage2() {
            return bigImage2;
        }

        public void setBigImage2(String bigImage2) {
            this.bigImage2 = bigImage2;
        }

        public String getBigImage3() {
            return bigImage3;
        }

        public void setBigImage3(String bigImage3) {
            this.bigImage3 = bigImage3;
        }
    }
}

