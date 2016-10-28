package com.zh.fullnews;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fullnews.adapter.MainPageAdapter;
import com.fullnews.frament.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    
    private TabLayout mTablayout;
    private MainPageAdapter mAdapter;
    private ViewPager mViewPager;
    private RelativeLayout relativelayout;
    private List<String> tabIndicators;
    private List<Integer> tabImage;
    private List<Fragment> tabFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initTab();
    }

    private void initData() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("首页");
        tabIndicators.add("视频");
        tabIndicators.add("发现");
        tabIndicators.add("我");

        tabImage=new ArrayList<>();
        tabImage.add(R.drawable.selector_menu_home);
        tabImage.add(R.drawable.selector_menu_video);
        tabImage.add(R.drawable.selector_menu_discover);
        tabImage.add(R.drawable.selector_menu_personage);
    }

    private void initTab() {
        mTablayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTablayout, 10);
        mTablayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTablayout.getTabAt(i);
            if (itemTab!=null){
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                itemTv.setText(tabIndicators.get(i));
                ImageView itemIv=(ImageView)itemTab.getCustomView().findViewById(R.id.iv_menu_item);
                itemIv.setImageResource(tabImage.get(i));
            }
        }
        mTablayout.getTabAt(0).getCustomView().setSelected(true);
    }

    private void initView() {
        mTablayout=(TabLayout)this.findViewById(R.id.main_tab);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpage);
        tabFragments = new ArrayList<>();
        for (String s : tabIndicators) {
            tabFragments.add(HomeFragment.newInstance(s));
        }
        mAdapter = new MainPageAdapter(getSupportFragmentManager(),tabFragments,tabIndicators);
        mViewPager.setAdapter(mAdapter);
    }

}
