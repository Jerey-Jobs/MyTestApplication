package com.example.handlerdemo1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private Handler handler;
    private TextView textView;



    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            Log.i("iii","runner");
            imageView.setVisibility(View.INVISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onclick(View v)
    {
        switch (v.getId())
        {
            case R.id.button:
            {
                handler.post(runner);
            }
        }
    }
}
