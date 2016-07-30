package com.example.yiyiapp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class MyHttpManager {
    OkHttpClient okHttpClient;
    public final static String getStringURL = "http://fanyi.youdao.com/openapi.do?keyfrom=kehuduan" +
            "&key=34281701&type=data&doctype=json&version=1.1&q=";

    public String YouDaoGetResult(String input) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(getStringURL + input).build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful())
        {
            return response.body().string();
        }
        else {
            return "error";
        }
    }

}


