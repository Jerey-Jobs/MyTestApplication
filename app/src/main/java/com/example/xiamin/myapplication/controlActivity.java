package com.example.xiamin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xiamin on 2016/6/21.
 */
public class controlActivity extends AppCompatActivity {
    private ListView listView;
    private ScrollView scrollView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist;
    private Button returnButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);

        ItemInit();
        listTest();
    }

    private void ItemInit()
    {
        listView = (ListView) findViewById(R.id.listview);
        scrollView = (ScrollView) findViewById(R.id.myscrollView);
        datalist = new ArrayList<Map<String, Object>>();

        simpleAdapter = new SimpleAdapter(controlActivity.this,datalist,R.layout.useritem,
                new String[]{"pic","name","password"},new int[]{R.id.itemImage,R.id.itemName,R.id.itemPassword});
        listView.setAdapter(simpleAdapter);

        /***
         *加载一个layout动画
         */
        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.activityin));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listView.setLayoutAnimation(lac);
        listView.startLayoutAnimation();
    }


    private void listTest(){
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("name", "xiamin" + i);
            map.put("password",i+ "sss");
            datalist.add(map);
            Log.i("iii",map.toString());
        }
        simpleAdapter.notifyDataSetChanged();


    }

}
