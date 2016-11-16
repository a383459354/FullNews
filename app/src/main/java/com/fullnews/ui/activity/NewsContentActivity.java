package com.fullnews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zh.fullnews.R;

public class NewsContentActivity extends AppCompatActivity {

    private WebView mWebView;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView_content);
        mWebView.setWebViewClient(reloadUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(getIntent().getStringExtra("newsUrl"));
        Log.d("newsurl", getIntent().getStringExtra("newsUrl"));
        tvTitle = (TextView) findViewById(R.id.textView_news_title);
        tvTitle.setText(getIntent().getStringExtra("newsName"));
    }

    private WebViewClient reloadUrl = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (url != null) {

                String fun = "javascript:function getClass(parent,sClass) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";

                view.loadUrl(fun);

                String fun2 = "javascript:function hideOther() {getClass(document,'appdown appUrl')[0].style.display='none';getClass(document,'am-index-footer')[0].style.display='none';getClass(document,'aui-bar aui-bar-nav aui-bar-info bar-index bar-list')[0].style.display='none';}";

                view.loadUrl(fun2);

                String fun3 = "javascript:function getHeaderClass(parent,sClass) { var aEle=parent.getElementsByTagName('header'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";
                view.loadUrl(fun3);
                String fun4 = "javascript:function hideOtherHeader() {getHeaderClass(document,'aui-bar aui-bar-nav aui-bar-info bar-index bar-list')[0].style.display='none';}";
                view.loadUrl(fun4);

                view.loadUrl("javascript:hideOther();");
                view.loadUrl("javascript:hideOtherHeader();");
                super.onPageFinished(view, url);
            }
        }
    };
}
