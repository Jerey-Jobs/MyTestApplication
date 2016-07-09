package com.example.asynctaskdemo;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Xiamin on 2016/7/9.
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        Log.i("iii","doInBackground");
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.i("iii","onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.i("iii","onPostExecute");
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.i("iii","onProgressUpdate");
        super.onProgressUpdate(values);
    }
}
