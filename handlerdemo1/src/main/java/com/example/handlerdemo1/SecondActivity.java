package com.example.handlerdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Xiamin on 2016/7/2.
 */
public class SecondActivity extends Activity {

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Log.i("iii","ui____ Thread : " + Thread.currentThread());
            super.handleMessage(msg);
        }
    };

    private Mythread thread;
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        textView.setText("xiamin");
        thread = new Mythread();
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(2);

    }


    class Mythread extends Thread{
        public Handler handler;
        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    Log.i("iii","cur Thread : " + Thread.currentThread());
                    super.handleMessage(msg);
                }
            };
        }
    }

}
