package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class WYVideoBeans {

    private List<V9LG4B3A0Bean> V9LG4B3A0;

    public List<V9LG4B3A0Bean> getV9LG4B3A0() {
        return V9LG4B3A0;
    }

    public void setV9LG4B3A0(List<V9LG4B3A0Bean> V9LG4B3A0) {
        this.V9LG4B3A0 = V9LG4B3A0;
    }

    public static class V9LG4B3A0Bean {
        private String cover;
        private String title;
        private int playCount;
        private String mp4_url;
        private int length;
        private int playersize;
        private String ptime;
        private String topicName;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
