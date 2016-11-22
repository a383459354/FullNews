package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class BookClassifyContentBeans {

    /**
     * _id : 50c53a578380e4f81500000f
     * author : 星辉
     * cover : /agent/http://static.zongheng.com/upload/cover/2016/03/1458531433337.jpg
     * shortIntro : 徐青本是一位率性冲动的平凡少年，一次不平凡的际遇让他的双眼拥有奇特的能力，从此他的生活发生了不可思议的改变，财富唾手可得，赌坛王者横空出世，纵意人生，笑傲都市江...
     * title : 透视之眼
     * site : zhuishuvip
     * majorCate : 都市
     * latelyFollower : 29133
     * latelyFollowerBase : 0
     * minRetentionRatio : 0
     * retentionRatio : 61.63
     * lastChapter : 第2536章 传承不息(大结局)
     * tags : ["都市生活","美女如云","架空","异能","奇遇"]
     */

    private List<BooksBean> books;

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        private String _id;
        private String author;
        private String cover;
        private String shortIntro;
        private String title;
        private String site;
        private String majorCate;
        private String latelyFollower;
        private String latelyFollowerBase;
        private String minRetentionRatio;
        private String retentionRatio;
        private String lastChapter;
        private List<String> tags;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getShortIntro() {
            return shortIntro;
        }

        public void setShortIntro(String shortIntro) {
            this.shortIntro = shortIntro;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getMajorCate() {
            return majorCate;
        }

        public void setMajorCate(String majorCate) {
            this.majorCate = majorCate;
        }

        public String getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(String latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public String getLatelyFollowerBase() {
            return latelyFollowerBase;
        }

        public void setLatelyFollowerBase(String latelyFollowerBase) {
            this.latelyFollowerBase = latelyFollowerBase;
        }

        public String getMinRetentionRatio() {
            return minRetentionRatio;
        }

        public void setMinRetentionRatio(String minRetentionRatio) {
            this.minRetentionRatio = minRetentionRatio;
        }

        public String getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(String retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
