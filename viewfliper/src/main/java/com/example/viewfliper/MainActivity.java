package com.example.viewfliper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private final int[] resid = {R.drawable.qq1, R.drawable.qq2, R.drawable.qq3};
    private float startx;
   // private float endx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("iii","start!");
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        //动态导入的方式为
        for (int i = 0; i < resid.length; i++) {
            viewFlipper.addView(getView(resid[i]));
        }

        Log.i("iii","start setInAnimation");
        //设置加载动画
        // viewFlipper.setInAnimation(thi);
//        viewFlipper.setInAnimation(null);
//        viewFlipper.setOutAnimation(null);
        //设置试图切换时间间隔
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
        Log.i("iii","start startFlipping");

    }

    private ImageView getView(int id) {
        ImageView imageView = new ImageView(this);
        //imageView.setImageResource(id);
        imageView.setBackgroundResource(id);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指已经落下
            case MotionEvent.ACTION_DOWN:
                startx = event.getX();
                break;
                //手指滑动
            case MotionEvent.ACTION_MOVE: {
                if(event.getX()- startx > 100)
                {
                    viewFlipper.showNext();
                }
                else if(event.getX() - startx < -100)
                {
                    viewFlipper.showPrevious();
                }
                break;
            }
            //手指抬起
            case MotionEvent.ACTION_UP:
                break;
        }


        return super.onTouchEvent(event);
    }
}
