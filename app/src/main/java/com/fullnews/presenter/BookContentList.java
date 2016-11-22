package com.fullnews.presenter;

import com.fullnews.entity.BookContentBeans;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public interface BookContentList {

    /**
     * 获取下一章小声内容
     */
    public void getTopBookContent(BookContentBeans beans);

    /**
     * 获取小声内容
     */
    public void getBookContent(BookContentBeans beans);

    /**
     * 获取下一章小声内容
     */
    public void getNextBookContent(BookContentBeans beans);
}
