package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Xiamin on 2016/6/30.
 */
public class MyBC1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("msg");
        Log.i("iii",str);
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
