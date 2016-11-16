package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class HumorBeans {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String index;

        private List<ArticleBean> article;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {
            private String summary;
            private String source_name;
            private String grab_time;

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getSource_name() {
                return source_name;
            }

            public void setSource_name(String source_name) {
                this.source_name = source_name;
            }

            public String getGrab_time() {
                return grab_time;
            }

            public void setGrab_time(String grab_time) {
                this.grab_time = grab_time;
            }
        }
    }
}
