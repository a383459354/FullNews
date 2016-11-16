package com.fullnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    List<Fragment> tabFragments;
    List<String> tabIndicators;

    public MainPageAdapter(FragmentManager fm ,List<Fragment> tabFragments,List<String> tabIndicators) {
        super(fm);
        this.tabFragments=tabFragments;
        this.tabIndicators=tabIndicators;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabIndicators.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabIndicators.get(position);
    }
}