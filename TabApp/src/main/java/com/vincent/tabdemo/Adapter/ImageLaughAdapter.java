package com.vincent.tabdemo.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.show.api.ShowApiRequest;
import com.vincent.tabdemo.Bean.XiaohuaBean;
import com.vincent.tabdemo.R;
import com.vincent.tabdemo.tools.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiamin on 2016/7/17.
 */
public class ImageLaughAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private List<XiaohuaBean> list;
    private ListView listView;
    private Context context;
    private LayoutInflater inflater;

    private ImageLoader imageLoader;

    private int mstart;
    private int mend;
    private boolean isFirstLoad = true;

    public ImageLaughAdapter(List<XiaohuaBean> list, ListView listView, Context context) {
        this.list = list;
        this.listView = listView;

        listView.setOnScrollListener(this);
        imageLoader = new ImageLoader(listView);
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        Log.i("iii", "ImageLaughAdapter");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.xiaohua_image_item, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.image_item_title);
            viewHolder.content = (ImageView) convertView.findViewById(R.id.iamge_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.title.setText(list.get(position).title);
        /**将每个image和对应url绑定 避免错乱显示*/
        viewHolder.content.setTag(list.get(position).content);
    //    viewHolder.content.setImageResource(R.mipmap.ic_launcher);
        imageLoader.showImageByAsyncTask(viewHolder.content,list.get(position).content);


        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mstart = firstVisibleItem;
        mend = firstVisibleItem + visibleItemCount;
        if (isFirstLoad == true && visibleItemCount > 0) {
            Log.i("iii", "onScroll isFirstLoad");

            //    imageLoader.loadImage(firstVisibleItem, visibleItemCount,list);
            isFirstLoad = false;
        }

        if((listView.getLastVisiblePosition() + 1) >= totalItemCount)
        {
            new MyAsyncTaskGeJoke().execute(20);
        }
    }



    class ViewHolder {
        public TextView title;
        public ImageView content;
    }

    class MyAsyncTaskGeJoke extends AsyncTask<Integer, Void, List<XiaohuaBean>> {

        @Override
        protected List<XiaohuaBean> doInBackground(Integer... params) {
            List<XiaohuaBean> tmp = new ArrayList<XiaohuaBean>();

            String appid = "22021";//要替换成自己的
            String secret = "8c8fa76935b44d959fada3656a812a61";//要替换成自己的
            final String res = new ShowApiRequest("http://route.showapi.com/341-2", appid, secret)
                    .addTextPara("time", "2015-07-10")
                    .addTextPara("page", "" + params[0])
                    .addTextPara("maxResult", "10")
                    .post();


            Log.i("iii", res);

            try {
                JSONObject jsonObject = new JSONObject(res);
                jsonObject = jsonObject.getJSONObject("showapi_res_body");
                int allnum = jsonObject.getInt("allNum");
                //   Log.i("iii","json allnum = " + allnum);
                //   jsonObject = jsonObject.getJSONObject("contentlist");
                JSONArray jsonArray = jsonObject.getJSONArray("contentlist");
                //   Log.i("iii","json array = " + jsonArray);

                XiaohuaBean xiaohuaBean;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);

                    xiaohuaBean = new XiaohuaBean();
                    xiaohuaBean.title = jsonObject.getString("title");
                    xiaohuaBean.content = jsonObject.getString("img");
                    tmp.add(xiaohuaBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("iii", "jsonobj 创建失败");
            }

            return tmp;
        }

        @Override
        protected void onPostExecute(List<XiaohuaBean> xiaohuaBeen) {

            super.onPostExecute(xiaohuaBeen);
            for (XiaohuaBean k : xiaohuaBeen) {
                list.add(k);
            }
            ImageLaughAdapter.this.notifyDataSetChanged();
        }
    }


//    class MyThreadGeJoke extends HandlerThread {
//        public MyThreadGeJoke(String name) {
//            super(name);
//        }
//
//        @Override
//        public void run() {
//            super.run();
//        }
//
//
//    }


    public void getJoke(int page)
    {
        new MyAsyncTaskGeJoke().execute(page);
    }

    public void clearJoke()
    {
        list.clear();
        listView.clearDisappearingChildren();

    }

}
