package com.fullnews.model;

import android.content.Context;

import com.fullnews.entity.HumorBeans;
import com.fullnews.entity.JiaodianBeans;
import com.fullnews.entity.PictrueBeans;
import com.fullnews.entity.WYVideoBeans;
import com.fullnews.presenter.GetDataListener;
import com.fullnews.presenter.HumorList;
import com.fullnews.presenter.NewsList;
import com.fullnews.presenter.PictrueList;
import com.fullnews.presenter.VideoList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class GetGsonData {

    private DataModel dataModel;
    private Context context;
    private List<JiaodianBeans.DataBean> newsData;
    private List<WYVideoBeans.V9LG4B3A0Bean> wyVideoData;
    private List<PictrueBeans.DataBean.ArticleBean> pictrueData;
    private List<HumorBeans.DataBean.ArticleBean> humorData;


    public GetGsonData(Context context) {
        this.context = context;
        dataModel = new DataModel(context);
        newsData = new ArrayList<>();
        wyVideoData = new ArrayList<>();
        pictrueData = new ArrayList<>();
        humorData = new ArrayList<>();
    }

    public void ParseData(String url, final NewsList newsList, final int position) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                JiaodianBeans beans = gson.fromJson(data, JiaodianBeans.class);
                newsData = beans.getData();
                if (position == 1) {
                    newsList.getNewsDataList(newsData);
                } else {
                    newsList.getNewsMoreDataList(newsData);
                }
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }


    public void ParseVideoData(String url, final VideoList videoList, int position) {
        ParseMoreVideoData(url, videoList, position);
    }

    public void ParseMoreVideoData(String url, final VideoList videoList, final int position) {
        dataModel.getVideolist(url, new GetDataListener() {
            @Override
            public void success(String data) {

                Gson gson = new Gson();
                WYVideoBeans wyVideoBeans = gson.fromJson(data, WYVideoBeans.class);
                wyVideoData = wyVideoBeans.getV9LG4B3A0();
                if (position == 0) {
                    videoList.getVideoDataList(wyVideoData);
//                    videoData.clear();
                } else {
                    videoList.getVideoMoreDataList(wyVideoData);
//                    videoData.clear();
                }
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }

    public void ParseHumorData(String url, final HumorList humorList, final int position) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                HumorBeans humorBeans = gson.fromJson(data, HumorBeans.class);
                humorData = humorBeans.getData().getArticle();
                if (position == 1) {
                    humorList.getHumorDataList(humorData, humorBeans.getData().getIndex().toString());
                } else {
                    humorList.getHumorMoreDataList(humorData, humorBeans.getData().getIndex().toString());
                }
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }

    public void ParsePictrueData(String url, final PictrueList pictrueList, final int position) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                PictrueBeans pictrueBeans = gson.fromJson(data, PictrueBeans.class);
                pictrueData = pictrueBeans.getData().getArticle();
                if (position == 1) {
                    pictrueList.getPictrueDataList(pictrueData, pictrueBeans.getData().getIndex().toString());
                } else {
                    pictrueList.getPictrueMoreDataList(pictrueData, pictrueBeans.getData().getIndex().toString());
                }
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }


}
