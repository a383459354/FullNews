package com.fullnews.entity;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class BookDetailsBeans {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cover : http://oss-asq-img.11222.cn/bcv/middle/201604261230511812.jpg
         * class_name : 大陆
         * author_name : 乱
         * introduction :
         * book_info : 一觉醒来，世界大变。熟悉的高中传授的是魔法，告诉大家要成为一名出色的魔法师。居住的都市之外游荡着袭击人类的魔物妖兽，虎视眈眈。
         * author_id : 1121
         * state : 1
         * id : 5546147
         * chapterid : 709716
         * topic : 第1358章 反其道而行
         * full_price_wap : 0.0
         * date_updated : 2016-11-16 03:00:03
         * size : 71550
         * bookname : 全职法师
         * jd_url : http://wap.cmread.com/r/409651794/409654099/index.htm?page=1&cm=M3080044&fr=89
         */

        private BkBean bk;

        public BkBean getBk() {
            return bk;
        }

        public void setBk(BkBean bk) {
            this.bk = bk;
        }

        public static class BkBean {
            private String cover;
            private String class_name;
            private String author_name;
            private String introduction;
            private String book_info;
            private String author_id;
            private String state;
            private String id;
            private String chapterid;
            private String topic;
            private String date_updated;
            private int size;
            private String bookname;
            private String jd_url;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getBook_info() {
                return book_info;
            }

            public void setBook_info(String book_info) {
                this.book_info = book_info;
            }

            public String getAuthor_id() {
                return author_id;
            }

            public void setAuthor_id(String author_id) {
                this.author_id = author_id;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getChapterid() {
                return chapterid;
            }

            public void setChapterid(String chapterid) {
                this.chapterid = chapterid;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public String getDate_updated() {
                return date_updated;
            }

            public void setDate_updated(String date_updated) {
                this.date_updated = date_updated;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getBookname() {
                return bookname;
            }

            public void setBookname(String bookname) {
                this.bookname = bookname;
            }

            public String getJd_url() {
                return jd_url;
            }

            public void setJd_url(String jd_url) {
                this.jd_url = jd_url;
            }
        }
    }
}
