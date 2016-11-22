package com.fullnews.presenter;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public interface BookChannelPresenter {

    /**
     * 小说频道
     */
    public void getBookChannelData(List<Fragment> channelFragment, List<String> channelItem);
}
