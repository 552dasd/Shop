package com.example.shop.enrty;

import java.util.List;

public class Commodity {


    /**
     * result : success
     * Commodity : [{"id":1,"title":"new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待","sellPoint":"清仓！仅北京，武汉仓有货！","price":29900,"num":99999,"barcode":"","image":"http://nanhai655.cn:8080/img/title/1.jpg","cid":560,"status":1,"created":"2015-03-08T13:33:18.000+0000","updated":"2015-04-11T12:38:38.000+0000"}]
     * Commodity_image : [{"ip":1,"id":null,"img1":"http://nanhai655.cn:8080/img/title/2.jpg","img2":"http://nanhai655.cn:8080/img/title/4.jpg","img3":"http://nanhai655.cn:8080/img/title/1.jpg","title1":"超级手机上线了","title2":"超级手机大折扣","title3":"超级流畅"}]
     */

    private String result;
    private List<CommodityBean> Commodity;
    private List<CommodityImageBean> Commodity_image;

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

    public List<CommodityImageBean> getCommodity_image() {
        return Commodity_image;
    }

    public void setCommodity_image(List<CommodityImageBean> Commodity_image) {
        this.Commodity_image = Commodity_image;
    }

    public static class CommodityBean {
        /**
         * id : 1
         * title : new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待
         * sellPoint : 清仓！仅北京，武汉仓有货！
         * price : 29900
         * num : 99999
         * barcode :
         * image : http://nanhai655.cn:8080/img/title/1.jpg
         * cid : 560
         * status : 1
         * created : 2015-03-08T13:33:18.000+0000
         * updated : 2015-04-11T12:38:38.000+0000
         */

        private int id;
        private String title;
        private String sellPoint;
        private int price;
        private int num;
        private String barcode;
        private String image;
        private int cid;
        private int status;
        private String created;
        private String updated;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSellPoint() {
            return sellPoint;
        }

        public void setSellPoint(String sellPoint) {
            this.sellPoint = sellPoint;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }

    public static class CommodityImageBean {
        /**
         * ip : 1
         * id : null
         * img1 : http://nanhai655.cn:8080/img/title/2.jpg
         * img2 : http://nanhai655.cn:8080/img/title/4.jpg
         * img3 : http://nanhai655.cn:8080/img/title/1.jpg
         * title1 : 超级手机上线了
         * title2 : 超级手机大折扣
         * title3 : 超级流畅
         */

        private int ip;
        private Object id;
        private String img1;
        private String img2;
        private String img3;
        private String title1;
        private String title2;
        private String title3;

        public int getIp() {
            return ip;
        }

        public void setIp(int ip) {
            this.ip = ip;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getTitle1() {
            return title1;
        }

        public void setTitle1(String title1) {
            this.title1 = title1;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public String getTitle3() {
            return title3;
        }

        public void setTitle3(String title3) {
            this.title3 = title3;
        }
    }
}
