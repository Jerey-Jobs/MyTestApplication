package com.example.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText editText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IDInit();
        WebViewInit();
        setEditText();
    }

    private void IDInit() {
        webView = (WebView) findViewById(R.id.webview);
        editText = (EditText) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void setEditText()
    {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                webView.loadUrl(editText.getText().toString());
            }
        });
    }

    private void WebViewInit() {
        webView.loadUrl("http://www.baidu.com");
        //覆盖webview试图通过第三方浏览器或者系统浏览器打开页面的请求
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

        /*
        * 增加WebView进度条功能 当加载时 显示加载进度条  加载完成使进度条不可见
        */
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    progressBar.setProgress(newProgress);
                } else {
                    progressBar.setProgress(0);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        //启用javascript
        WebSettings settings = webView.getSettings();
        settings.getJavaScriptEnabled();

        //webview 加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }


    //改写物理按键返回逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(MainActivity.this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        } else {
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }



}
