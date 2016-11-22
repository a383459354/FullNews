package com.fullnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.fullnews.adapter.ViewPagerAdapter;
import com.fullnews.ui.fragment.BookStoreSexFragment;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

public class BookStoreActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView ivClassify;
    private ImageView ivStore_seek;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> item;

    private List<Fragment> tabFragments;

    private List<Integer> tabImage;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_store);
        initView();
        initData();
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < item.size(); i++) {
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab!=null){
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
                ImageView itemIv=(ImageView)itemTab.getCustomView().findViewById(R.id.iv_menu_item);
                itemIv.setImageResource(tabImage.get(i));
            }
        }
    }

    private void initData() {
        tabImage=new ArrayList<>();
        tabImage.add(R.drawable.select_tab_book_men);
        tabImage.add(R.drawable.select_tab_book_wumen);
        item = new ArrayList<>();
        item.add("男生");
        item.add("女生");
        tabFragments = new ArrayList<>();
        for (int i=0;i<item.size();i++){
            tabFragments.add(BookStoreSexFragment.newInstance(item.get(i)));
        }
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabFragments, item);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    private void initView() {
        ivClassify = (ImageView) findViewById(R.id.imageView_book_store_classify);
        ivStore_seek = (ImageView) findViewById(R.id.imageView_book_store_seek);
        ivClassify.setOnClickListener(this);
        ivStore_seek.setOnClickListener(this);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_book_store);
        mViewPager = (ViewPager) findViewById(R.id.pager_book_store);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_book_store_classify:
                startActivityForResult(new Intent(BookStoreActivity.this,BookClassifyActivity.class),6);
                break;

            case R.id.imageView_book_store_seek:
                startActivityForResult(new Intent(BookStoreActivity.this,BookSeekActivity.class),7);
                break;
        }
    }
}
