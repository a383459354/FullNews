package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fullnews.utils.GetScreen;
import com.zh.fullnews.R;

public class BookSeekActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private EditText etSeekInput;
    private ImageView ivSeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_seek);
        initView();

    }

    private void initView() {
        etSeekInput = (EditText) findViewById(R.id.editText_seek);
        ivBack = (ImageView) findViewById(R.id.imageView_seek_back);
        ivSeek = (ImageView) findViewById(R.id.imageView_seek_seek);
        ivBack.setOnClickListener(this);
        ivSeek.setOnClickListener(this);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) etSeekInput.getLayoutParams();
        params.weight = GetScreen.getScreenPix(this).widthPixels - ivSeek.getWidth()-ivBack.getWidth()-13;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_seek_back:
                finish();
                break;

            case R.id.imageView_seek_seek:

                break;
        }
    }
}
