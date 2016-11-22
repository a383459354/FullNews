package com.fullnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullnews.adapter.ViewPagerAdapter;
import com.fullnews.model.BookChannel;
import com.fullnews.presenter.BookChannelPresenter;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class BookStoreSexFragment extends Fragment implements BookChannelPresenter {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ViewPagerAdapter viewPagerAdapter;

    private static final String EXTRA_CONTENT = "content";

    public static BookStoreSexFragment newInstance(String content) {
        BookStoreSexFragment fragment = new BookStoreSexFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tablayout_viewpager, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        new BookChannel().channel(getArguments().getString(EXTRA_CONTENT),this);

    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.pager_t);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_t);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void getBookChannelData(List<Fragment> channelFragment, List<String> channelItem) {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), channelFragment, channelItem);
        mViewPager.setAdapter(viewPagerAdapter);
    }
}
