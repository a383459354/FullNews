package com.fullnews.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public class HomeNavigationData {

    Context mContext;

    private HomePersenter homePersenter;

    private SharedPreferences.Editor editorItem;
    private SharedPreferences.Editor editorOrtherItem;
//    private SharedPreferences.Editor editorChannel;

//    private SharedPreferences channelId;
    private SharedPreferences preferencesItem;
    private SharedPreferences preferencesOrtherItem;

    public HomeNavigationData(Context context, HomePersenter homePersenter) {
        this.mContext = context;
        this.homePersenter = homePersenter;
//        channelId = mContext.getSharedPreferences("channelId", Context.MODE_PRIVATE);
        preferencesItem = mContext.getSharedPreferences("navigationItemData", Context.MODE_PRIVATE);
        preferencesOrtherItem = mContext.getSharedPreferences("navigationOrtherItemData", Context.MODE_PRIVATE);
    }

//    public void initChannel() {
//        if (channelId.getAll().size() > 0) {
//            homePersenter.ChannelData(channelId.getInt("cId", 0));
//        } else {
//            homePersenter.NavigationNull();
//        }
//    }
//
//    public void setChannel(int cId) {
//        editorChannel = mContext.getSharedPreferences("channelId", Context.MODE_PRIVATE).edit();
//        editorChannel.putInt("cId", cId);
//        editorChannel.commit();
//    }

    public void getNavigetionData() {
        if (preferencesItem.getAll().size() > 0) {
            homePersenter.NavigationData(preferencesItem, preferencesOrtherItem);
        } else {
            homePersenter.NavigationNull();
        }
    }

    public void setNavigetionData() {
        editorItem = mContext.getSharedPreferences("navigationItemData", Context.MODE_PRIVATE).edit();
        editorOrtherItem = mContext.getSharedPreferences("navigationOrtherItemData", Context.MODE_PRIVATE).edit();
        editorItem.clear();
        editorOrtherItem.clear();
        for (int i = 0; i < initItemData().length; i++) {
            editorItem.putString("item" + i, initItemData()[i]);
        }
        for (int i = 0; i < initItemOrtherData().length; i++) {
            editorOrtherItem.putString("ortherItem" + i, initItemOrtherData()[i]);
        }
        editorItem.commit();
        editorOrtherItem.commit();
    }

    private String[] initItemData() {
        String[] item = new String[]{"推荐", "热点", "手机", "互联网",  "汽车"};

        return item;
    }

    private String[] initItemOrtherData() {
        String[] ortherItem = new String[]{ "游戏", "电影" ,"电商"};
        return ortherItem;
    }


}
