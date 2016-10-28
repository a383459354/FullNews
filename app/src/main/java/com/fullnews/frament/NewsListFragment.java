package com.fullnews.frament;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullnews.adapter.NewsListAdapter;
import com.fullnews.entity.NewsBeans;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.GetGsonData;
import com.fullnews.presenter.NewsList;
import com.fullnews.view.MyDecoration;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,NewsList, NewsListAdapter.OnItemClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private GetGsonData getGsonData;

    private String webUrl;

    private List<NewsBeans.ListBean> mData;

    private Context mContext;

    public static NewsListFragment newInstance(String content){
        NewsListFragment newsListFragment=new NewsListFragment();
        return newsListFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_list,null);
        mContext=view.getContext();
        initData();
        initView(view);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //添加分隔线
        mRecyclerView.addItemDecoration(new MyDecoration(mContext, MyDecoration.VERTICAL_LIST));

        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);

        //监听SwipeRefreshLayout.OnRefreshListener

        mRefreshLayout.setOnRefreshListener(this);

        /**
         * 监听addOnScrollListener这个方法，新建我们的EndLessOnScrollListener
         * 在onLoadMore方法中去完成上拉加载的操作
         * */
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {

                loadMoreData();
            }
        });
        return view;
    }
    //初始化界面
    private void initView(View view){
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        //这个是下拉刷新出现的那个圈圈要显示的颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorRed,
                R.color.colorYellow,
                R.color.colorGreen
        );
    }

    //初始化一开始加载的数据
    private void initData(){
        mData = new ArrayList<NewsBeans.ListBean>();
        Log.d("2222222222222222","2222222222222222222");
        getGsonData=new GetGsonData(mContext,this);
        getGsonData.ParseData("http://10.20.34.59:8080/news/servlet/servlet");
        Log.d("33333333333333","3333333333333333");
    }

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData(){
//        for (int i =0; i < 10; i++){
//            mData.add("嘿，我是“上拉加载”生出来的"+i);
//        }
    }

    /**
     * 重写SwipeRefreshLayout.OnRefreshListener的OnRefresh方法
     * 在这里面去做下拉刷新的操作
     */
    @Override
    public void onRefresh() {
        updateData();
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }

    private void updateData(){
        //我在List最前面加入一条数据
//        mData.add(0, "嘿，我是“下拉刷新”生出来的");
    }

    @Override
    public void getNewsDataList(List<NewsBeans.ListBean> dataList) {
//        mAdapter = new MyAdapter(this,dataList);
        mData.addAll(dataList);
        mAdapter=new NewsListAdapter(getActivity(),mData);
        mAdapter.setOnItemClickListener(this);
        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("+----+++++--",position+"---------------");
        Intent intent=new Intent(getActivity(),NewsContentActivity.class);
        intent.putExtra("newsName",mData.get(position).getTitle());
        intent.putExtra("newsUrl",mData.get(position).getUrl());
        startActivityForResult(intent,2);
    }
}
