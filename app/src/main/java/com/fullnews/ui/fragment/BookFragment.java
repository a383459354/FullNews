package com.fullnews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fullnews.ui.activity.BookStoreActivity;
import com.zh.fullnews.R;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class BookFragment extends Fragment implements View.OnClickListener {

    ImageView ivBookStroe;
    private ImageView ivBookStore;

    public static BookFragment newInstance(String content) {
        BookFragment bookFragment = new BookFragment();
        return bookFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.book_fragment, null);
        initView(contentView);
        return contentView;
    }

    private void initView(View contentView) {
        ivBookStore = (ImageView) contentView.findViewById(R.id.imageView_book);
        ivBookStore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(getActivity(),BookStoreActivity.class),3);
    }
}
