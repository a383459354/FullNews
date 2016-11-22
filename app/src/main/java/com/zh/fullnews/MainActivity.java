package com.zh.fullnews;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fullnews.adapter.MainPageAdapter;
import com.fullnews.ui.fragment.BookFragment;
import com.fullnews.ui.fragment.FunnyFragment;
import com.fullnews.ui.fragment.HomeFragment;
import com.fullnews.ui.fragment.VideoFragment;

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
//        setTranslucentSystemUI();
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
        tabImage.add(R.drawable.selector_menu_book);
        tabImage.add(R.drawable.selector_menu_funny);
    }

    private void initTab() {
        mTablayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTablayout, 10);
        mTablayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTablayout.getTabAt(i);
            if (itemTab!=null){
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
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
        tabFragments.add(HomeFragment.newInstance(tabIndicators.get(0)));
        tabFragments.add(VideoFragment.newInstance(tabIndicators.get(1)));
        tabFragments.add(BookFragment.newInstance(tabIndicators.get(2)));
        tabFragments.add(FunnyFragment.newInstance(tabIndicators.get(3)));

        mAdapter = new MainPageAdapter(getSupportFragmentManager(),tabFragments,tabIndicators);
        mViewPager.setAdapter(mAdapter);
    }

    private void setTranslucentSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
