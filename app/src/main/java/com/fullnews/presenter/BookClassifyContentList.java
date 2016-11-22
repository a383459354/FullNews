package com.fullnews.presenter;

import com.fullnews.entity.BookClassifyContentBeans;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public interface BookClassifyContentList {
    /**
     * 获取小说分类内容信息
     */
    public void getBookClassifyContentData(BookClassifyContentBeans beans);

    /**
     * 获取小说分类更多内容信息
     */
    public void getMoreBookClassifyContentData(BookClassifyContentBeans beans);
}
