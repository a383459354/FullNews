package com.fullnews.model;

import android.content.Context;
import android.util.Log;

import com.fullnews.entity.BookDetailsBeans;
import com.fullnews.entity.BookHomeBeans;
import com.fullnews.presenter.BookDetailsList;
import com.fullnews.presenter.BookHomeList;
import com.fullnews.presenter.GetDataListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class BookGsonData {

    private DataModel dataModel;

    private List<BookHomeBeans.DataBean.PhBean.BookListBean> bookHomeData;

    public BookGsonData(Context context) {
        bookHomeData = new ArrayList<>();
        dataModel = new DataModel(context);
    }


    /**
     * 解析小说首页的数据
     *
     * @param url
     * @param bookHomeList
     * @param position
     */
    public void ParseBookData(String url, final BookHomeList bookHomeList, final int position) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BookHomeBeans bookHomeBeans = gson.fromJson(data, BookHomeBeans.class);
                bookHomeData = bookHomeBeans.getData().getPh().getBook_list();
                if (position == 1) {
                    bookHomeList.getBookDataList(bookHomeData);
                } else {
                    bookHomeList.getBookMoreDataList(bookHomeData);
                }
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }

    /**
     * 小说详情页数据
     */
    public void ParseBookDetailsData(String url, final BookDetailsList bookDetailsList) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BookDetailsBeans beans = gson.fromJson(data, BookDetailsBeans.class);
                Log.d("beansdata",beans.getData().toString());
                bookDetailsList.getBookDetailsData(beans.getData());
            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }
}
