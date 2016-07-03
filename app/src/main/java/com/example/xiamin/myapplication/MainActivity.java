package com.example.xiamin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        usrName = (EditText) findViewById(R.id.usr_name);
        usrPassword = (EditText) findViewById(R.id.pro_password);
        usrPassword2 = (EditText) findViewById(R.id.confirm_password);
        enSureButton = (Button) findViewById(R.id.confirm_button);

        enSureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, usrName.getText() + "m:" + usrPassword.getText(),
                        Toast.LENGTH_LONG).show();
                /*
                *intent 第一个参数 上下文对象
                *       第二个参数 目标文件
                */


                Intent intent = new Intent(MainActivity.this, controlActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.activityin,R.anim.activityout);

            }
        });
    }

    /*接收其他activity传来的数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.i("tag","1 == ");
            if(resultCode == 2)
            {
                String str = data.getStringExtra("data");
                Log.i("tag",str);
            }

        }
    }
}
