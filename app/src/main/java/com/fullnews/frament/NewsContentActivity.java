package com.fullnews.frament;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.zh.fullnews.R;

public class NewsContentActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        initView();
    }

    private void initView() {
        mWebView=(WebView)findViewById(R.id.webView_content);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(getIntent().getStringExtra("newsUrl"));
        Toast.makeText(this,getIntent().getStringExtra("newsName"),Toast.LENGTH_SHORT).show();
    }
}
