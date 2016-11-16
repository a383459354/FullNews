package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class JiaodianBeans {


    /**
     * title : 售价超3100元：中国特供机三星C7现身美国零售
     * titleurl : http://api.myjiaodian.com/phone/201611/86321.html
     * wburl :
     * username : kk61admin
     * userid : 1
     * thumbnail : http://i1.piimg.com/503018/14c82f837273eecft.jpg
     * titlepic : http://i1.piimg.com/503018/14c82f837273eecft.jpg
     * notimg :
     * newstime : 2016-11-15
     * timestamp : 2016-11-15
     * id : 86321
     * classid : 2
     * smalltext :
     * hide : 0
     * classname : 手机
     * tbname : news
     * onclick : 4
     * plnum : 0
     * resendnum : 0
     * ismember : 0
     * isfava : 0
     * writer : 书生
     * morepic : null
     * picnum : 1
     * userinfo : []
     * modelType : news
     * diggtop : 0
     * diggdown : 0
     * isurl : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String titleurl;
        private String titlepic;
        private String newstime;
        private String writer;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleurl() {
            return titleurl;
        }

        public void setTitleurl(String titleurl) {
            this.titleurl = titleurl;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }

        public String getNewstime() {
            return newstime;
        }

        public void setNewstime(String newstime) {
            this.newstime = newstime;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }
    }
}
