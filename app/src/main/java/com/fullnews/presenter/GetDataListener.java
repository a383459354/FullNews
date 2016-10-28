package com.fullnews.presenter;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public interface GetDataListener {

    /**
     * 获取成功
     */
    public void success(String data);

    /**
     * 获取失败
     */
    public void failure(String errorMsg);
}
