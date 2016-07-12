package com.example.vietpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private List<View> viewList;
    private ViewPager viewPager;
    private PagerTabStrip tabStrip;
    private List<String> tablist;
    private List<Fragment> fragmentslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablist = new ArrayList<String>();
        tablist.add("第1页");
        tablist.add("第2页");
        tablist.add("第3页");
        tablist.add("第4页");

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabStrip = (PagerTabStrip) findViewById(R.id.tab);

        tabStrip.setTextColor(Color.RED);
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTabIndicatorColor(Color.BLUE);

        viewPager.setOnPageChangeListener(this);
        /**
         * 为viewPager设置动画效果
         */
        viewPager.setPageTransformer(true, new DepthPageTransformer());
       FragmentInit();
     //  Init();

    }


    private void Init() {
        viewList = new ArrayList<View>();


        View view1 = View.inflate(this, R.layout.view1, null);
        View view2 = View.inflate(this, R.layout.view2, null);
        View view3 = View.inflate(this, R.layout.view3, null);
        View view4 = View.inflate(this, R.layout.view4, null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        //创建适配器
        MypagerAdapter adapter = new MypagerAdapter(viewList, tablist);

        viewPager.setAdapter(adapter);
    }


    private void FragmentInit() {
        fragmentslist = new ArrayList<Fragment>();
        fragmentslist.add(new Fragment1());
        fragmentslist.add(new Fragment2());
        fragmentslist.add(new Fragment3());
        fragmentslist.add(new Fragment4());

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), tablist, fragmentslist);
        MyFragmentStateAdapter myFragmentStateAdapter = new MyFragmentStateAdapter(getSupportFragmentManager(), tablist, fragmentslist);

        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "dangqian" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
