package com.example.listlearn;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
    private ListView listView;
    private ArrayAdapter<String> my_arrAdapter;
    private SimpleAdapter my_simpleAdapter;
    private List<Map<String, Object>> datalist;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listView);
        datalist = new ArrayList<Map<String, Object>>();
        String[] src = new String[]{"xiamin1", "xiamin2", "xiamin3", "xiamin5", "xiamin6", "xiamin7"
                , "xiamin8", "xiamin9", "xiamin11", "xiamin12", "xiamin13", "xiamin14", "xiamin15",
                "xiamin16", "xiamin17", "xiamin18", "xiamin19"};
//        my_arrAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,src);
//        listView.setAdapter(my_arrAdapter);
        /*
        * data: 数据源 一个map组成的list集合
        * 每一个map的键必须包含所有在from中所制定的建
        * resource: 列表项的布局文件
        * from 记录map中的建明
        */
        my_simpleAdapter = new SimpleAdapter(this, this.getdata(), R.layout.item_layout, new String[]{"pic", "text"},
                new int[]{R.id.imageView, R.id.text});
        listView.setAdapter(my_simpleAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);

        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.layoutanim));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listView.setLayoutAnimation(lac);
        listView.startLayoutAnimation();
    }

    private List<Map<String, Object>> getdata() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text", "xiamin" + i);
            datalist.add(map);
        }
        return datalist;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String src = listView.getItemAtPosition(i) + ":id = " + l;
        Toast.makeText(this, src, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i)
        {
            case SCROLL_STATE_FLING:
                Log.i("tag","用户在手指离开之前，用力的划一下 试图惯性滑动");
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("pic",R.mipmap.ic_launcher);
                map.put("text","add");
                datalist.add(map);
                my_simpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Log.i("tag","空闲 试图停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("tag","手指没有离开屏幕，视图正在滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
/**
 * firstVisibleItem 表示在当前屏幕显示的第一个listItem在整个listView里面的位置（下标从0开始）
 * visibleItemCount表示在现时屏幕可以见到的ListItem(部分显示的ListItem也算)总数
 * totalItemCount表示ListView的ListItem总数
 * listView.getLastVisiblePosition()表示在现时屏幕最后一个ListItem
 * (最后ListItem要完全显示出来才算)在整个ListView的位置（下标从0开始）
 */
    }

}
