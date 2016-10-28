package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class NewsBeans {

    /**
     * id : 1
     * title : 这些人当选总统后真的把对手关进了监狱
     * laiyuan : 腾讯新闻
     * url : http://app.myzaker.com/news/article.php?pk=57fb61689490cbf15c000069
     * time : 6小时前
     * img_01 : http://zkres2.myzaker.com/201610/57fb5670a07aecc97a04b251_640.jpg
     * img_02 : http://zkres1.myzaker.com/201610/57fb5670a07aecc97a04b252_640.jpg
     * img_03 : http://zkres1.myzaker.com/201610/57fb5670a07aecc97a04b253_640.jpg
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int id;
        private String title;
        private String laiyuan;
        private String url;
        private String time;
        private String img_01;
        private String img_02;
        private String img_03;

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

        public String getLaiyuan() {
            return laiyuan;
        }

        public void setLaiyuan(String laiyuan) {
            this.laiyuan = laiyuan;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImg_01() {
            return img_01;
        }

        public void setImg_01(String img_01) {
            this.img_01 = img_01;
        }

        public String getImg_02() {
            return img_02;
        }

        public void setImg_02(String img_02) {
            this.img_02 = img_02;
        }

        public String getImg_03() {
            return img_03;
        }

        public void setImg_03(String img_03) {
            this.img_03 = img_03;
        }
    }
}
