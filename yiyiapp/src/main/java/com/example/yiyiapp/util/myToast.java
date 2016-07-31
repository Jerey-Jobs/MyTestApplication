package com.example.yiyiapp.util;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yiyiapp.MainActivity;
import com.example.yiyiapp.R;

/**
 * Created by Xiamin on 2016/7/31.
 */
public class myToast {
    Toast toast;
    TextView title;
    View layout;
    TextView text;

    public myToast(ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.getContext());
        layout = inflater.inflate(R.layout.toast_view, viewGroup);
        title = (TextView) layout.findViewById(R.id.tvTitleToast);
        text = (TextView) layout.findViewById(R.id.tvTextToast);
    }


    /**
     *
     * @param src
     * @param res
     */
    public void show(String src, String res) {
        Log.i("iii","toast show");
        if(src.equals("") || res.equals("") || res.equals("no query"))
        {
            return;
        }
        title.setText(src);
        text.setText(res);
        toast = new Toast(MainActivity.getContext());
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
