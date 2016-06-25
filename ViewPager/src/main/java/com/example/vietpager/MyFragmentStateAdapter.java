package com.example.vietpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Xiamin on 2016/6/25.
 */
public class MyFragmentStateAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> Flist;
    private List<String> titlelist;

    public MyFragmentStateAdapter(FragmentManager fm, List<String> titlelist, List<Fragment> flist) {
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
