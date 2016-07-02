package com.example.servicestart;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button StartService;
    private Button StopService;
    private Button BindService;
    private Button UnBindService;
    private Button play;
    private Button pause;
    private Button nextMusic;
    private Button preMusic;
    private Intent intent1 = null;
    private MyBindService servicebinder;
    private ServiceConnection connet = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
           servicebinder =  ((MyBindService.Mybinder)iBinder).getservice();
        }

        //当启动源和service连接意外丢失时会调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void doClick(View v) {
        Log.i("iii", "doClick");

        switch (v.getId()) {
            case R.id.startService: {
                intent1 = new Intent(MainActivity.this, MyStartService.class);
                startService(intent1);
                break;
            }
            case R.id.StopServide: {
                stopService(intent1);
                break;
            }
            case R.id.BindService: {
                Intent bindintent = new Intent(MainActivity.this,MyBindService.class);
                bindService(bindintent,connet, Service.BIND_AUTO_CREATE);

                break;
            }
            case R.id.UnbindService: {
                unbindService(connet);
                break;
            }
            case R.id.play: {
                servicebinder.play();
                break;
            }
            case R.id.pause: {
                servicebinder.pause();
                break;
            }
            case R.id.next_music: {
                servicebinder.nextmusic();
                break;
            }
            case R.id.premusic: {
                servicebinder.premusic();
                break;
            }
        }

    }

}
