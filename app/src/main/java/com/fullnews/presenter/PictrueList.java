package com.fullnews.presenter;

import com.fullnews.entity.PictrueBeans;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public interface PictrueList {

    /**
     * 获取新闻列表
     */
    public void getPictrueDataList(List<PictrueBeans.DataBean.ArticleBean> dataList,String index);

    /**
     * 获取更多新闻列表
     */
    public void getPictrueMoreDataList(List<PictrueBeans.DataBean.ArticleBean> dataList,String index);
}
