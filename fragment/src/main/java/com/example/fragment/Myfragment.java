package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Xiamin on 2016/6/25.
 */
public class Myfragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //如何把layout文件转换成view对象

        //第一个是需要加载的布局文件 第二个是加载layout的父viewgroup
        //第三个是false 不反回
        View view = inflater.inflate(R.layout.fragment, container, false);
        TextView text = (TextView) view.findViewById(R.id.Fragment_textView);
        text.setText("w gai guo l ");
        Log.i("iii", "我改过了");
        return view;
    }
}
