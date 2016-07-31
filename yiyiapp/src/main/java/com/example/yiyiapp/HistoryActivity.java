package com.example.yiyiapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.yiyiapp.adapter.HistoryListViewAdapter;

/**
 * Created by Xiamin on 2016/7/31.
 */
public class HistoryActivity extends AppCompatActivity{

    private ListView listView;
    private HistoryListViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView) findViewById(R.id.history_list);
        adapter = new HistoryListViewAdapter(listView,HistoryActivity.this);
        listView.setAdapter(adapter);
    }
}
