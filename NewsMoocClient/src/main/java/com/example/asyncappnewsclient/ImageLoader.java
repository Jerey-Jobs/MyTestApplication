package com.example.asyncappnewsclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Xiamin on 2016/7/10.
 */
public class ImageLoader {
    private ImageView imageView;
    private String url;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /**
             * 用tag判断，避免listview自带的缓存机制导致加载前面的图片
             */
            if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    /**
     * 多线程的方式加载图片
     * @param imageView
     * @param url
     */
    public void showImageByThread(ImageView imageView, final String url) {
        this.imageView = imageView;
        this.url = url;
        new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmapFromUrl(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
                super.run();

            }
        }.start();

    }

    /**
     * 将url转换成图片
     * @param str
     * @return
     */
    public Bitmap getBitmapFromUrl(String str) {
        Bitmap bitmap;
        InputStream inputStream;
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);
            connection.disconnect();
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 异步的方式加载图片
     * @param imageView
     * @param url
     */
    public void showImageByAsyncTask(ImageView imageView, final String url) {
        new newsAsyncTask(imageView, url).execute(url);
    }

    private class newsAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView timageview;
        private String turl;

        public newsAsyncTask(ImageView imageView, String url) {
            this.timageview = imageView;
            turl = url;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return getBitmapFromUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (timageview.getTag().equals(turl))
                timageview.setImageBitmap(bitmap);
        }
    }
}
