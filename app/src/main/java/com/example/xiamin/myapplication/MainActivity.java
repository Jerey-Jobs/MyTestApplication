package com.example.xiamin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("sad");
        TextView text = (TextView) findViewById(R.id.text1);
        text.setText("xiamin");
        Log.i("xiamin","keyi");
        Log.e("testerror","sdr");
    }

    protected void onStart()
    {
        super.onStart();


    }
}
