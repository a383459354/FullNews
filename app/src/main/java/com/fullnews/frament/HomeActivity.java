package com.fullnews.frament;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.fullnews.adapter.HomeViewPagerAdapter;
import com.fullnews.model.HomeNavigationData;
import com.fullnews.presenter.HomePersenter;
import com.fullnews.utils.GetScreen;
import com.zh.fullnews.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomePersenter, View.OnClickListener {



    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageButton mBtnAdd;

    private List<String> item;

    private List<Fragment> tabFragments;

    private SharedPreferences mPreferences;

    private HomeNavigationData homeNavigationData;

    private HomeViewPagerAdapter homeViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initNavigationData();
    }

    private void initNavigationData() {
        item=new ArrayList<>();
        homeNavigationData=new HomeNavigationData(this,this);
        homeNavigationData.getNavigetionData();
    }

    private void initView() {
        mBtnAdd=(ImageButton) findViewById(R.id.imageButton_add);
        mBtnAdd.setOnClickListener(this);
        mViewPager=(ViewPager)findViewById(R.id.pager);
        mTabLayout=(TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) mTabLayout.getLayoutParams();
        params.weight= GetScreen.getScreenPix(this).widthPixels-50;
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void NavigationData(SharedPreferences preferences,SharedPreferences ortherItem) {
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

    public void uploadFragment(List<String> list){
        tabFragments=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            tabFragments.add(NewsListFragment.newInstance(list.get(i)));
        }
        homeViewPagerAdapter=new HomeViewPagerAdapter(getSupportFragmentManager(),tabFragments,list);
        mViewPager.setAdapter(homeViewPagerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_add: {
                startActivityForResult(new Intent(HomeActivity.this, RedactNavigaionItemActivity.class), 1);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:{
                   if (resultCode==RESULT_OK){
                       homeNavigationData.getNavigetionData();
                   }
                break;
            }
        }
    }
}
