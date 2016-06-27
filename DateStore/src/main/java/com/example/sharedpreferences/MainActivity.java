package com.example.sharedpreferences;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            FileOperate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void Init_sharedPreference()
    {
        //    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        sharedPreferences = getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name","xiamin");

        editor.commit();

        Log.i("iii",sharedPreferences.getString("name",""));

    }

    void SqliteDemo()
    {
        //每个程序都有字节的数据库，默认情况下互相不干扰
        SQLiteDatabase db = openOrCreateDatabase("test",MODE_PRIVATE,null);
        db.execSQL("create table if not exists userdb (_id integer primary key autoincrement,name text not null," +
                " age integer not null,sex text not null)");
        ContentValues values = new ContentValues();
        values.put("name","1111");
        values.put("age",33);
        values.put("name","2222");
        values.put("sex","nan");
        db.insert("userdb",null,values);

//        db.execSQL("insert into userdb(name,sex,age)values('zhangsan','nv',18)");
//        db.execSQL("insert into userdb(name,sex,age)values('lisi','nv',12)");
//        db.execSQL("insert into userdb(name,sex,age)values('ssss','nan',19)");
        //
        Cursor cursor =  db.rawQuery("select * from userdb",null);
        if(cursor != null) {
            Log.i("iii","getColumnCount :" + cursor.getColumnCount());
            while(cursor.moveToNext())
            {
                Log.i("iii","id:"+ cursor.getInt(cursor.getColumnIndex("_id")));
                Log.i("iii","name:"+ cursor.getString(cursor.getColumnIndex("name")));
                Log.i("iii","age:"+ cursor.getInt(cursor.getColumnIndex("age")));
                Log.i("iii","sex:"+ cursor.getString(cursor.getColumnIndex("sex")));
            }
        }
    }

    void SqliteOpenhelperDemo()
    {
       MySqliteOpenHelper helper =  new MySqliteOpenHelper(MainActivity.this,"userdb2");
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor =  db.rawQuery("select * from userdb2",null);
        if(cursor != null) {
            Log.i("iii","getColumnCount :" + cursor.getColumnCount());
            while(cursor.moveToNext())
            {
                Log.i("iii","id:"+ cursor.getInt(cursor.getColumnIndex("_id")));
                Log.i("iii","name:"+ cursor.getString(cursor.getColumnIndex("name")));
                Log.i("iii","age:"+ cursor.getInt(cursor.getColumnIndex("age")));
                Log.i("iii","sex:"+ cursor.getString(cursor.getColumnIndex("sex")));
            }
            cursor.close();
        }
        else {
            Log.i("iii","cursor is null");
        }

    }


    private void FileOperate() throws IOException {
        Log.i("iii","aaa");
        File extDir = this.getCacheDir();

        File file = new File(extDir,"test2.txt");
        if(!file.exists())
        {
            try {
                file.createNewFile();
                Log.i("iii","文件创建成功");

            } catch (IOException e) {
                e.printStackTrace();
                Log.i("iii","异常");
            }
        }
        else
        {
            Log.i("iii","文件已存在");
        }
        Log.i("iii",extDir + " ");
        FileOutputStream fileOutputStream = new FileOutputStream(extDir + "test2.txt");
        fileOutputStream.write("hello world".getBytes());
        fileOutputStream.close();

        FileInputStream fin = new FileInputStream(extDir + "test2.txt");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer =  new byte[1000];
        int i;
        while((i = fin.read(buffer)) != -1) {
            bos.write(buffer,0,i);

        }
        Log.i("iii", bos.toString());



    }




}
