package com.example.yiyiapp.present;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yiyiapp.Db.MySqliteOpenHelper;
import com.example.yiyiapp.MainActivity;
import com.example.yiyiapp.util.HistoryInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiamin on 2016/7/31.
 */
public class HistoryListViewPresenter {

    public void ListGetInfo(ArrayList<HistoryInfoBean> list)
    {

    }

    public void SqliteRead(List<HistoryInfoBean> list)
    {

        MySqliteOpenHelper helper =  new MySqliteOpenHelper(MainActivity.getContext(),"search_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor =  db.rawQuery("select * from search_db",null);
        if(cursor != null) {
            Log.i("iii","getColumnCount :" + cursor.getColumnCount());
            while(cursor.moveToNext())
            {
                Log.i("iii","id:"+ cursor.getInt(cursor.getColumnIndex("_id")));
                Log.i("iii","name:"+ cursor.getString(cursor.getColumnIndex("word")));
                Log.i("iii","name:"+ cursor.getString(cursor.getColumnIndex("result")));
                HistoryInfoBean bean = new HistoryInfoBean();
                bean.src = cursor.getString(cursor.getColumnIndex("word"));
                bean.result = cursor.getString(cursor.getColumnIndex("result"));
                list.add(bean);
            }
            cursor.close();
        }
        else {
            Log.i("iii","cursor is null");
        }
        db.close();
        helper.close();
    }

    public void InsertToHistory(HistoryInfoBean bean)
    {
        MySqliteOpenHelper helper =  new MySqliteOpenHelper(MainActivity.getContext(),"search_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("word",bean.src);
        values.put("result",bean.result);
        db.insert("search_db",null,values);
        Log.i("iii","InsertToHistory" + ": " +bean.src + " "+  bean.result);
        db.close();
        helper.close();
    }

    public void DeleteFromHistory(HistoryInfoBean bean)
    {
        MySqliteOpenHelper helper =  new MySqliteOpenHelper(MainActivity.getContext(),"search_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("word",bean.src);
        db.delete("search_db", "word=?", new String[] {bean.src});
        Log.i("iii","DeleteFromHistory" + ": " +bean.src + " "+  bean.result);
        db.close();
        helper.close();
    }

}
