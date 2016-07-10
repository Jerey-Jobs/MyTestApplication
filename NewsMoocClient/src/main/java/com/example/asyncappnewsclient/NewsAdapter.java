package com.example.asyncappnewsclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Xiamin on 2016/7/10.
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    private List<NewsBean> mlist;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private int mstart, mend;
    public static String[] urls;
    private boolean isFirstLoad = true;

    public NewsAdapter(List<NewsBean> mlist, Context context, ListView listView) {
        this.mlist = mlist;
        this.inflater = LayoutInflater.from(context);
        urls = new String[mlist.size()];
        listView.setOnScrollListener(this);
        imageLoader = new ImageLoader(listView);
        for (int i = 0; i < mlist.size(); i++)
        {
            urls[i] = mlist.get(i).newsImageUrl;
        }
    }

    @Override
    public int getCount() {

        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.newsitem_layout, null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.item_image);
            viewHolder.title = (TextView) view.findViewById(R.id.item_titile);
            viewHolder.content = (TextView) view.findViewById(R.id.item_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.imageView.setTag(mlist.get(i).newsImageUrl);
        imageLoader.showImageByAsyncTask(viewHolder.imageView, mlist.get(i).newsImageUrl);
        viewHolder.title.setText(mlist.get(i).newsTitle);
        viewHolder.content.setText(mlist.get(i).newsContent);

        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == SCROLL_STATE_IDLE)   //若当前状态为停止状态，加载可见项
        {
            imageLoader.loadImage(mstart,mend);
        } else {
            //停止所有加载任务
            imageLoader.cancelAllTask();
        }
    }

    /**
     * @param absListView
     * @param i           第一个可见元素
     * @param i1          可见元素的长度
     * @param i2
     */
    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                mstart = i;
        mend = i + i1;
        if(isFirstLoad == true && i1 > 0)
        {
            imageLoader.loadImage(i,i1);
            isFirstLoad = false;
        }
    }

    class ViewHolder {
        public TextView title;
        public TextView content;
        public ImageView imageView;
    }
}
