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

import com.fullnews.adapter.BookClassifyContentAdapter;
import com.fullnews.entity.BookClassifyContentBeans;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookClassifyContentList;
import com.fullnews.ui.activity.BookDetailsActivity;
import com.fullnews.utils.ChanneUrlUtils;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class BookClassifyContentFragment extends Fragment implements BookClassifyContentList, SwipeRefreshLayout.OnRefreshListener, BookClassifyContentAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    private BookGsonData bookGsonData;
    private BookClassifyContentAdapter mAdapter;

    private List<BookClassifyContentBeans.BooksBean> mData;

    private LinearLayoutManager mLinearLayoutManager;

    private static String EXTRA_CONTENT = "content";
    private static String EXTRA_NAME = "name";
    private static String EXTRA_CLASSIFY = "classify";

    public static BookClassifyContentFragment newInstance(String content, String name, String classify) {
        BookClassifyContentFragment fragment = new BookClassifyContentFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        arguments.putString(EXTRA_NAME, name);
        arguments.putString(EXTRA_CLASSIFY, classify);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerviews, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mData = new ArrayList<>();
        String url = "http://api.zhuishushenqi.com/book/by-categories?gender="
                + getArguments().getString(EXTRA_CLASSIFY)
                + "&minor=&start=0&limit=20&type="
                + ChanneUrlUtils.bookClassifyContentChanne(getArguments().getString(EXTRA_CONTENT))
                + "&major=" + getArguments().getString(EXTRA_NAME);
        Log.d("bookUrl---", url);
        bookGsonData = new BookGsonData(getActivity());
        bookGsonData.ParseBookClassifyContentData(url, this, 1);
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

    @Override
    public void onRefresh() {
        String url = "http://api.zhuishushenqi.com/book/by-categories?gender="
                + getArguments().getString(EXTRA_CLASSIFY)
                + "&minor=&start=0&limit=20&type="
                + ChanneUrlUtils.bookClassifyContentChanne(getArguments().getString(EXTRA_CONTENT))
                + "&major=" + getArguments().getString(EXTRA_NAME);
        bookGsonData.ParseBookClassifyContentData(url, this, 1);

        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }

    private void loadMoreData() {
        String url = "http://api.zhuishushenqi.com/book/by-categories?gender="
                + getArguments().getString(EXTRA_CLASSIFY)
                + "&minor=&start="
                + mData.size()
                + "&limit=20&type="
                + ChanneUrlUtils.bookClassifyContentChanne(getArguments().getString(EXTRA_CONTENT))
                + "&major=" + getArguments().getString(EXTRA_NAME);
        bookGsonData.ParseBookClassifyContentData(url, this, 2);
    }

    @Override
    public void getBookClassifyContentData(BookClassifyContentBeans beans) {
        mData = beans.getBooks();
        mAdapter = new BookClassifyContentAdapter(getActivity(), mData);
        mAdapter.setOnItemClickListener(this);
        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getMoreBookClassifyContentData(BookClassifyContentBeans beans) {
        mData.addAll(beans.getBooks());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("bookId",mData.get(position).get_id());
        startActivityForResult(intent, 9);
    }
}
