package com.example.systemservicedemo;

import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
    }


    public void doclick(View view) {
        switch (view.getId()) {
            case R.id.button1: {
                WifiManager wifiManager = (WifiManager) MainActivity.this.getSystemService(WIFI_SERVICE);
                if (wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(MainActivity.this, "wifi closed", Toast.LENGTH_SHORT).show();
                } else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(MainActivity.this, "wifi opened", Toast.LENGTH_SHORT).show();
                }

            }
            case R.id.getvoice:
            {
                AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(AUDIO_SERVICE);
                int cut = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
                Toast.makeText(MainActivity.this, "Audio is" + cut, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
