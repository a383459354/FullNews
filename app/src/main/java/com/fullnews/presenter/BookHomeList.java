package com.fullnews.presenter;

import com.fullnews.entity.BookHomeBeans;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public interface BookHomeList {

    /**
     * 获取小说列表
     */
    public void getBookDataList(List<BookHomeBeans.RankingBean.BooksBean> dataList);
}
