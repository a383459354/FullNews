package com.fullnews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullnews.adapter.VideoListAdapter;
import com.fullnews.entity.WYVideoBeans;
import com.fullnews.ui.activity.VideoPlayActivity;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.GetGsonData;
import com.fullnews.presenter.VideoList;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class VideoFragment extends Fragment implements VideoList, VideoListAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private VideoListAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private GetGsonData getGsonData;

    private List<WYVideoBeans.V9LG4B3A0Bean> mData;

    private int startIndex=2;

    public static VideoFragment newInstance(String content) {
        VideoFragment videoFragment = new VideoFragment();
        return videoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_video, null);
        initData();
        initView(contentView);
        return contentView;
    }

    //初始化界面
    private void initView(View view){
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_video);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {

                loadMoreData();
            }
        });
    }

    //初始化一开始加载的数据
    private void initData(){
        mData = new ArrayList<WYVideoBeans.V9LG4B3A0Bean>();
        getGsonData=new GetGsonData(getActivity());
        getGsonData.ParseVideoData("http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/0-10.html",this,0);
    }

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData(){
        startIndex+=10;
        Log.d("index---",startIndex+"--");
        getGsonData.ParseMoreVideoData("http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/"+startIndex+"-10.html",this,1);
        Log.d("index---","http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/"+startIndex+"-10.html");
    }

    @Override
    public void getVideoDataList(List<WYVideoBeans.V9LG4B3A0Bean> dataList) {
//        mAdapter = new MyAdapter(this,dataList);
        mData=dataList;
        Log.d("index---","size-"+mData.size());
        mAdapter=new VideoListAdapter(getActivity(),mData);
        mAdapter.setOnItemClickListener(this);
        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getVideoMoreDataList(List<WYVideoBeans.V9LG4B3A0Bean> dataList) {
        Log.d("index---","datalistsize-"+dataList.size());
        mData.addAll(dataList);
        Log.d("index---","moresize-"+mData.size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("+----+++++--",position+"---------------");
        Intent intent=new Intent(getActivity(),VideoPlayActivity.class);
        intent.putExtra("videoName",mData.get(position).getTitle());
        intent.putExtra("videoUrl",mData.get(position).getMp4_url());
        startActivityForResult(intent,2);
    }
}
