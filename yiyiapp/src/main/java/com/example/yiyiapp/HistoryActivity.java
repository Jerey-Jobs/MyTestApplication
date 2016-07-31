package com.example.yiyiapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.yiyiapp.adapter.HistoryListViewAdapter;

/**
 * Created by Xiamin on 2016/7/31.
 */
public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    private HistoryListViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView) findViewById(R.id.history_list);
        adapter = new HistoryListViewAdapter(listView, HistoryActivity.this);
        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        /**显示回退键，默认为false*/
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home) {//当点击了返回箭头
            finish();
        }
          return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case android.R.id.home:
            {
                Log.i("iii","android.R.id.home");
                finish();
            }
        }
    }
}
