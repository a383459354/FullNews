package com.fullnews.model;

import android.content.Context;
import android.util.Log;

import com.fullnews.entity.NewsBeans;
import com.fullnews.presenter.GetDataListener;
import com.fullnews.presenter.NewsList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class GetGsonData {

    private NewsModel newsModel;
    private NewsList newsList;
    private Context context;
    private List<NewsBeans.ListBean> list;
    private String nextUrl;

    public GetGsonData(Context context, NewsList newsList) {
        this.context = context;
        newsModel = new NewsModel(context);
        this.newsList = newsList;
        list = new ArrayList<>();
    }

    public void ParseData(String url) {
        newsModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                NewsBeans newsBeans=gson.fromJson(data,NewsBeans.class);
                list=newsBeans.getList();
                Log.d("listsize", list.size() + "");
                newsList.getNewsDataList(list);
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }

}
