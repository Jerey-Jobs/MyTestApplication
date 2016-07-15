package com.vincent.tabdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincent.tabdemo.R;

/**
 * 创建者     Vincent
 * 创建时间   2016/7/8 23:49
 * 描述	      ${TODO}
 * <p/>
 * 更新者     xiamin
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class NewsFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * 当从tabA切换到tabB,然后再次切换到tabA时，发现对应的Fragment会重新加载，
         * 重新创建布局。这样会引起一个问题：如果布局里有较多的View，
         * 则每次tab项切换时重新创建布局会很浪费时间，并且重新创建后，也保存不了之前的状态。
         * 我们可以通过复写fragmenttabhost实现，将销毁和添加的方法以hide和show代替
         *
         * 我们先判断当前view为空 则去加载布局文件 不然直接返回
         * 且view是否已经有parnet 若有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
         */
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment1, container, false);
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }
}
