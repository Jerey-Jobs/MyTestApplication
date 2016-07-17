package com.vincent.tabdemo.fragment;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.vincent.tabdemo.Adapter.ImageLaughAdapter;
import com.vincent.tabdemo.Adapter.XiaohuaListAdapter;
import com.vincent.tabdemo.Base.BaseFragment;
import com.vincent.tabdemo.Bean.XiaohuaBean;
import com.vincent.tabdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     Vincent
 * 创建时间   2016/7/8 23:49
 * 描述	      ${TODO}
 * <p/>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class MoveFragment extends BaseFragment implements  SwipeRefreshLayout.OnRefreshListener{

    private ListView listView;
    private SwipeRefreshLayout mSwipeLayout;

    private List<XiaohuaBean> listBean;
    private ImageLaughAdapter adapter;
    private ImageView refershButton;

    private int pageCount = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment2;
    }


    @Override
    public void  initViews() {
        listView = (ListView) rootView.findViewById(R.id.fragment2_listview);
        listBean = new ArrayList<XiaohuaBean>();
        adapter = new ImageLaughAdapter(listBean,listView,rootView.getContext());
        listView.setAdapter(adapter);

        getPageCount();
        adapter.getJoke(pageCount++);


        mSwipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment2_swipe);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        refershButton = (ImageView) rootView.findViewById(R.id.fragment2_goto_top);

        /**此为返回顶部按钮，目前刷新功能未做好，只能先以此代替*/
        refershButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setSelection(0);

                TranslateAnimation animation = new TranslateAnimation(0,0,listView.getPivotY(),0);
                animation.setDuration(500);
                listView.startAnimation(animation);

            }
        });
    }

    @Override
    public void onRefresh() {
        adapter.clearJoke();
        adapter.getJoke(pageCount++);
        mSwipeLayout.setRefreshing(false);
    }


    private void getPageCount()
    {
        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("mypreference",getContext().MODE_PRIVATE);
        pageCount =  sharedPreferences.getInt("Fragment2pageCount",1);
        Log.i("iii","Fragment2pageCount getPageCount = " + pageCount);
        if(pageCount != 1)
        {
            Toast.makeText(getActivity(),"获取上次浏览Page成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("mypreference",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Fragment2pageCount",pageCount);
        editor.commit();
        Log.i("iii","Fragment2pageCount onDestroy= " + pageCount + "写入成功");
    }
}
