package com.example.yiyiapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;
import okhttp3.OkHttpClient;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class MyAsyncTaskGetResult extends AsyncTask<String, Void, String>{

    OkHttpClient okHttpClient;
    private String url;
    public  String getStringURL = "http://fanyi.youdao.com/openapi.do?keyfrom=kehuduan" +
            "&key=34281701&type=data&doctype=json&version=1.1&q=";

    public MyAsyncTaskGetResult(String url)
    {
        this.url = url;
    }


    public String  YouDaoGetResult(String input) throws IOException {
        Log.i("iii",getStringURL + input);
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().
//                url(getStringURL + input).build();
//
//        Response response = client.newCall(request).execute();
//        try
//        {
//            if(response.isSuccessful())
//        {
//            return response.body().string();
//        }
//        else {
//            return "error";
//        }

        /**
         * 异步使用httpclient get结果
         */
        HttpClient httpCLient = new DefaultHttpClient();

        // 创建get请求实例
        HttpGet httpget = new HttpGet(getStringURL + input);
      //  System.out.println("executing request "+httpget.getURI());
        // 客户端执行get请求 返回响应实体
        HttpResponse response = httpCLient.execute(httpget);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    @Override
    protected String doInBackground(String... strings) {
        String res = "";
        try {
            return YouDaoGetResult(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("iii",s);
    }
}
