package com.example.vietpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Xiamin on 2016/6/25.
 */
public class MypagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<String> tables;

    public MypagerAdapter(List<View> list, List<String> titlelist) {
        this.viewList = list;
        this.tables = titlelist;
    }

    //返回页面的数量
    @Override
    public int getCount() {
        return viewList.size();
    }

    //当前view是否来自与对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //实列化一个页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //销毁一个
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tables.get(position);
    }


}
