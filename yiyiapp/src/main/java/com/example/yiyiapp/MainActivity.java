package com.example.yiyiapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yiyiapp.present.MainPresenter;
import com.example.yiyiapp.service.ListenClipboardService;
import com.example.yiyiapp.util.myToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextInput;
    private Button buttonTranslate;
    private TextView textViewResult;
    private static Context context;
    private MainPresenter presenter = new MainPresenter();
    private static myToast toast ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        InitView();
        checkNetworkState();
        toast = new myToast((ViewGroup)findViewById(R.id.llToast));


        Intent serviceIntent = new Intent(MainActivity.this, ListenClipboardService.class);
        context.startService(serviceIntent);
    }

    private void InitView() {
        editTextInput = (EditText) findViewById(R.id.main_edit_text);
        buttonTranslate = (Button) findViewById(R.id.main_ensure_button);
        textViewResult = (TextView) findViewById(R.id.main_result_textview);

        buttonTranslate.setOnClickListener(this);

//        ActionBar actionBar = getSupportActionBar();
//        /**显示回退键，默认为false*/
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    /**
     * 检测网络是否连接
     *
     * @return
     */
    private void checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
            Toast.makeText(this, "无网络连接！", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "网络准备充分！", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_ensure_button: {
                Log.i("iii","button pressed!");
                presenter.MainPresenterget(editTextInput.getText().toString(),textViewResult,buttonTranslate);
                break;
            }
        }
    }

    public static Context getContext()
    {
        return context;
    }
    public static myToast getToast() {return toast;}
}
