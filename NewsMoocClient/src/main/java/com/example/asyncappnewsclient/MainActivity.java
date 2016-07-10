package com.example.asyncappnewsclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String url = "http://www.imooc.com/api/teacher?type=4&num=90";


    private ListView listView_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_news = (ListView) findViewById(R.id.news_listview);
        new NewsAsyncTask().execute(url);
    }

    /**
     * url数据转化为我们所需要的对象列表 此操作在异步的AsyncTask中
     * @param url
     * @return
     */
    private List<NewsBean> getJsonData(String url) {
        List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
        try {
            /**
             * url.openConnection().getInputStream()  和 URL(url).openStream() 没什么区别 都直接根据
             * url就可以获得输入流
             */
            String jsonString = readStream(new URL(url).openStream());
            Log.i("iii",jsonString);

            /**
             * 解析json数据
             */

            JSONObject jsonObject;
            NewsBean newsBean;
            jsonObject = new JSONObject(jsonString);
          //  JSONArray jsonArray = new JSONArray(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Log.i("iii","jsonArray length = " + jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                newsBean = new NewsBean();
                newsBean.newsImageUrl = jsonObject.getString("picSmall");
                newsBean.newsTitle = jsonObject.getString("name");
                newsBean.newsContent = jsonObject.getString("description");
                newsBeanList.add(newsBean);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("iii","newsBeanList size = " + newsBeanList.size());
        return newsBeanList;
    }

    /**
     * 通过inputStream 解析网络数据
     * @param inputStream
     * @return
     */
    private String readStream(InputStream inputStream) {
        InputStreamReader isr;
        String result = "";  //若result初始化为空 会发生异常情况！！！数据错误
        String line = "";
        try {
            isr = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(isr);

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {

        @Override
        protected List<NewsBean> doInBackground(String... strings) {
            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeen) {
            super.onPostExecute(newsBeen);
            NewsAdapter adapter = new NewsAdapter(newsBeen,MainActivity.this,listView_news);
            listView_news.setAdapter(adapter);
        }
    }


}
