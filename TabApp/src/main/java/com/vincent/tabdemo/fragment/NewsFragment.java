package com.vincent.tabdemo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.show.api.ShowApiRequest;
import com.vincent.tabdemo.Adapter.XiaohuaListAdapter;
import com.vincent.tabdemo.Bean.XiaohuaBean;
import com.vincent.tabdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     Vincent
 * 创建时间   2016/7/8 23:49
 * 描述	      ${TODO}
 * <p>
 * 更新者     xiamin
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class NewsFragment extends Fragment implements AbsListView.OnScrollListener{
    private View rootView;

    private ListView listView;

    private List<XiaohuaBean> listBean;
    private XiaohuaListAdapter adapter;
    /**为加载笑话的页数 根据页数去服务器返回 为了下次进入不显示相同笑话 之后会将其存入sharepreference*/
    private Integer pageCount = 1;

    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * 当从tabA切换到tabB,然后再次切换到tabA时，发现对应的Fragment会重新加载，
         * 重新创建布局。这样会引起一个问题：如果布局里有较多的View，
         * 则每次tab项切换时重新创建布局会很浪费时间，并且重新创建后，也保存不了之前的状态。
         * 我们可以通过复写fragmenttabhost实现，将销毁和添加的方法以hide和show代替
         *
         * 我们先判断当前view为空 则去加载布局文件 不然直接返回
         * 且view是否已经有parnet 若有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
         */
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment1, container, false);
            InitView();


        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private void InitView() {
        listView = (ListView) rootView.findViewById(R.id.fragment1_listview);

        listBean = new ArrayList<XiaohuaBean>();
        adapter = new XiaohuaListAdapter(listBean, listView, getContext());
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);

        new MyAsyncTask().execute(pageCount++);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState)
        {
            case SCROLL_STATE_FLING:
                Log.i("iii","用户在手指离开之前，用力的划一下 试图惯性滑动");
              //  new MyAsyncTask().execute(pageCount++);
                break;
            case SCROLL_STATE_IDLE:
                Log.i("iii","空闲 试图停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("iii","手指没有离开屏幕，视图正在滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        /**
         * firstVisibleItem 表示在当前屏幕显示的第一个listItem在整个listView里面的位置（下标从0开始）
         * visibleItemCount表示在现时屏幕可以见到的ListItem(部分显示的ListItem也算)总数
         * totalItemCount表示ListView的ListItem总数
         * listView.getLastVisiblePosition()表示在现时屏幕最后一个ListItem
         * (最后ListItem要完全显示出来才算)在整个ListView的位置（下标从0开始）
         */
        if(listView.getLastVisiblePosition() + 2 >= totalItemCount)
        {
            new MyAsyncTask().execute(pageCount++);
        }

    }


    class MyAsyncTask extends AsyncTask<Integer,Void,List<XiaohuaBean>>{

        @Override
        protected List<XiaohuaBean> doInBackground(Integer... params) {
      //      List<XiaohuaBean> tmpListBean = new ArrayList<XiaohuaBean>();

            String appid = "22021";//要替换成自己的
            String secret = "8c8fa76935b44d959fada3656a812a61";//要替换成自己的
            final String res=new ShowApiRequest( "http://route.showapi.com/341-1", appid, secret)
                    .addTextPara("time", "2015-07-10")
                    .addTextPara("page", "" + params[0] )
                    .addTextPara("maxResult", "10")
                    .post();


            Log.i("iii",res);

            try {
                JSONObject jsonObject = new JSONObject(res);
                jsonObject = jsonObject.getJSONObject("showapi_res_body");
                int allnum = jsonObject.getInt("allNum");
                Log.i("iii","json allnum = " + allnum);
             //   jsonObject = jsonObject.getJSONObject("contentlist");
                JSONArray jsonArray = jsonObject.getJSONArray("contentlist");
                Log.i("iii","json array = " + jsonArray);

                XiaohuaBean xiaohuaBean;
                for(int i = 0; i < jsonArray.length(); i++)
                {
                    jsonObject = jsonArray.getJSONObject(i);

                    xiaohuaBean = new XiaohuaBean();
                    xiaohuaBean.title = jsonObject.getString("title");
                    xiaohuaBean.content = jsonObject.getString("text");
                    listBean.add(xiaohuaBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("iii","jsonobj 创建失败");
            }

            return listBean;
        }

        @Override
        protected void onPostExecute(List<XiaohuaBean> xiaohuaBeen) {
            super.onPostExecute(xiaohuaBeen);
            adapter.notifyDataSetChanged();
        }
    }

}
