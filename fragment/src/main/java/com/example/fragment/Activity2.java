package com.example.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Xiamin on 2016/6/25.
 */
public class Activity2 extends Activity {
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        button = (Button) findViewById(R.id.Fragment_button);
        textView = (TextView) findViewById(R.id.Fragment_textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("text改变");
            }
        });
    }
}
