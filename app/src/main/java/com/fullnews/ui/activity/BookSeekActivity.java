package com.fullnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fullnews.adapter.BookSeekContentAdapter;
import com.fullnews.adapter.BookSeekAutoAdapter;
import com.fullnews.adapter.BookSeekHistoryAdapter;
import com.fullnews.entity.BookClassifyContentBeans;
import com.fullnews.entity.BookHotSeekBeans;
import com.fullnews.listener.AutoTextChangeListener;
import com.fullnews.model.BookGsonData;
import com.fullnews.model.SearchHistoryModel;
import com.fullnews.presenter.BookClassifyContentList;
import com.fullnews.presenter.BookHistoryPresenter;
import com.fullnews.presenter.BookHotSeekList;
import com.fullnews.utils.GetScreen;
import com.zh.fullnews.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookSeekActivity extends AppCompatActivity implements View.OnClickListener, BookHotSeekList, BookSeekAutoAdapter.OnItemClickListener, BookClassifyContentList, BookSeekContentAdapter.OnItemClickListener, View.OnFocusChangeListener, BookHistoryPresenter, BookSeekHistoryAdapter.OnItemClickListener {

    private LinearLayout mLayout;
    private LinearLayout mOneLayout;
    private LinearLayout mHistoryLayout;
    private RecyclerView mHistoryRecyclerView;
    private RecyclerView mAutoRecyclerView;
    private RecyclerView mContentRecyclerView;
    private ImageView ivBack;
    private EditText etInput;
    private ImageView ivSeek;
    private TextView tvRefresh;
    private TextView tvDelete;
    private TextView tvHotTag1;
    private TextView tvHotTag2;
    private TextView tvHotTag3;
    private TextView tvHotTag4;

    private List<String> autoData;
    private List<String> hotData;
    private List<String> mHistoryData;

    private BookSeekAutoAdapter mAutoAdapter;
    private BookSeekHistoryAdapter mHistoryAdapter;

    private BookGsonData bookGsonData;
    private BookSeekContentAdapter mSeekContentAdapter;
    private List<BookClassifyContentBeans.BooksBean> contentData;

    private SearchHistoryModel searchHistory;

    private int fragmentTag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_seek);
        initView();
        initRecyclerView();
        initThirdFragmentView();
        initData();
    }

    private void initData() {
        searchHistory = new SearchHistoryModel(this);
        searchHistory.getSearchHistoryData(this);
        bookGsonData = new BookGsonData(this);
        bookGsonData.ParsehotSeekData("http://api.zhuishushenqi.com/book/hot-word", this);

    }

    private void initView() {
        mLayout = (LinearLayout) findViewById(R.id.activity_book_seek);
        mLayout.setFocusableInTouchMode(true);
        etInput = (EditText) findViewById(R.id.edittext_input);
        ivBack = (ImageView) findViewById(R.id.imageView_seek_back);
        ivSeek = (ImageView) findViewById(R.id.imageView_seek_seek);
        ivBack.setOnClickListener(this);
        ivSeek.setOnClickListener(this);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) etInput.getLayoutParams();
        params.weight = GetScreen.getScreenPix(this).widthPixels - ivSeek.getWidth() - ivBack.getWidth() - 20;
        etInput.setOnFocusChangeListener(this);
        etInput.addTextChangedListener(new AutoTextChangeListener(this) {
            @Override
            public void autoPrompting(List<String> data) {
                fragmentTag=2;
                mOneLayout.setVisibility(View.GONE);
                setAutoData(data);
            }
        });
    }

    private void initRecyclerView() {
        mHistoryRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_seek_history);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAutoRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_seek_auto);
        mAutoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContentRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_seek_content);
        mContentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initThirdFragmentView() {
        mOneLayout = (LinearLayout) findViewById(R.id.linearlayout_one);
        mHistoryLayout = (LinearLayout) findViewById(R.id.linearlayout_history);
        tvHotTag1 = (TextView) findViewById(R.id.textview_hot_tag1);
        tvHotTag2 = (TextView) findViewById(R.id.textview_hot_tag2);
        tvHotTag3 = (TextView) findViewById(R.id.textview_hot_tag3);
        tvHotTag4 = (TextView) findViewById(R.id.textview_hot_tag4);
        tvHotTag1.setOnClickListener(this);
        tvHotTag2.setOnClickListener(this);
        tvHotTag3.setOnClickListener(this);
        tvHotTag4.setOnClickListener(this);
        tvRefresh = (TextView) findViewById(R.id.textview_refresh);
        tvDelete = (TextView) findViewById(R.id.textview_delete);
        tvRefresh.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
    }

    private void setAutoData(List<String> data) {
        this.autoData = data;
        mAutoAdapter = new BookSeekAutoAdapter(this, data);
        mAutoRecyclerView.setAdapter(mAutoAdapter);
        mAutoRecyclerView.setVisibility(View.VISIBLE);
        mAutoAdapter.setOnItemClickListener(this);
        Log.d("autoText-----", "填入的值----" + data.toString());
    }

    private void getSeekContentData(String seekName) {
        fragmentTag=3;
        mLayout.setFocusableInTouchMode(true);
        mLayout.requestFocusFromTouch();
        mOneLayout.setVisibility(View.GONE);
        mAutoRecyclerView.setVisibility(View.GONE);
        mContentRecyclerView.setVisibility(View.VISIBLE);
        searchHistory.setSearchHistoryData(seekName);
        try {
            seekName = URLEncoder.encode(seekName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bookGsonData.ParseBookClassifyContentData("http://api.zhuishushenqi.com/book/fuzzy-search?query=" + seekName, this, 1);
    }

    private List<String> randomList(List<String> list) {
        List<String> data=new ArrayList<>();
        if (list.size() > 6) {
            for (int i = 0; i < 6; i++) {
                data.add(list.get(new Random().nextInt(list.size())));
                Log.d("BookSeekActivity", data.size()+data.toString());
            }
        }
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_seek_back:
                backListener();
                break;

            case R.id.imageView_seek_seek:
                if (etInput.getText().toString()!=null||etInput.getText().toString()!=""||etInput.getText().toString().length()>0){
                    getSeekContentData(etInput.getText().toString());
                }
                break;

            case R.id.textview_refresh:
                setHotTagData(randomList(hotData));
                break;

            case R.id.textview_delete:
                searchHistory.deleteSearchHistoryData();
                searchHistory.getSearchHistoryData(this);
                break;

            case R.id.textview_hot_tag1:
                etInput.setText(tvHotTag1.getText());
                getSeekContentData(tvHotTag1.getText().toString());
                break;

            case R.id.textview_hot_tag2:
                etInput.setText(tvHotTag2.getText());
                getSeekContentData(tvHotTag2.getText().toString());
                break;

            case R.id.textview_hot_tag3:
                etInput.setText(tvHotTag3.getText());
                getSeekContentData(tvHotTag3.getText().toString());
                break;

            case R.id.textview_hot_tag4:
                etInput.setText(tvHotTag4.getText());
                getSeekContentData(tvHotTag4.getText().toString());
                break;
        }
    }

    private void backListener() {
        if (fragmentTag==1){
            finish();
        }else if (fragmentTag==2){
            fragmentTag=1;
            mOneLayout.setVisibility(View.VISIBLE);
            mAutoRecyclerView.setVisibility(View.GONE);
            mContentRecyclerView.setVisibility(View.GONE);
        }else if (fragmentTag==3){
            fragmentTag=2;
            mOneLayout.setVisibility(View.GONE);
            mAutoRecyclerView.setVisibility(View.VISIBLE);
            mContentRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void getBookHotSeekData(BookHotSeekBeans beans) {
        this.hotData=beans.getHotWords();
        setHotTagData(randomList(hotData));
    }

    private void setHotTagData(final List<String> data) {
        tvHotTag1.setText(data.get(0));
        tvHotTag2.setText(data.get(1));
        tvHotTag3.setText(data.get(2));
        tvHotTag4.setText(data.get(3));
    }

    @Override
    public void getHistoryData(List<String> data) {
        this.mHistoryData=data;
        Log.d("BookSeekActivity", data.toString()+"----"+data.size());
        if (data.toString()!= "[]") {
            mHistoryLayout.setVisibility(View.VISIBLE);
        }else{
            mHistoryLayout.setVisibility(View.GONE);
        }
        mHistoryAdapter = new BookSeekHistoryAdapter(this, data);
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
        mHistoryAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemAutoClick(View view, int position) {
        etInput.setText(autoData.get(position));
        getSeekContentData(autoData.get(position));
        Toast.makeText(this, etInput.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getBookClassifyContentData(BookClassifyContentBeans beans) {
        this.contentData = beans.getBooks();
        mSeekContentAdapter = new BookSeekContentAdapter(this, beans.getBooks());
        mContentRecyclerView.setAdapter(mSeekContentAdapter);
        mSeekContentAdapter.setOnItemClickListener(this);
    }

    @Override
    public void getMoreBookClassifyContentData(BookClassifyContentBeans beans) {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookId", contentData.get(position).get_id());
        startActivityForResult(intent, 10);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Log.d("visible", "visible");
            mAutoRecyclerView.setVisibility(View.VISIBLE);
            mContentRecyclerView.setVisibility(View.GONE);
            mOneLayout.setVisibility(View.GONE);
        } else {
            Log.d("visible", "gone");
        }
    }

    @Override
    public void onItemHistoryClick(View view, int position) {
        etInput.setText(mHistoryData.get(position));
        getSeekContentData(mHistoryData.get(position));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
            {
                backListener();
            }
            return false;
    }
}
