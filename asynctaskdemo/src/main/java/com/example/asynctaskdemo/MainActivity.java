package com.example.asynctaskdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    MyAsyncTask task = new MyAsyncTask();
     //   task.execute();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ImageTask.class);
                startActivity(intent);
            }
        });
    }

    void button2OnClick(View v)
    {
        Intent intent = new Intent(MainActivity.this,ProgressBarTest.class);
        startActivity(intent);
    }
}
