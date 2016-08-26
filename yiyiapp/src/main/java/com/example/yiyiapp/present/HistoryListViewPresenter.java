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
    /**
     * 维护一个list，插入时需要判断是否已经存在
     */
    private List<HistoryInfoBean> wordList;

    public HistoryListViewPresenter() {
        initList();
    }


    private void initList() {
        wordList = new ArrayList<HistoryInfoBean>();

        MySqliteOpenHelper helper = new MySqliteOpenHelper(MainActivity.getContext(), "search_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from search_db", null);
        if (cursor != null) {
            Log.i("iii", "getColumnCount :" + cursor.getColumnCount());
            while (cursor.moveToNext()) {
                Log.i("iii", "id:" + cursor.getInt(cursor.getColumnIndex("_id")));
                Log.i("iii", "name:" + cursor.getString(cursor.getColumnIndex("word")));
                Log.i("iii", "name:" + cursor.getString(cursor.getColumnIndex("result")));
                HistoryInfoBean bean = new HistoryInfoBean();
                bean.src = cursor.getString(cursor.getColumnIndex("word"));
                bean.result = cursor.getString(cursor.getColumnIndex("result"));
                wordList.add(bean);
            }
            cursor.close();
        } else {
            Log.i("iii", "cursor is null");
        }
        db.close();
        helper.close();
    }

    public void ListGetInfo(ArrayList<HistoryInfoBean> list) {

    }

    public void SqliteRead(List<HistoryInfoBean> list) {
        /**
         * 该list位于外部 因此不能直接使用list = wordlist，因为wordlist会被释放
         */
        for (HistoryInfoBean h : wordList) {
            list.add(h);
        }
    }

    public void InsertToHistory(HistoryInfoBean bean) {
        /**
         * 判断该单词是否已经存在在历史记录中
         */
        for (HistoryInfoBean k : wordList) {
            if (k.src.equals(bean.src)) {
                Log.i("iii", "历史纪录已经存在");
                return;
            }
        }

        MySqliteOpenHelper helper = new MySqliteOpenHelper(MainActivity.getContext(), "search_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("word", bean.src);
        values.put("result", bean.result);
        db.insert("search_db", null, values);
        Log.i("iii", "InsertToHistory" + ": " + bean.src + " " + bean.result);
        db.close();
        helper.close();
    }

    public void DeleteFromHistory(HistoryInfoBean bean) {
        MySqliteOpenHelper helper = new MySqliteOpenHelper(MainActivity.getContext(), "search_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("word", bean.src);
        db.delete("search_db", "word=?", new String[]{bean.src});
        Log.i("iii", "DeleteFromHistory" + ": " + bean.src + " " + bean.result);
        db.close();
        helper.close();
    }

}
