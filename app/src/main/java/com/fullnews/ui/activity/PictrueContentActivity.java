package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zh.fullnews.R;

public class PictrueContentActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictrue_content);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView_content);
        mWebView.setWebViewClient(reloadUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(getIntent().getStringExtra("pictrueUrl"));
        Toast.makeText(this, getIntent().getStringExtra("pictrueName"), Toast.LENGTH_SHORT).show();
    }

    private WebViewClient reloadUrl = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

}
