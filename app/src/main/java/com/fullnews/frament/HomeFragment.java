package com.fullnews.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zh.fullnews.R;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class HomeFragment  extends Fragment {

    private static final String EXTRA_CONTENT = "content";

    public static HomeFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(arguments);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_video, null);
        ((TextView) contentView.findViewById(R.id.textView4)).setText(getArguments().getString(EXTRA_CONTENT));
        return contentView;
    }

}
