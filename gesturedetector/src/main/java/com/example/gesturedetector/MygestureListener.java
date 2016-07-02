package com.example.gesturedetector;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by Xiamin on 2016/7/2.
 */
public class MygestureListener extends GestureDetector.SimpleOnGestureListener {
    private Context context;

    public MygestureListener(Context context)
    {
        this.context = context;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX() > 100)
        {
            Log.i("iii","onFling");
            Toast.makeText(context,"左向右滑动",Toast.LENGTH_SHORT).show();
        }

        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
