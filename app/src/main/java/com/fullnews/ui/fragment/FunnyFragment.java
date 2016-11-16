package com.fullnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.fullnews.adapter.ViewPagerAdapter;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class FunnyFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageButton mBtnAdd;

    private List<String> item;

    private List<Fragment> tabFragments;

    private ViewPagerAdapter viewPagerAdapter;

    public static FunnyFragment newInstance(String content) {
        FunnyFragment fragment = new FunnyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.funny_fragment, null);

        initView(contentView);
        initData();
        return contentView;
    }

    private void initData() {
        item = new ArrayList<>();
        item.add("幽默段子");
        item.add("搞笑图片");
        tabFragments = new ArrayList<>();
        tabFragments.add(HumorFragment.newInstance(item.get(0)));
        tabFragments.add(PictrueFragment.newInstance(item.get(1)));
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), tabFragments, item);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.pager_funny);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_funny);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
