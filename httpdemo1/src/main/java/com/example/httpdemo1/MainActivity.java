package com.example.httpdemo1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = (WebView) findViewById(R.id.myweb);
        imageView = (ImageView) findViewById(R.id.image);

        handler = new Handler();

        new HttpThread("http://www.baidu.com",webView,handler).start();
    }
}
