package com.fullnews.presenter;

import com.fullnews.entity.JiaodianBeans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public interface NewsList {

    /**
     * 获取新闻列表
     */
    public void getNewsDataList(List<JiaodianBeans.DataBean> dataList);

    /**
     * 获取更多新闻列表
     */
    public void getNewsMoreDataList(List<JiaodianBeans.DataBean> dataList);
}
