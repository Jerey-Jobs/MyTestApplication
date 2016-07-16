package com.vincent.tabdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vincent.tabdemo.Bean.XiaohuaBean;
import com.vincent.tabdemo.R;

import java.util.List;

/**
 * Created by Xiamin on 2016/7/16.
 */
public class XiaohuaListAdapter extends BaseAdapter {
    private List<XiaohuaBean> list;
    private ListView listView;
    private Context context;
    private LayoutInflater inflater;

    public  XiaohuaListAdapter(List<XiaohuaBean> list, ListView listView, Context context)
    {
        this.list = list;
        this.listView = listView;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.xiaohua_item,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(list.get(position).title);
        viewHolder.content.setText(list.get(position).content);

        return convertView;
    }

    class ViewHolder{
        public TextView title;
        public TextView content;
    }
}
