package com.example.yiyiapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yiyiapp.present.ServicePresenter;

/**
 * Created by Xiamin on 2016/7/31.
 */
public class ListenClipboardService extends Service {

    private ServicePresenter presenter;

    @Override
    public void onCreate() {
        Log.i("iii","Service onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("iii","onStartCommand");
        /**
         * 在presenter中获取service的管理结构，接下来所有业务都是presenter完成
         */
        presenter = new ServicePresenter();
        presenter.SetClipBoardListener();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
