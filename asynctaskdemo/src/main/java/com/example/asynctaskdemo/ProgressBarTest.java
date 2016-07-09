package com.example.asynctaskdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by Xiamin on 2016/7/9.
 */
public class ProgressBarTest extends AppCompatActivity {

    private ProgressBar progressBar;
    private AsyncTask<Void, Integer, Void> mytask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mytask =  new ProgressAsyncTask();
        mytask.execute();
    }

    @Override
    protected void onPause() {
        if(mytask != null)
        {
            if(mytask.getStatus() == AsyncTask.Status.RUNNING)
            {
                mytask.cancel(true);  //cancel 只是将对应AsyncTask标记为cancel状态 并不是真正的取消线程执行
            }
        }
        super.onPause();
    }

    class ProgressAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(isCancelled())
            {
                return;
            }
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * 模拟进度更新
             */


            for (int i = 0; i < 100; i++) {

                if(isCancelled())
                {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);


            }
            return null;
        }
    }
}
