package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fullnews.adapter.ViewPagerAdapter;
import com.fullnews.model.BookChannel;
import com.fullnews.presenter.BookChannelPresenter;
import com.zh.fullnews.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class BookClassifyContentActivity extends AppCompatActivity implements BookChannelPresenter {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ViewPagerAdapter viewPagerAdapter;

    private String headerName = null;
    private TextView tvHeaderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_classify_content);
        initView();
        initData();
    }

    private void initData() {
        try {
            headerName = URLEncoder.encode(getIntent().getStringExtra("bookClassifyName"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new BookChannel().BookClassifyChannel(getIntent().getStringExtra("bookClassify"),headerName, this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.pager_t);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_t);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setupWithViewPager(mViewPager);
        tvHeaderName = (TextView) findViewById(R.id.textView_classify_name);
        tvHeaderName.setText(getIntent().getStringExtra("bookClassifyName"));
    }


    @Override
    public void getBookChannelData(List<Fragment> channelFragment, List<String> channelItem) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), channelFragment, channelItem);
        mViewPager.setAdapter(viewPagerAdapter);
    }
}
