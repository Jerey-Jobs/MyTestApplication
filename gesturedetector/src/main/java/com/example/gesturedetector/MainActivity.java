package com.example.gesturedetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private GestureDetector gestureDetector;
    class MygestureListener2 extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("iii","onScroll" + e2.getX()+ "-" + e1.getX() );
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("iii",e2.getX()+ "-" + e1.getX() );
            if (e2.getX() - e1.getX() > 100)
            {
                Log.i("iii","onFling");
                Toast.makeText(MainActivity.this,"左向右滑动",Toast.LENGTH_SHORT).show();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.myimg);
        gestureDetector = new GestureDetector(MainActivity.this,new MygestureListener2());

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override //捕获到触摸屏幕发生的Event事件
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("iii","motionEvent");

                gestureDetector.onTouchEvent(motionEvent);
                return true; //
            }
        });
    }

}
