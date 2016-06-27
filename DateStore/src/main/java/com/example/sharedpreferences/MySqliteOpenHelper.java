package com.example.sharedpreferences;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Xiamin on 2016/6/27.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {


    public MySqliteOpenHelper(Context context, String name) {
        super(context, name, null,1);
    }

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    //首次创建数据库的时候调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists userdb2 (_id integer primary key autoincrement,name text not null," +
                " age integer not null,sex text not null)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","qqqqqq");
        contentValues.put("age",234);
        contentValues.put("sex","nsadf");
        sqLiteDatabase.insert("userid",null, contentValues);
    }

    //当数据库版本发生变化时
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
