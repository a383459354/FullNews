package com.fullnews.model;

import android.support.v4.app.Fragment;

import com.fullnews.presenter.BookChannelPresenter;
import com.fullnews.ui.fragment.BookClassifyContentFragment;
import com.fullnews.ui.fragment.BookStoreRankingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class BookChannel {

    public void channel(String sex,BookChannelPresenter bcPresenter) {
        List<String> item = new ArrayList<>();
        List<Fragment> tabFragments = new ArrayList<>();
        item.add("热门");
        item.add("留存");
        item.add("完结");
        item.add("月榜");
        item.add("潜力");
        item.add("其他");
        for (int i = 0; i < item.size(); i++) {
            tabFragments.add(BookStoreRankingFragment.newInstance(item.get(i),sex));
        }
        bcPresenter.getBookChannelData(tabFragments,item);
    }

    public void BookClassifyChannel(String classify,String name,BookChannelPresenter bookChannelPresenter){
        List<String> item = new ArrayList<>();
        List<Fragment> tabFragments = new ArrayList<>();
        item.add("最新");
        item.add("热门");
        item.add("完结");
        item.add("好评");
        for (int i = 0; i < item.size(); i++) {
            tabFragments.add(BookClassifyContentFragment.newInstance(item.get(i),name,classify));
        }
        bookChannelPresenter.getBookChannelData(tabFragments,item);
    }


}
