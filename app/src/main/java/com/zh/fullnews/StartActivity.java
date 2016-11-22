package com.zh.fullnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fullnews.model.HomeNavigationData;

public class StartActivity extends AppCompatActivity{

    private HomeNavigationData homeNavigationData;
    private int channelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        GetData();

    }

//    private void initChannelData() {
//        homeNavigationData=new HomeNavigationData(this,this);
//        homeNavigationData.initChannel();
//    }
//
//    private void GetData() {
//        String url="http://www.yidianzixun.com/api/q/?path=user|get-info&t=1479191156165";
//        GetGsonData data=new GetGsonData(this);
//        data.ParseNewsChannelData(url,this);
//    }
//
//    @Override
//    public void getNewsChannelList(List<YidianChannelBeans.UserChannelsBean> dataList, int uesrId) {
//        Log.d("----------","-----"+dataList.size()+"-----");
//        this.channelId=uesrId;
//        this.channelData=dataList;
//        initChannelData();
//    }
//
//    @Override
//    public void NavigationData(SharedPreferences item, SharedPreferences ortherItem) {
//
//    }
//
//    @Override
//    public void NavigationNull() {
//        homeNavigationData.setChannel(channelId);
//        homeNavigationData.setNavigetionData(channelData);
//        startActivityForResult(new Intent(StartActivity.this,MainActivity.class),7);
//        finish();
//    }
//
//    @Override
//    public void ChannelData(int id) {
//        if (id==channelId){
//
//        }else{
//            homeNavigationData.setChannel(channelId);
//            homeNavigationData.setNavigetionData(channelData);
//        }
//        startActivityForResult(new Intent(StartActivity.this,MainActivity.class),7);
//        finish();
//    }
}
