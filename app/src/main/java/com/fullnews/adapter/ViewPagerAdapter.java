package com.fullnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> tabFragment;
    List<String> item;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> tabFragment, List<String> item){
        super(fragmentManager);
        this.tabFragment=tabFragment;
        this.item=item;
    }

    @Override
    public Fragment getItem(int position) {

        Log.d("--name","++posi"+position);
        return tabFragment.get(position);
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return item.get(position);
    }
}
