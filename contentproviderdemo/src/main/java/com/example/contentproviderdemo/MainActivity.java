package com.example.contentproviderdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI; //查询联系人
        //第一个是uri
        Cursor cursor = contentResolver.query(uri,new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME},null,null,null);
        Log.i("iii",ContactsContract.Contacts._ID  + ContactsContract.Contacts.DISPLAY_NAME);
        if(cursor != null)
        {
            Log.i("iii","cursor is not null");
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String str = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.i("iii","id:" + id + "  name:" + str);

            }
            cursor.close();
        }
        else {
            Log.i("iii","cursor is null");
        }

    }
}
