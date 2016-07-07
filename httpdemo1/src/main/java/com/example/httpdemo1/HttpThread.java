package com.example.httpdemo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Xiamin on 2016/7/5.
 */
public class HttpThread extends Thread {

    private String url;
    private WebView webView;
    private Handler handler;
    private ImageView imageView;

    public HttpThread(String url, WebView webView, Handler handler)
    {
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    public HttpThread(String url, ImageView imageView, Handler handler)
    {
        this.url = url;
        this.imageView = imageView;
        this.handler = handler;
    }


    @Override
    public void run() {
        try {
            URL httpURL = new URL(url);
            HttpURLConnection urlConnection =  (HttpURLConnection)httpURL.openConnection();

            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = null;


            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                File parent = Environment.getExternalStorageDirectory();
                File downloadfile = new File(parent,"xiamindowload");

                fileOutputStream = new FileOutputStream(downloadfile);
            }

            byte[]  b = new byte[2*1024];

            int len;
            if(fileOutputStream != null)
            {
                while ((len = fileOutputStream.read(b)) != -1)
                {
                    fileOutputStream.write(b,0,len);
                }
            }




//            final StringBuffer stringBuffer = new StringBuffer();
//            //InputStreamReader把字符流转成字节流
//            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String str;
//            while((str = reader.readLine()) != null)
//            {
//                stringBuffer.append(str);
//            }
//
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    webView.loadData(stringBuffer.toString(),"text/html;charset=utf-8",null);
//                }
//            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
