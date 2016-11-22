package com.fullnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fullnews.db.DBManager;
import com.fullnews.entity.BookDetailsBeans;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookDetailsList;
import com.fullnews.utils.ConversionTime;
import com.fullnews.utils.WordNumber;
import com.zh.fullnews.R;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener, BookDetailsList {

    private ImageView ivBack;
    private ImageView ivBookCover;
    private TextView tvBookName;
    private TextView tvBookAuther;
    private TextView tvBookType;
    private TextView tvBookSize;
    private TextView tvBookInfo;
    private TextView tvNewChapter;
    private TextView tvNewChapterTime;
    private Button btnAddBookrack;
    private Button btnStartRead;
    private Button btnReadChapter;
    private TextView[] tvTags;

    private BookDetailsBeans mData;

    private String bookId=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        initView();
        initData();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.imageView_details_back);
        ivBookCover = (ImageView) findViewById(R.id.imageview_book_cover);
        tvBookName = (TextView) findViewById(R.id.textView_book_name);
        tvBookAuther = (TextView) findViewById(R.id.textView_book_auther);
        tvBookType = (TextView) findViewById(R.id.textView_book_type);
        tvBookSize = (TextView) findViewById(R.id.textView_book_size);
        tvBookInfo = (TextView) findViewById(R.id.textView_book_info);
        tvNewChapter = (TextView) findViewById(R.id.textView_book_new_chapter);
        tvNewChapterTime = (TextView) findViewById(R.id.textView_book_new_chapter_time);
        btnAddBookrack = (Button) findViewById(R.id.button_add_bookrack);
        btnStartRead = (Button) findViewById(R.id.button_start_read);
        btnReadChapter = (Button) findViewById(R.id.button_read_chapter);

        btnAddBookrack.setOnClickListener(this);
        btnStartRead.setOnClickListener(this);
        btnReadChapter.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvTags = new TextView[5];
        tvTags[0] = (TextView) findViewById(R.id.textView_tags1);
        tvTags[1] = (TextView) findViewById(R.id.textView_tags2);
        tvTags[2] = (TextView) findViewById(R.id.textView_tags3);
        tvTags[3] = (TextView) findViewById(R.id.textView_tags4);
        tvTags[4] = (TextView) findViewById(R.id.textView_tags5);
    }

    private void initData() {
        bookId=getIntent().getStringExtra("bookId");
        BookGsonData bgData = new BookGsonData(this);
        Log.d("url+++", "http://api.zhuishushenqi.com/book/" + bookId);
        bgData.ParseBookDetailsData("http://api.zhuishushenqi.com/book/" + bookId, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_bookrack:
                DBManager db=new DBManager(this);
                db.add(mData.get_id(),mData.getTitle(),mData.getAuthor(),mData.getLastChapter(),
                        mData.getUpdated().replace("T", " ").substring(0, mData.getUpdated().replace("T", " ").length() - 5),
                        mData.getCover());
                break;
            case R.id.button_start_read:

                break;
            case R.id.button_read_chapter:
                Intent intent=new Intent(BookDetailsActivity.this,ReadChapterActivity.class);
                intent.putExtra("bookId",bookId);
                startActivityForResult(intent,12);
                break;

            case R.id.imageView_details_back:
                finish();
                break;
        }
    }

    @Override
    public void getBookDetailsData(BookDetailsBeans data) {
        this.mData=data;
        Log.d("url++", data.getTitle());
        tvBookName.setText(data.getTitle());
        tvBookAuther.setText(data.getAuthor());
        tvBookType.setText(data.getCat());
        tvBookSize.setText(WordNumber.words(data.getWordCount()));
        tvBookInfo.setText(data.getLongIntro());
        tvNewChapter.setText("[最新]" + data.getLastChapter());
        tvNewChapterTime.setText(ConversionTime.pubDate(data.getUpdated().replace("T", " ").substring(0, data.getUpdated().replace("T", " ").length() - 5)));
        ImgGlide("http://statics.zhuishushenqi.com"+data.getCover(), ivBookCover);
        if (data.getTags().size() >= 5) {
            for (int i = 0; i < 5; i++) {
                tvTags[i].setText(data.getTags().get(i));
                tvTags[i].setVisibility(View.VISIBLE);
            }
        } else {
            for (int i = 0; i < data.getTags().size(); i++) {
                tvTags[i].setText(data.getTags().get(i));
                tvTags[i].setVisibility(View.VISIBLE);
            }
        }
    }

    public void ImgGlide(String url, ImageView imageView) {
        Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_crop_original_white_48dp)
                .crossFade()
                .into(imageView);
    }
}
