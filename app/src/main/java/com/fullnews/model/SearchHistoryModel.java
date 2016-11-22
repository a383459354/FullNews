package com.fullnews.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.fullnews.presenter.BookHistoryPresenter;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class SearchHistoryModel {

    private Context mContext;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SearchHistoryModel(Context context) {
        this.mContext = context;
        preferences = mContext.getSharedPreferences("SearchHistory", Context.MODE_PRIVATE);
        editor = mContext.getSharedPreferences("SearchHistory", Context.MODE_PRIVATE).edit();
    }

    public void setSearchHistoryData(String seekName) {
        String dataStr=preferences.getString("seekName", "");
        String[] history = dataStr.split(",");
        for (int i = 0; i < history.length; i++) {
            if (history[i].equals(seekName)) {
                return;
            }
        }
        dataStr=seekName + ","+dataStr;
        editor.putString("seekName", dataStr);
        editor.commit();
    }

    public void getSearchHistoryData(BookHistoryPresenter bookHistoryPresenter) {
        bookHistoryPresenter.getHistoryData(Arrays.asList(preferences.getString("seekName", "").split(",")));
    }

    public void deleteSearchHistoryData() {
        editor.clear();
        editor.commit();
    }
}
