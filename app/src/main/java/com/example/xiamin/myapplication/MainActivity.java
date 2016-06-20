package com.example.xiamin.myapplication;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private EditText usrName;
    private EditText usrPassword;
    private EditText usrPassword2;
    private Button enSureButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将布局xml文件加载
        setContentView(R.layout.activity_main);
        System.out.println("sad");
        Log.i("xiamin", "keyi");
        Log.e("testerror", "sdr");
        Log.e("testerror", "sdr");
        usrName = (EditText) findViewById(R.id.usr_name);
        usrPassword = (EditText) findViewById(R.id.usr_name);
        usrPassword2 = (EditText) findViewById(R.id.usr_name);
        enSureButton = (Button) findViewById(R.id.confirm_button);

      //  enSureButton.setOnClickListener();


    }

}
