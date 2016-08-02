package com.example.yiyiapp.present;

import android.widget.Button;
import android.widget.TextView;

import com.example.yiyiapp.MyAsyncTaskGetResult;
import com.example.yiyiapp.util.HistoryInfoBean;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class MainPresenter {

    public void MainPresenterget(final String url, final TextView textView, final Button button) {
        if (url.toString().equals("")) {
            return;
        }
        button.setText("翻译中...");
        button.setClickable(false);
        MyAsyncTaskGetResult asyncTaskGetResult = new MyAsyncTaskGetResult(url) {
            @Override
            public void taskSuccessful(String json) {
                textView.setText(json);
                button.setText("翻译");
                button.setClickable(true);


                if (!url.toString().equals("") && !json.toString().equals("no query")) {
                    HistoryInfoBean bean;
                    HistoryListViewPresenter presenter = new HistoryListViewPresenter();
                    bean = new HistoryInfoBean();
                    bean.src = url;
                    bean.result = json;
                    presenter.InsertToHistory(bean);
                }
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
