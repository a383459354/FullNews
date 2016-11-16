package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fullnews.entity.BookDetailsBeans;
import com.fullnews.model.BookGsonData;
import com.fullnews.presenter.BookDetailsList;
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
    }

    private void initData() {
        BookGsonData bgData=new BookGsonData(this);
        Log.d("url---","http://ajax.shuqiapi.com/?bamp=sqbk&tk=NTU0NjE0NzQxN2FjNTllOWY%3D&bid="+getIntent().getStringExtra("bookId"));
        bgData.ParseBookDetailsData("http://ajax.shuqiapi.com/?bamp=sqbk&tk=NTU0NjE0NzQxN2FjNTllOWY%3D&bid="+getIntent().getStringExtra("bookId"),this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_bookrack:

                break;
            case R.id.button_start_read:

                break;
            case R.id.button_read_chapter:

                break;

            case R.id.imageView_details_back:

                break;
        }
    }

    @Override
    public void getBookDetailsData(BookDetailsBeans.DataBean data) {
//        tvBookName.setText(data.getBk().getBookname());
//        tvBookAuther.setText(data.getBk().getAuthor_name());
//        tvBookType.setText(data.getBk().getClass_name());
//        tvBookSize.setText(WordNumber.words(data.getBk().getSize()));
//        tvBookInfo.setText(data.getBk().getBook_info());
//        tvNewChapter.setText("[最新]"+data.getBk().getTopic());
//        tvNewChapterTime.setText(ConversionTime.pubDate(data.getBk().getDate_updated()));
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
