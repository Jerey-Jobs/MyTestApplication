package com.example.yiyiapp.present;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yiyiapp.MainActivity;
import com.example.yiyiapp.MyAsyncTaskGetResult;
import com.example.yiyiapp.service.ListenClipboardService;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class ServicePresenter {
    ClipboardManager clipboardManager = (ClipboardManager) MainActivity.getContext().
            getSystemService(Context.CLIPBOARD_SERVICE);
    private long pritime;



    public void SetClipBoardListener()
    {
        clipboardManager.setPrimaryClip(ClipData.newPlainText("",""));
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {

 //               Toast.makeText(MainActivity.getContext(),"错误的格式", Toast.LENGTH_SHORT).show();
                /**
                 * 解决粘贴板重复响应的问题
                 */
                long now = System.currentTimeMillis();
                if(now - pritime <= 200)
                {
                    pritime = now;
                    return;
                }
                pritime = now;

                Log.i("iii","onPrimaryClipChanged" + clipboardManager.getText());

                MyAsyncTaskGetResult myAsyncTaskGetResult = new MyAsyncTaskGetResult(clipboardManager.getText().toString()) {
                    @Override
                    public void taskSuccessful(String json) {
                  //      Toast.makeText(MainActivity.getContext(),json, Toast.LENGTH_SHORT).show();
                        MainActivity.getToast().show(clipboardManager.getText().toString(),json);
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
