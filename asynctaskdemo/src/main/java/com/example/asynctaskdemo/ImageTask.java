package com.example.asynctaskdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Xiamin on 2016/7/9.
 */
public class ImageTask extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar mprogressbar;
    private static String murl = "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";

    /**
     * 手动敲onCreate时，记得是protected这个 不是public那个
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        imageView = (ImageView) findViewById(R.id.imageView);
        mprogressbar = (ProgressBar) findViewById(R.id.myProgessbar);
        new MyImageAsyncTask().execute(murl);
    }


    class MyImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {

            mprogressbar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mprogressbar.setVisibility(View.INVISIBLE);
            imageView.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.i("iii", "doInBackground");
            String url = strings[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream inputStream;
            try {
                Log.i("iii", "image doInBackground ");
                connection = new URL(url).openConnection();
                inputStream = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                Log.i("iii", "image BufferedInputStream ");
                Thread.sleep(2000);    //为了显示加载效果
                /**
                 * 通过decodeSteam解析输入流　转换成bitmap
                 */
                bitmap = BitmapFactory.decodeStream(bis);
                inputStream.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }
}
