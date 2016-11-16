package com.fullnews.presenter;

import com.fullnews.entity.HumorBeans;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public interface HumorList {

    /**
     * 获取幽默段子列表
     */
    public void getHumorDataList(List<HumorBeans.DataBean.ArticleBean> dataList, String index);

    /**
     * 获取更多幽默段子列表
     */
    public void getHumorMoreDataList(List<HumorBeans.DataBean.ArticleBean> dataList,String index);
}
