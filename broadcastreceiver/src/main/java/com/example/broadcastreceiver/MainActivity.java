package com.example.broadcastreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
    }

    public void doclick(View v)
    {
        switch (v.getId())
        {
            case R.id.button1:
            {
                Intent intent = new Intent();
                intent.putExtra("msg","common broadcast");
                intent.setAction("BC_one");
                sendBroadcast(intent);
                break;
            }
        }
    }
}
