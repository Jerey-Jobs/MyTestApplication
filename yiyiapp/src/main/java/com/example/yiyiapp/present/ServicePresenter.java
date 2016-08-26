package com.example.yiyiapp.present;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yiyiapp.MainActivity;
import com.example.yiyiapp.MyAsyncTaskGetResult;
import com.example.yiyiapp.service.ListenClipboardService;
import com.example.yiyiapp.util.HistoryInfoBean;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class ServicePresenter {
    ClipboardManager clipboardManager = (ClipboardManager) MainActivity.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
    private long pritime;

    public void SetClipBoardListener()
    {
        clipboardManager.setPrimaryClip(ClipData.newPlainText("",""));
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {

 //               Toast.makeText(MainActivity.getContext(),"错误的格式", Toast.LENGTH_SHORT).show();
                /**
                 * 解决粘贴板重复响应的问题，200ms内不重复相应（但是好像并没有太大的改善）
                 */
                long now = System.currentTimeMillis();
                if(now - pritime <= 200 && clipboardManager.getText().toString().equals(""))
                {
                    pritime = now;
                    return;
                }
                pritime = now;
                String str = clipboardManager.getText().toString();
                Log.i("iii","onPrimaryClipChanged" + str);

                MyAsyncTaskGetResult myAsyncTaskGetResult = new MyAsyncTaskGetResult(str) {
                    @Override
                    public void taskSuccessful(String json) {
                  //      Toast.makeText(MainActivity.getContext(),json, Toast.LENGTH_SHORT).show();
                        MainActivity.getToast().show(clipboardManager.getText().toString(),json);
                        /**
                         *将记录插入到历史纪录中，合理的方法是维护一个历史纪录的表，插入时判断是否已经存在
                         * 不然会出现历史纪录重复的问题
                         */
                        HistoryInfoBean bean;
                        HistoryListViewPresenter presenter = new HistoryListViewPresenter();
                        bean = new HistoryInfoBean();
                        bean.src = clipboardManager.getText().toString();
                        bean.result = json;
                        presenter.InsertToHistory(bean);
                    }

                    @Override
                    public void taskFailed() {

                    }
                };
                myAsyncTaskGetResult.execute();
            }
        });
    }

    public static void start(Context context) {
        Intent serviceIntent = new Intent(context, ListenClipboardService.class);
        context.startService(serviceIntent);
    }

}
