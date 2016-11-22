package com.fullnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fullnews.adapter.BookClassifyAdapter;
import com.fullnews.entity.BookClassifyBeans;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookClassifyList;
import com.zh.fullnews.R;

public class BookClassifyActivity extends AppCompatActivity implements BookClassifyList, BookClassifyAdapter.OnItemClickListener {

    private RecyclerView mRecycler;
    private GridLayoutManager layoutManager;
    private BookClassifyAdapter mAdapter;
    private BookClassifyBeans beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        initView();
        initData();
    }

    private void initData() {
        BookGsonData bookGsonData=new BookGsonData(this);
        bookGsonData.ParseBookClassifyData("http://api.zhuishushenqi.com/cats/lv2/statistics",this);
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler_classify);
        layoutManager=new GridLayoutManager(this,4);
        mRecycler.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.d("BookClassifyActivity", position+"");
                Log.d("BookClassifyActivity", mAdapter.getItemViewType(position)+"");
                Log.d("BookClassifyActivity", "----------------");
                return mAdapter.getItemViewType(position)== 1 || mAdapter.getItemViewType(position) == 3||mAdapter.getItemViewType(position)==5 ? 1 : 4;
            }
        });
    }

    @Override
    public void getBookClassifyData(BookClassifyBeans beans) {
        this.beans=beans;
        mAdapter=new BookClassifyAdapter(this,beans.getMale(),beans.getFemale(),beans.getPress());
        mAdapter.setOnItemClickListener(this);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        String name = null;
        String classify=null;
        if (position<beans.getMale().size()+1){
            name=beans.getMale().get(position-1).getName();
            classify="male";
        }else if(position>beans.getMale().size()+1&&position<beans.getMale().size()+beans.getFemale().size()+2){
            name=beans.getFemale().get(position-beans.getMale().size()-2).getName();
            classify="female";
        }else if (position>beans.getMale().size()+2+beans.getFemale().size()){
            name=beans.getPress().get(position-3-beans.getMale().size()-beans.getFemale().size()).getName();
            classify="press";
        }
        Intent intent=new Intent(BookClassifyActivity.this,BookClassifyContentActivity.class);
        intent.putExtra("bookClassifyName",name);
        intent.putExtra("bookClassify",classify);
        startActivityForResult(intent,8);
    }
}
