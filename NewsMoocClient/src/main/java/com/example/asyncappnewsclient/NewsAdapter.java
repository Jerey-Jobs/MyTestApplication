package com.example.asyncappnewsclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Xiamin on 2016/7/10.
 */
public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> mlist;
    private LayoutInflater inflater;

    public NewsAdapter(List<NewsBean> mlist, Context context) {
        this.mlist = mlist;
        this.inflater = LayoutInflater.from(context);
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

        if(view == null)
        {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.newsitem_layout,null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.item_image);
            viewHolder.title = (TextView) view.findViewById(R.id.item_titile);
            viewHolder.content = (TextView) view.findViewById(R.id.item_content);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.imageView.setTag(mlist.get(i).newsImageUrl);
        new ImageLoader().showImageByAsyncTask(viewHolder.imageView,mlist.get(i).newsImageUrl);
        viewHolder.title.setText(mlist.get(i).newsTitle);
        viewHolder.content.setText(mlist.get(i).newsContent);

        return view;
    }

    class ViewHolder{
        public TextView title;
        public TextView content;
        public ImageView imageView;
    }
}
