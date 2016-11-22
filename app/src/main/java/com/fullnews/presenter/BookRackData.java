package com.fullnews.presenter;

import com.fullnews.entity.ReadHistoryEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public interface BookRackData {
    /**
     * 从数据库中获取数据
     */
    public void getBookRackDB(List<ReadHistoryEntity> data);
}
