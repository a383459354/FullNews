package com.fullnews.presenter;

import com.fullnews.entity.NewsBeans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public interface NewsList {

    /**
     * 获取新闻列表
     */
    public void getNewsDataList(List<NewsBeans.ListBean> dataList);
}
