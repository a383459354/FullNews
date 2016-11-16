package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zh.fullnews.R;

public class PersonageActivity extends AppCompatActivity {

    private TextView textView4;
    private ImageView imageView_book;
    private RecyclerView recyclerView_book;
    private SwipeRefreshLayout swipeRefreshLayout_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_fragment);
        initView();
    }

    private void initView() {
        textView4 = (TextView) findViewById(R.id.textView4);
        imageView_book = (ImageView) findViewById(R.id.imageView_book);
        recyclerView_book = (RecyclerView) findViewById(R.id.recyclerView_book);
        swipeRefreshLayout_book = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_book);
    }
}
