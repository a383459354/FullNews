package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fullnews.adapter.BookContentAdapter;
import com.fullnews.entity.BookContentBeans;
import com.fullnews.entity.BookChapterBeans;
import com.fullnews.listener.EndLessOnScrollListener;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookChapterList;
import com.fullnews.presenter.BookContentList;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, BookContentList, BookChapterList {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    private List<BookContentBeans.ChapterBean> mData;
    private BookGsonData bookGsonData;
    private BookContentAdapter mAdapter;

    private BookChapterBeans chapterData;

    private int initIndex=0;
    private int newIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviews);
        initView();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        String url="http://api.zhuishushenqi.com/mix-atoc/"+getIntent().getStringExtra("bookId")+"?view=chapters";
        bookGsonData = new BookGsonData(this);
        bookGsonData.ParseChapterData(url, this);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        //这个是下拉刷新出现的那个圈圈要显示的颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorRed,
                R.color.colorYellow,
                R.color.colorGreen
        );
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //监听SwipeRefreshLayout.OnRefreshListener
        mRefreshLayout.setOnRefreshListener(this);

        /**
         * 监听addOnScrollListener这个方法，新建我们的EndLessOnScrollListener
         * 在onLoadMore方法中去完成上拉加载的操作
         * */
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(new LinearLayoutManager(this)) {
            @Override
            public void onLoadMore(int currentPage) {

                loadMoreData();
            }
        });
    }

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData() {
        newIndex+=1;
        String url="http://chapter2.zhuishushenqi.com/chapter/"+chapterData.getMixToc().getChapters().get(newIndex).getLink();
        bookGsonData.ParseBookContentData(url,this,2);
        Log.d("url---",url);
    }

    @Override
    public void onRefresh() {
        bookGsonData.ParseBookContentData("", this, 0);
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }


    @Override
    public void getBookChapterData(BookChapterBeans Beans) {
        this.chapterData=Beans;
        bookGsonData.ParseBookContentData("http://chapter2.zhuishushenqi.com/chapter/"+chapterData.getMixToc().getChapters().get(initIndex).getLink(),this,1);
    }

    @Override
    public void getTopBookContent(BookContentBeans beans) {
        List<BookContentBeans.ChapterBean> list=new ArrayList<>();
        list.add(beans.getChapter());
        list.addAll(mData);
        mData.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBookContent(BookContentBeans beans) {
        mData.add(beans.getChapter());
        mAdapter=new BookContentAdapter(this,mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getNextBookContent(BookContentBeans beans) {
        mData.add(beans.getChapter());
        mAdapter.notifyDataSetChanged();
    }
}
