package com.fullnews.ui.fragment;

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

import com.fullnews.adapter.HumorListAdapter;
import com.fullnews.entity.HumorBeans;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.GetGsonData;
import com.fullnews.presenter.HumorList;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class HumorFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,HumorList {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private HumorListAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private GetGsonData getGsonData;

    private List<HumorBeans.DataBean.ArticleBean> mData;

    private String index="20";
    private String url="http://zzd.sm.cn/iflow/api/v1/article/category/youmoduanzi?uc_param_str=dnnivebichfrmintcpgieiwidsudpf&zzd_from=webapp&app=webapp&client_os=webapp&count=10&method=hotter&index=";


    public static HumorFragment newInstance(String content) {
        HumorFragment fragment = new HumorFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_pictrue, null);
        initView(contentView);
        initData();
        return contentView;
    }

    private void initData() {
        mData = new ArrayList<HumorBeans.DataBean.ArticleBean>();
        Log.d("url-----", "3333" + url+index);
        getGsonData = new GetGsonData(getActivity());
        getGsonData.ParseHumorData(url+index, this, 1);
    }

    //初始化界面
    private void initView(View view) {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_pictrue);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_pictrue);

        //这个是下拉刷新出现的那个圈圈要显示的颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorRed,
                R.color.colorYellow,
                R.color.colorGreen
        );
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
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

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData() {
        getGsonData.ParseHumorData(url+index,this,2);
        Log.d("url---",url);
    }

    @Override
    public void onRefresh() {
        getGsonData.ParseHumorData(url+index, this, 1);
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getHumorDataList(List<HumorBeans.DataBean.ArticleBean> dataList,String index) {
//        mAdapter = new MyAdapter(this,dataList);
        this.index=index;
        mData=dataList;
        mAdapter = new HumorListAdapter(getActivity(), mData);
        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getHumorMoreDataList(List<HumorBeans.DataBean.ArticleBean> dataList,String index) {
        this.index=index;
        Log.d("index---","datalistsize-"+dataList.size());
        mData.addAll(dataList);
        Log.d("index---","moresize-"+mData.size());
        mAdapter.notifyDataSetChanged();
    }
}
