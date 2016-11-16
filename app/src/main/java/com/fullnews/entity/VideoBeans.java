package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class VideoBeans {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int video_id;
        private String video_title;
        private String video_url;
        private int video_time;
        private String video_read;
        private String video_img;
        private String video_laiyuan;
        private String video_pubDate;

        public int getVideo_id() {
            return video_id;
        }

        public void setVideo_id(int video_id) {
            this.video_id = video_id;
        }

        public String getVideo_title() {
            return video_title;
        }

        public void setVideo_title(String video_title) {
            this.video_title = video_title;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public int getVideo_time() {
            return video_time;
        }

        public void setVideo_time(int video_time) {
            this.video_time = video_time;
        }

        public String getVideo_read() {
            return video_read;
        }

        public void setVideo_read(String video_read) {
            this.video_read = video_read;
        }

        public String getVideo_img() {
            return video_img;
        }

        public void setVideo_img(String video_img) {
            this.video_img = video_img;
        }

        public String getVideo_laiyuan() {
            return video_laiyuan;
        }

        public void setVideo_laiyuan(String video_laiyuan) {
            this.video_laiyuan = video_laiyuan;
        }

        public String getVideo_pubDate() {
            return video_pubDate;
        }

        public void setVideo_pubDate(String video_pubDate) {
            this.video_pubDate = video_pubDate;
        }
    }
}
