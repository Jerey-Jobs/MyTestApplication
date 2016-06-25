package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton first;
    private RadioButton second;
    private RadioButton third;
    private RadioButton forth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IDInit();

    }

    private void IDInit() {
        first = (RadioButton) findViewById(R.id.first);
        second = (RadioButton) findViewById(R.id.second);
        third = (RadioButton) findViewById(R.id.end);
        forth = (RadioButton) findViewById(R.id.four);

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        forth.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first:
                Log.i("iii", "first");
                Intent intent = new Intent(this, Activity2.class);
                startActivity(intent);
                break;
            case R.id.second:
                Myfragment2 myfragment2 = new Myfragment2();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Frame,myfragment2).commit();
                //增加可以返回的方法
                fragmentManager.beginTransaction().addToBackStack(null);
                Log.i("iii", "second");
                break;

            case R.id.end:
                Log.i("iii", "third");
                break;

            case R.id.four:
                Log.i("iii", "forth");
                break;

        }
    }
}
