package com.fullnews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fullnews.adapter.BookRackAdapter;
import com.fullnews.db.DBManager;
import com.fullnews.entity.ReadHistoryEntity;
import com.fullnews.presenter.BookRackData;
import com.fullnews.ui.activity.BookStoreActivity;
import com.fullnews.ui.activity.ReadActivity;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class BookFragment extends Fragment implements View.OnClickListener, BookRackData, BookRackAdapter.OnItemClickListener {

    private ImageView ivBookStore;
    private RecyclerView mRecyclerView;
    private BookRackAdapter bookRackAdapter;
    private DBManager db;

    private List<ReadHistoryEntity> mData;

    public static BookFragment newInstance(String content) {
        BookFragment bookFragment = new BookFragment();
        return bookFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.book_fragment, null);
        initView(contentView);
        intData();
        return contentView;
    }

    private void intData() {
        db = new DBManager(getActivity());
        db.queryBookRack(this);
    }

    private void initView(View contentView) {
        ivBookStore = (ImageView) contentView.findViewById(R.id.imageView_book);
        ivBookStore.setOnClickListener(this);
        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView_book);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_book:
                startActivityForResult(new Intent(getActivity(), BookStoreActivity.class), 3);
                break;
        }
    }


    @Override
    public void getBookRackDB(List<ReadHistoryEntity> data) {
        this.mData=data;
        if (data.size() > 0) {
            bookRackAdapter = new BookRackAdapter(getActivity(), data);
            bookRackAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(bookRackAdapter);
        } else {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        db.queryBookRack(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(getActivity(),ReadActivity.class);
        intent.putExtra("bookId",mData.get(position).getBookId());
        startActivityForResult(intent,13);
    }
}
