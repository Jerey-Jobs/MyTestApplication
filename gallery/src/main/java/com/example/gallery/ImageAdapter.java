package com.example.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Xiamin on 2016/6/27.
 */
public class ImageAdapter extends BaseAdapter {
    private int[] res;
    private Context context;

    public ImageAdapter(int res[], Context c) {
        this.context = c;
        this.res = res;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return res[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        imageView = new ImageView(context);
        imageView.setBackgroundResource(res[i % res.length]);
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 100));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
