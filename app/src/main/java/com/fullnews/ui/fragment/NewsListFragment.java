package com.fullnews.ui.fragment;

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

import com.fullnews.adapter.JiaodianAdapter;
import com.fullnews.entity.JiaodianBeans;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.GetGsonData;
import com.fullnews.presenter.NewsList;
import com.fullnews.ui.activity.NewsContentActivity;
import com.fullnews.utils.ChanneUrlUtils;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, NewsList, JiaodianAdapter.OnItemClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private JiaodianAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private GetGsonData getGsonData;

    private List<JiaodianBeans.DataBean> mData;

    private Context mContext;

    private static final String EXTRA_CONTENT = "content";

    private int pageIndex=1;

    public static NewsListFragment newInstance(String content) {
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        newsListFragment.setArguments(arguments);
        return newsListFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerviews, null);
        mContext = view.getContext();
        initView(view);
        initData();

        return view;
    }

    //初始化界面
    private void initView(View view) {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //这个是下拉刷新出现的那个圈圈要显示的颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorRed,
                R.color.colorYellow,
                R.color.colorGreen
        );
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

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
    }

    //初始化一开始加载的数据
    public void initData() {
        mData = new ArrayList<JiaodianBeans.DataBean>();
        getGsonData = new GetGsonData(getActivity());
        String url="http://api.myjiaodian.com/e/appapi/?api=info/random&classid="+ChanneUrlUtils.newsChannelId(getArguments().getString(EXTRA_CONTENT));
        Log.d("u----init--",url);
        getGsonData.ParseData(url, this, 1);
    }

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData() {
        pageIndex+=1;
        String url="http://api.myjiaodian.com/e/appapi/?api=info/list&classid="+ChanneUrlUtils.newsChannelId(getArguments().getString(EXTRA_CONTENT))+"&pageIndex="+pageIndex;
        getGsonData.ParseData(url,this,2);
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

    private void updateData() {
        String url="http://api.myjiaodian.com/e/appapi/?api=info/random&classid="+ChanneUrlUtils.newsChannelId(getArguments().getString(EXTRA_CONTENT));
        getGsonData.ParseData(url, this, 1);
    }

    @Override
    public void getNewsDataList(List<JiaodianBeans.DataBean> dataList) {
        mData=dataList;
        mAdapter = new JiaodianAdapter(getActivity(), mData);
        mAdapter.setOnItemClickListener(this);
        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getNewsMoreDataList(List<JiaodianBeans.DataBean> dataList) {
        mData.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), NewsContentActivity.class);
        intent.putExtra("newsName", mData.get(position).getTitle());
        intent.putExtra("newsUrl", mData.get(position).getTitleurl());
        startActivityForResult(intent, 2);
    }
}
