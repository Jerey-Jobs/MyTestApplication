package com.vincent.tabdemo.tools;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.vincent.tabdemo.Bean.XiaohuaBean;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Xiamin on 2016/7/17.
 */
public class ImageLoader {
    /**
     * 创建cache 最近最少使用算法
     */
    public LruCache<String, Bitmap> cache;
    private ImageView imageView;
    private String url;
    private ListView mListview;

    public ImageLoader(ListView Listview) {
        mListview = Listview;

        /**
         * 获取最大可用内存
         */
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cachesize = maxMemory / 4;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            cache = new LruCache<String, Bitmap>(cachesize) {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    //在每次存入缓存的时候调用
                    return value.getByteCount();
                }
            };
        }
    }

    /** 将bitmap增加到缓存
    *
            * @param url
    * @param bitmap
    */
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            cache.put(url, bitmap);
        }
    }

    /**
     * 从缓存中读取
     *
     * @param url
     * @return
     */
    public Bitmap getBitmapFromCache(String url) {
        return cache.get(url);
    }

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

    private class imageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView timageview;
        private String turl;

        public imageAsyncTask(ImageView imageView, String url) {
            this.timageview = imageView;
            turl = url;
        }

        public imageAsyncTask(String url) {
            turl = url;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            /**
             * 从网络下载图片，并将其放入缓存中
             */
            Bitmap bitmap = getBitmapFromUrl(strings[0]);
            if (bitmap != null) {
                addBitmapToCache(strings[0], bitmap);
            }
            else {
                Log.i("iii", "getBitmap URL error URL is:" + strings[0]);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (timageview.getTag().equals(turl) && bitmap != null) {
                timageview.setImageBitmap(bitmap);
            }else {
               // Log.i("iii","timageview.getTag().equals(turl) fail");
               // timageview.setImageResource(R.mipmap.widget_bar_news_nor);
            }

//            if(bitmap == null)
//            {
//
//            }

//            ImageView imageView = (ImageView) mListview.findViewWithTag(turl);
//            if (imageView != null && bitmap != null) {
//                imageView.setImageBitmap(bitmap);
//            }
        }
    }



    /**
     * 加载开始到结束的图片
     */
    public void loadImage(int start, int end,List<XiaohuaBean> list) {
        for (int i = start; i < end; i++) {
            String url = list.get(i).content;
            /**
             * 从网络下载图片，并将其放入缓存中
             */
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null) {
                imageAsyncTask task = new imageAsyncTask(url);
                task.execute(url);

            } else {
                ImageView image = (ImageView) mListview.findViewWithTag(url);
                image.setImageBitmap(bitmap);
            }
        }
    }


    /**
     * 异步的方式加载图片
     *
     * @param imageView
     * @param url
     */
    public void showImageByAsyncTask(ImageView imageView, final String url) {
        /**
         * 先判断缓存中是否有图片
         */
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
       //     imageView.setImageResource(R.mipmap.ic_launcher);
            new imageAsyncTask(imageView, url).execute(url);

        } else {
            imageView.setImageBitmap(bitmap);
        }

    }

}
