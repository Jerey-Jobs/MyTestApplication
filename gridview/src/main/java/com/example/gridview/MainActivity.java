package com.example.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;
    private int[] icon = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
            , R.mipmap.ic_launcher
    };
    private String[] name = {"图标1", "图标2", "图标3", "图标4", "图标5", "图标6", "图标7", "图标8", "图标9", "图标10",
            "图标11", "图标12", "图标12", "图标12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);

        dataList = new ArrayList<Map<String, Object>>();

        simpleAdapter = new SimpleAdapter(this, this.getdata(), R.layout.item, new String[]{"icon", "text"},
                new int[]{R.id.image, R.id.text});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = "ps"+ i + "id= " + l;
                Toast.makeText(MainActivity.this,str, LENGTH_LONG).show();
            }
        });

    }

    private List<Map<String,Object>> getdata()
    {
        for(int i = 0; i < 12; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("icon",icon[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
        return dataList;
    }

}
