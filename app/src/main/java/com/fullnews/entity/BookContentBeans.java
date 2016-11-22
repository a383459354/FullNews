package com.fullnews.entity;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class BookContentBeans {

    private boolean ok;

    private ChapterBean chapter;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public ChapterBean getChapter() {
        return chapter;
    }

    public void setChapter(ChapterBean chapter) {
        this.chapter = chapter;
    }

    public static class ChapterBean {
        private String title;
        private String body;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
