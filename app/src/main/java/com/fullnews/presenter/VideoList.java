package com.fullnews.presenter;

import com.fullnews.entity.WYVideoBeans;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public interface VideoList {
    /**
     * 获取视频列表
     */
    public void getVideoDataList(List<WYVideoBeans.V9LG4B3A0Bean> dataList);

    /**
     * 获取更多视频列表
     */
    public void getVideoMoreDataList(List<WYVideoBeans.V9LG4B3A0Bean> dataList);
}
