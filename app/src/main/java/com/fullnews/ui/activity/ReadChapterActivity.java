package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fullnews.adapter.ReadChapterAdapter;
import com.fullnews.entity.BookChapterBeans;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookChapterList;
import com.zh.fullnews.R;

public class ReadChapterActivity extends AppCompatActivity implements BookChapterList, ReadChapterAdapter.OnItemClickListener {

    private RecyclerView mRecyclerview;
    private BookGsonData bookGsonData;
    private ReadChapterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        initView();
        initData();
    }

    private void initData() {
        bookGsonData = new BookGsonData(this);
        bookGsonData.ParseChapterData("http://api.zhuishushenqi.com/mix-atoc/" + getIntent().getStringExtra("bookId") + "?view=chapters", this);
        Log.d("ReadChapterActivity", "http://api.zhuishushenqi.com/mix-atoc/" + getIntent().getStringExtra("bookId") + "?view=chapters");
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview_chapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void getBookChapterData(BookChapterBeans Beans) {
        if (Beans.getMixToc().getChapters() != null) {
            mAdapter = new ReadChapterAdapter(this, Beans.getMixToc().getChapters());
            mRecyclerview.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
