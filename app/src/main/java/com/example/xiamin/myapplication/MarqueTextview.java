package com.example.xiamin.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Xiamin on 2016/6/20.
 */
public class MarqueTextview extends TextView {

    public MarqueTextview(Context context) {
        super(context);
    }

    public MarqueTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}
