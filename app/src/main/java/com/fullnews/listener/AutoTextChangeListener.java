package com.fullnews.listener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.fullnews.entity.BookSeekAutoBeans;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookSeekAutoList;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public abstract class AutoTextChangeListener implements TextWatcher, BookSeekAutoList {

    private Context mContext;

    public AutoTextChangeListener(Context context){
        this.mContext=context;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d("autoText-----","开始的值------"+s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("autoText-----","中间改变后的值----"+s.toString());
        String url = null;
        try {
            url = URLEncoder.encode(s.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BookGsonData bookGsonData=new BookGsonData(mContext);
        bookGsonData.ParseBookSeekAutoData("http://api.zhuishushenqi.com/book/auto-complete?query="+url,this);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("autoText-----","结束的值----"+s.toString());
    }

    @Override
    public void getBookSeekAutoData(BookSeekAutoBeans beans) {
        autoPrompting(beans.getKeywords());
        Log.d("autoText-----","获取的值----"+beans.getKeywords().toString());
    }

    public abstract void autoPrompting(List<String> data);
}
