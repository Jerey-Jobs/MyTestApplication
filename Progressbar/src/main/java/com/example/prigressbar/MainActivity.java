package com.example.prigressbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private Button show;
    private TextView textView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启用窗口特征 启用带进度和不带进度的进度条
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_main);

        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        setProgress(200);

        Init();

    }

    public void Init() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        add = (Button) findViewById(R.id.button);
        reduce = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.text);
        show = (Button) findViewById(R.id.button4);
        int firstBar = progressBar.getProgress();
        int secondBar = progressBar.getSecondaryProgress();
        int max = progressBar.getMax();
        textView.setText("First:" + (int) (((float) firstBar / max) * 100));
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //add
            case R.id.button:
                progressBar.incrementProgressBy(10);
                break;
            //reduce
            case R.id.button2:
                progressBar.incrementProgressBy(-10);
                break;
            //reset
            case R.id.button3:
                progressBar.setProgress(50);
                break;
            //对话框形式显示
            case R.id.button4:
                Log.i("iii","显示1");
                //新建ProgressDialog对象
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("xiamin");
                progressDialog.setMessage("欢迎");
              //  progressDialog.setIcon(R.mipmap.ic_launcher);

                //设置进度条属性
                progressDialog.setMax(100);
                progressDialog.setProgress(40);
                progressDialog.setSecondaryProgress(80);
                progressDialog.setIndeterminate(false);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(true);
                progressDialog.show();
                Log.i("iii","显示2");
                break;
        }
        textView.setText("First:" + (int) (((float) progressBar.getProgress() / progressBar.getMax()) * 100));
    }
}
