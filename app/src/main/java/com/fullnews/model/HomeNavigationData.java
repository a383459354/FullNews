package com.fullnews.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.fullnews.presenter.HomePersenter;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public class HomeNavigationData {

    Context mContext;

    private HomePersenter homePersenter;

    private SharedPreferences.Editor editorItem;
    private SharedPreferences.Editor editorOrtherItem;
    private SharedPreferences preferencesItem;
    private SharedPreferences preferencesOrtherItem;

    public HomeNavigationData(Context context,HomePersenter homePersenter){
        this.mContext=context;
        this.homePersenter=homePersenter;
    }

    public void getNavigetionData(){
        preferencesItem=mContext.getSharedPreferences("navigationItemData",Context.MODE_PRIVATE);
        preferencesOrtherItem=mContext.getSharedPreferences("navigationOrtherItemData",Context.MODE_PRIVATE);
        if (preferencesItem.getAll().size()>0){
            homePersenter.NavigationData(preferencesItem,preferencesOrtherItem);
        }else{
            homePersenter.NavigationNull();
        }
    }

    public void setNavigetionData(){
        editorItem=mContext.getSharedPreferences("navigationItemData",Context.MODE_PRIVATE).edit();
        editorOrtherItem=mContext.getSharedPreferences("navigationOrtherItemData",Context.MODE_PRIVATE).edit();
        editorItem.clear();
        editorOrtherItem.clear();
        for (int i=0;i<initItemData().length;i++){
            editorItem.putString("item"+i,initItemData()[i]);
        }
        for (int i=0;i<initItemOrtherData().length;i++){
            editorOrtherItem.putString("ortherItem"+i,initItemOrtherData()[i]);
        }
        editorItem.commit();
        editorOrtherItem.commit();
    }

    private String[] initItemData(){
        String[] item=new String[]{"热点","科技","体育","娱乐","财经","旅游"};
        return item;
    }

    private String[] initItemOrtherData(){
        String[] ortherItem=new String[]{"热点","科技","体育","娱乐","财经","旅游"};
        return ortherItem;
    }


}
