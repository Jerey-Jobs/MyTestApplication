package com.example.vietpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Xiamin on 2016/6/25.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> Flist;
    private List<String> titlelist;

    public MyFragmentPagerAdapter(FragmentManager fm, List<String> titlelist, List<Fragment> flist) {
        super(fm);
        this.titlelist = titlelist;
        Flist = flist;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return Flist.get(position);
    }

    @Override
    public int getCount() {
        return Flist.size();
    }
}
