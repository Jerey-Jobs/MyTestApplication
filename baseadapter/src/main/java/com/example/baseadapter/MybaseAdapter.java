package com.example.baseadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Xiamin on 2016/7/8.
 */
public class MybaseAdapter extends BaseAdapter {
    private List<ItemBean> list;
    private LayoutInflater inflater;  //将xml转换成布局

    public MybaseAdapter(List<ItemBean> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(MainActivity.getContext());
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        /**
         * 逗逼方案，每次创建view 不重复使用
         */
//        View myview = inflater.inflate(R.layout.item,null);
//        ImageView imageView = (ImageView) myview.findViewById(R.id.iv_image);
//        TextView title = (TextView) myview.findViewById(R.id.iv_title);
//        TextView text = (TextView) myview.findViewById(R.id.iv_text);
//
//        imageView.setImageResource(list.get(i).ItemImageResId);
//        title.setText(list.get(i).Itemstring);
//        text.setText(list.get(i).ItemContent);
        // return myview;

        /**
         * 普通方案 还是得每次去findviewbyid 不过不需要每次都去将layout转换成view
         */

//        if(view == null)
//        {
//            view = inflater.inflate(R.layout.item,null);
//        }
//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
//        TextView title = (TextView) view.findViewById(R.id.iv_title);
//        TextView text = (TextView) view.findViewById(R.id.iv_text);
//
//        imageView.setImageResource(list.get(i).ItemImageResId);
//        title.setText(list.get(i).Itemstring);
//        text.setText(list.get(i).ItemContent);
//        return view;


        /**
         * 文艺式
         * viewhodler优化baseadapter 使用保存id的方法
         * 不仅利用了listview的自带缓存 更缓存了显示数据的视图的缓存
         * 避免多次findViewById
         */

        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item, null);
            viewHolder.HimageView = (ImageView) view.findViewById(R.id.iv_image);
            viewHolder.Htitle = (TextView) view.findViewById(R.id.iv_title);
            viewHolder.Htext = (TextView) view.findViewById(R.id.iv_text);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ItemBean bean = list.get(i);
        viewHolder.HimageView.setImageResource(bean.ItemImageResId);
        viewHolder.Htitle.setText(bean.Itemstring);
        viewHolder.Htext.setText(bean.ItemContent);

        return view;


    }

    class ViewHolder {
        public ImageView HimageView;
        public TextView Htitle;
        public TextView Htext;
    }
}
