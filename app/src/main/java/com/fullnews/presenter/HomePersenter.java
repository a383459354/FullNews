package com.fullnews.presenter;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public interface HomePersenter {

    /**
     *sharedPreferences不为空
     */
    public void NavigationData(SharedPreferences item,SharedPreferences ortherItem);

    /**
     *sharedPreferences为空
     */
    public void NavigationNull();
}
