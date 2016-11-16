package com.fullnews.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.fullnews.adapter.HomeNavigationItemAdapter;
import com.fullnews.entity.HomeNavigationItemEntity;
import com.fullnews.model.HomeNavigationData;
import com.fullnews.model.HomePersenter;
import com.fullnews.ui.view.ItemDragHelperCallback;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

public class RedactNavigaionItemActivity extends AppCompatActivity implements HomePersenter {

    private RecyclerView mRecyclerView;

    private HomeNavigationData homeNavigationData;

    private List<HomeNavigationItemEntity> mListItem;
    private List<HomeNavigationItemEntity> mListOrtherItem;
    private HomeNavigationItemEntity entity;

    private HomeNavigationItemAdapter mAdapter;

    private GridLayoutManager manager;
    private ItemDragHelperCallback callback;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact_navigaion_item);
        initView();
        initNavigationData();
    }

    private void initNavigationData() {
        homeNavigationData=new HomeNavigationData(this,this);
        homeNavigationData.getNavigetionData();
    }

    private void initView() {
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView_redact_item);
        manager=new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(manager);
        callback=new ItemDragHelperCallback();
        helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void NavigationData(SharedPreferences item,SharedPreferences ortherItem) {
        mListItem=new ArrayList<>();
        mListOrtherItem=new ArrayList<>();
        for (int i=0;i<item.getAll().size();i++){
            entity=new HomeNavigationItemEntity();
            entity.setName(item.getString("item"+i,""));
            mListItem.add(entity);
        }
        for (int i=0;i<ortherItem.getAll().size();i++){
            entity=new HomeNavigationItemEntity();
            entity.setName(ortherItem.getString("ortherItem"+i,""));
            mListOrtherItem.add(entity);
        }
        mAdapter=new HomeNavigationItemAdapter(this,helper,mListItem,mListOrtherItem);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType=mAdapter.getItemViewType(position);
                return viewType== HomeNavigationItemAdapter.TYPE_MY || viewType == HomeNavigationItemAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnMyChannelItemClickListener(new HomeNavigationItemAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(RedactNavigaionItemActivity.this,mListItem.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void NavigationNull() {

    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        setResult(1,intent);
        finish();
    }
}
