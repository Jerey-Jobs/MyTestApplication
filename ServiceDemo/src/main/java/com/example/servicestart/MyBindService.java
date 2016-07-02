package com.example.servicestart;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Xiamin on 2016/7/2.
 */
public class MyBindService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("iii","Service onBind");


        return new Mybinder();
    }

    public class Mybinder extends Binder
    {
        public MyBindService getservice()
        {
            return MyBindService.this;
        }

    }

    @Override
    public void onCreate() {
        Log.i("iii","Service onCreate");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("iii","Service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("iii","Service onDestroy");
        super.onDestroy();
    }


    public void play()
    {
        Log.i("iii","播放");
    }

    public void pause()
    {
        Log.i("iii","暂停");
    }

    public void nextmusic()
    {
        Log.i("iii","下一首");
    }

    public void premusic()
    {
        Log.i("iii","上一首");
    }


}
