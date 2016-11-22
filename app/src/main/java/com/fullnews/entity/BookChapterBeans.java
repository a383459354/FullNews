package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class BookChapterBeans {

    private MixTocBean mixToc;
    private boolean ok;

    public MixTocBean getMixToc() {
        return mixToc;
    }

    public void setMixToc(MixTocBean mixToc) {
        this.mixToc = mixToc;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class MixTocBean {
        private String _id;
        private String book;
        private int chaptersCount1;
        private String chaptersUpdated;
        private String updated;

        /**
         * title : 1.第1章 北灵院
         * link : http://www.hunhun520.com/book/dazhuzai/4628128.html
         * unreadble : false
         */

        private List<ChaptersBean> chapters;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }

        public int getChaptersCount1() {
            return chaptersCount1;
        }

        public void setChaptersCount1(int chaptersCount1) {
            this.chaptersCount1 = chaptersCount1;
        }

        public String getChaptersUpdated() {
            return chaptersUpdated;
        }

        public void setChaptersUpdated(String chaptersUpdated) {
            this.chaptersUpdated = chaptersUpdated;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public List<ChaptersBean> getChapters() {
            return chapters;
        }

        public void setChapters(List<ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        public static class ChaptersBean {
            private String title;
            private String link;
            private boolean unreadble;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public boolean isUnreadble() {
                return unreadble;
            }

            public void setUnreadble(boolean unreadble) {
                this.unreadble = unreadble;
            }
        }
    }
}
