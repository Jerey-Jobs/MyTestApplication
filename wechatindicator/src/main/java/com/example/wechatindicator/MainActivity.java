package com.example.wechatindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.view.myViewPagerIndicator.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicater;

    private List<String> mTitles = Arrays.asList("短信","收藏","推荐","disi");
    private List<SimpleFragment> mcontents = new ArrayList<SimpleFragment>();
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();



    }

    private void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mIndicater = (ViewPagerIndicator) findViewById(R.id.id_indicator);

        for(String title:mTitles)
        {
            SimpleFragment fragment = SimpleFragment.newInstance(title);
            mcontents.add(fragment);
        }

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mcontents.get(position);
            }

            @Override
            public int getCount() {
                return mcontents.size();
            }
        };
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicater.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
