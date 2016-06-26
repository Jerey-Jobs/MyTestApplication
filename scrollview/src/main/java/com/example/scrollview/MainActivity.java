package com.example.scrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private ScrollView scrollView;
    private Button up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        textView = (TextView) findViewById(R.id.mytext);
        scrollView = (ScrollView) findViewById(R.id.scroll);
        textView.setText(getResources().getString(R.string.mydata));

        up.setOnClickListener(this);
        down.setOnClickListener(this);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE: {
                        if(scrollView.getScrollY() <= 0)
                        {
                            Log.i("iii","顶部");
                        }

                        if(scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getHeight() + scrollView.getScrollY() )
                        {
                            Log.i("iii","滑动到底部");
                            textView.append(getResources().getString(R.string.mydata));
                        }
                        break;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.up:{
              //  scrollView.scrollTo(0,-30);
                scrollView.scrollBy(0,-30);
                break;
            }

            case R.id.down:{
                scrollView.scrollBy(0,30);
                break;
            }
        }
    }
}
