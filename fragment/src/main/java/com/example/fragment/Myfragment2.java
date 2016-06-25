package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Xiamin on 2016/6/25.
 */
public class Myfragment2 extends Fragment {
    private Button button;
    private TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //如何把layout文件转换成view对象

        //第一个是需要加载的布局文件 第二个是加载layout的父viewgroup
        //第三个是false 不反回
        View view = inflater.inflate(R.layout.fragment2, container, false);
         text = (TextView) view.findViewById(R.id.Fragment2_textView);
        text.setText("动态加载 ");
        Log.i("iii", "动态加载");
        button = (Button) view.findViewById(R.id.Fragment2_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("text改变");
            }
        });


        return view;
    }
}
