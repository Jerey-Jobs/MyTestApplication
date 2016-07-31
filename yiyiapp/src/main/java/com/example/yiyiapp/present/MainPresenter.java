package com.example.yiyiapp.present;

import android.widget.Button;
import android.widget.TextView;

import com.example.yiyiapp.MyAsyncTaskGetResult;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class MainPresenter {

    public void MainPresenterget(String url, final TextView textView, final Button button)
    {
        button.setText("翻译中...");
        button.setClickable(false);
        MyAsyncTaskGetResult asyncTaskGetResult = new MyAsyncTaskGetResult(url) {
            @Override
            public void taskSuccessful(String json) {
                textView.setText(json);
                button.setText("翻译");
                button.setClickable(true);
            }

            @Override
            public void taskFailed() {
                textView.setText("翻译失败");
                button.setText("翻译");
                button.setClickable(true);
            }
        };
        asyncTaskGetResult.execute();
    }
}
