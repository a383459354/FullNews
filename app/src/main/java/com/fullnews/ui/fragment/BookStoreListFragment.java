package com.fullnews.ui.fragment;

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

import com.fullnews.adapter.BookStoreAdapter;
import com.fullnews.entity.BookHomeBeans;
import com.fullnews.ui.activity.BookDetailsActivity;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookHomeList;
import com.fullnews.utils.ChanneUrlUtils;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class BookStoreListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BookHomeList, BookStoreAdapter.OnItemClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private BookStoreAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private BookGsonData bookGsonData;

    private List<BookHomeBeans.DataBean.PhBean.BookListBean> mData;

    private static final String EXTRA_CONTENT = "content";

    private int page=1;

    public static BookStoreListFragment newInstance(String content) {
        BookStoreListFragment bookFragment = new BookStoreListFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        bookFragment.setArguments(arguments);
        return bookFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerviews, null);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

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

    //初始化一开始加载的数据
    public void initData() {
        mData = new ArrayList<BookHomeBeans.DataBean.PhBean.BookListBean>();
        String url="http://ajax.shuqiapi.com/?bamp=sqphcm&desc_type="+ ChanneUrlUtils.bookChanne(getArguments().getString(EXTRA_CONTENT))+"&page=1&tk=NDE3YWM1OWU5Zg%253D%253D";
        Log.d("url-----", "3333" + url);
        bookGsonData=new BookGsonData(getActivity());
        bookGsonData.ParseBookData(url, this, 1);
    }

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData() {
        page+=1;
        String url="http://ajax.shuqiapi.com/?bamp=sqphcm&desc_type="+ ChanneUrlUtils.bookChanne(getArguments().getString(EXTRA_CONTENT))+"&page="+page+"&tk=NDE3YWM1OWU5Zg%253D%253D";
        bookGsonData.ParseBookData(url,this,2);
        Log.d("url---",url);
    }

    /**
     * 重写SwipeRefreshLayout.OnRefreshListener的OnRefresh方法
     * 在这里面去做下拉刷新的操作
     */
    @Override
    public void onRefresh() {
        bookGsonData.ParseBookData("http://ajax.shuqiapi.com/?bamp=sqphcm&desc_type="+ ChanneUrlUtils.bookChanne(getArguments().getString(EXTRA_CONTENT))+"&page=1&tk=NDE3YWM1OWU5Zg%253D%253D", this, 1);
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getBookDataList(List<BookHomeBeans.DataBean.PhBean.BookListBean> dataList) {
        mData=dataList;
        mAdapter = new BookStoreAdapter(getActivity(), mData);
        mAdapter.setOnItemClickListener(this);
        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getBookMoreDataList(List<BookHomeBeans.DataBean.PhBean.BookListBean> dataList) {
        Log.d("index---","datalistsize-"+dataList.size());
        mData.addAll(dataList);
        Log.d("index---","moresize-"+mData.size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("+----+++++--", position + "---------------");
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("bookId", mData.get(position).getId());
        startActivityForResult(intent, 2);
    }
}
