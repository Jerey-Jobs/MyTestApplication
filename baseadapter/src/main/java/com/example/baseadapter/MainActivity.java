package com.example.baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ItemBean> dataList = new ArrayList<ItemBean>();

        for(int i = 0; i < 20; i++)
        {
            dataList.add(new ItemBean(R.mipmap.ic_launcher,"titile" + i, "xiamin" + i));
        }

        ListView listView = (ListView)findViewById(R.id.Main_list);
        listView.setAdapter(new MybaseAdapter(dataList,this));
    }
}
