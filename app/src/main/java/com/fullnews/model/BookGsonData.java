package com.fullnews.model;

import android.content.Context;
import android.util.Log;

import com.fullnews.entity.BookContentBeans;
import com.fullnews.entity.BookChapterBeans;
import com.fullnews.entity.BookClassifyBeans;
import com.fullnews.entity.BookClassifyContentBeans;
import com.fullnews.entity.BookDetailsBeans;
import com.fullnews.entity.BookHomeBeans;
import com.fullnews.entity.BookHotSeekBeans;
import com.fullnews.entity.BookSeekAutoBeans;
import com.fullnews.presenter.BookChapterList;
import com.fullnews.presenter.BookClassifyContentList;
import com.fullnews.presenter.BookClassifyList;
import com.fullnews.presenter.BookContentList;
import com.fullnews.presenter.BookDetailsList;
import com.fullnews.presenter.BookHomeList;
import com.fullnews.presenter.BookHotSeekList;
import com.fullnews.presenter.BookSeekAutoList;
import com.fullnews.presenter.GetDataListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class BookGsonData {

    private DataModel dataModel;

    private List<BookHomeBeans.RankingBean.BooksBean> bookHomeData;

    public BookGsonData(Context context) {
        bookHomeData = new ArrayList<>();
        dataModel = new DataModel(context);
    }


    /**
     * 解析小说首页的数据
     *
     * @param url
     * @param bookHomeList
     */
    public void ParseBookData(String url, final BookHomeList bookHomeList) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BookHomeBeans bookHomeBeans = gson.fromJson(data, BookHomeBeans.class);
                bookHomeData = bookHomeBeans.getRanking().getBooks();
                bookHomeList.getBookDataList(bookHomeData);
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
                Log.d("url++", data.toString());
                BookDetailsBeans beans = gson.fromJson(data, BookDetailsBeans.class);
                bookDetailsList.getBookDetailsData(beans);
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

    /**
     * 小说分类列表信息
     */
    public void ParseBookClassifyData(String url, final BookClassifyList bookClassifyList) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Log.d("url++", data.toString());
                BookClassifyBeans beans = gson.fromJson(data, BookClassifyBeans.class);
                bookClassifyList.getBookClassifyData(beans);
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

    /**
     * 小说分类信息内容列表
     */
    public void ParseBookClassifyContentData(String url, final BookClassifyContentList bookClassifyContentList, final int position) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Log.d("url++", data.toString());
                BookClassifyContentBeans beans = gson.fromJson(data, BookClassifyContentBeans.class);
                if (position == 1) {
                    bookClassifyContentList.getBookClassifyContentData(beans);
                } else {
                    bookClassifyContentList.getMoreBookClassifyContentData(beans);
                }
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

    /**
     * 小说搜索自动提示列表
     */
    public void ParseBookSeekAutoData(String url, final BookSeekAutoList bookSeekAutoList) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Log.d("url++", data.toString());
                BookSeekAutoBeans beans = gson.fromJson(data, BookSeekAutoBeans.class);
                bookSeekAutoList.getBookSeekAutoData(beans);
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

    /**
     * 小说热门搜索列表
     */
    public void ParsehotSeekData(String url, final BookHotSeekList bookHotSeekList) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Log.d("url++", data.toString());
                BookHotSeekBeans beans = gson.fromJson(data, BookHotSeekBeans.class);
                bookHotSeekList.getBookHotSeekData(beans);
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

    /**
     * 小说章节列表
     */
    public void ParseChapterData(String url, final BookChapterList bookChapterList) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Log.d("url++", data.toString());
                BookChapterBeans beans = gson.fromJson(data, BookChapterBeans.class);
                bookChapterList.getBookChapterData(beans);
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

    /**
     * 获取小说内容
     */
    public void ParseBookContentData(String url, final BookContentList bookContentList, final int position) {
        dataModel.getNewslist(url, new GetDataListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Log.d("url++", data.toString());
                BookContentBeans beans = gson.fromJson(data, BookContentBeans.class);
                if (position == 0) {
                    bookContentList.getTopBookContent(beans);
                } else if (position == 1) {
                    bookContentList.getBookContent(beans);
                } else if (position == 2) {
                    bookContentList.getNextBookContent(beans);
                }
            }

            @Override
            public void failure(String errorMsg) {
                Log.d("url++", errorMsg.toString());
            }
        });
    }

}
