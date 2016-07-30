package com.example.wechatindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class SimpleFragment extends android.support.v4.app.Fragment {
    private String mtitle;
    public  static final String TITLE = "title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            mtitle = bundle.getString(TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setText(mtitle);
        tv.setGravity(Gravity.CENTER);

        return tv;

    }

    public static SimpleFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(TITLE,title);

        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

