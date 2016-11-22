package com.fullnews.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.fullnews.adapter.ViewPagerAdapter;
import com.fullnews.ui.activity.RedactNavigaionItemActivity;
import com.fullnews.model.HomeNavigationData;
import com.fullnews.model.HomePersenter;
import com.fullnews.utils.GetScreen;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class HomeFragment extends Fragment implements HomePersenter, View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageButton mBtnAdd;

    private List<String> item;

    private List<Fragment> tabFragments;

    private HomeNavigationData homeNavigationData;

    private ViewPagerAdapter viewPagerAdapter;

    public static HomeFragment newInstance(String content) {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_home, null);

        initView(contentView);
        initNavigationData();
        return contentView;
    }

    private void initNavigationData() {
        item=new ArrayList<>();
        homeNavigationData=new HomeNavigationData(getActivity(),this);
        homeNavigationData.getNavigetionData();
    }

    private void initView(View view) {
        mBtnAdd=(ImageButton) view.findViewById(R.id.imageButton_add);
        mBtnAdd.setOnClickListener(this);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTabLayout.getLayoutParams();
        params.weight = GetScreen.getScreenPix(getActivity()).widthPixels - 45;
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void uploadFragment(List<String> list) {
        tabFragments=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            tabFragments.add(NewsListFragment.newInstance(list.get(i)));
        }
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), tabFragments, list);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void NavigationData(SharedPreferences preferences, SharedPreferences ortherItem) {
        int index=preferences.getAll().size();
        Log.d("---------","-----"+index);
        item.clear();
        for (int i=0;i<index;i++){
            item.add(preferences.getString("item"+i,""));
        }
        uploadFragment(item);
    }

    @Override
    public void NavigationNull() {
        homeNavigationData.setNavigetionData();
        homeNavigationData.getNavigetionData();
    }

//    @Override
//    public void ChannelData(int ChannelId) {
//
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_add: {
                startActivityForResult(new Intent(getActivity(), RedactNavigaionItemActivity.class), 1);
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:{
                if (resultCode==1){
                    homeNavigationData.getNavigetionData();
                }
                break;
            }
        }
    }
}
