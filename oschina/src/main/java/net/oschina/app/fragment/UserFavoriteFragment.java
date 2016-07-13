package net.oschina.app.fragment;

import android.view.View;
import android.widget.AdapterView;

import net.oschina.app.AppContext;
import net.oschina.app.adapter.UserFavoriteAdapter;
import net.oschina.app.api.remote.OSChinaApi;
import net.oschina.app.base.BaseListFragment;
import net.oschina.app.bean.Favorite;
import net.oschina.app.bean.FavoriteList;
import net.oschina.app.util.UIHelper;
import net.oschina.app.util.XmlUtils;

import java.io.InputStream;
import java.io.Serializable;

public class UserFavoriteFragment extends BaseListFragment<Favorite> {

    protected static final String TAG = UserFavoriteFragment.class
            .getSimpleName();
    private static final String CACHE_KEY_PREFIX = "userfavorite_";

    @Override
    protected UserFavoriteAdapter getListAdapter() {
        return new UserFavoriteAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX + mCatalog;
    }

    @Override
    protected FavoriteList parseList(InputStream is) throws Exception {
        return XmlUtils.toBean(FavoriteList.class, is);
    }

    @Override
    protected FavoriteList readList(Serializable seri) {
        return ((FavoriteList) seri);
    }

    @Override
    protected void sendRequestData() {
        OSChinaApi.getFavoriteList(AppContext.getInstance().getLoginUid(),
                mCatalog, mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Favorite favorite = mAdapter.getItem(position);
        if (favorite != null) {
            switch (favorite.getType()) {

                case Favorite.CATALOG_BLOGS:
                    UIHelper.showUrlRedirect(getActivity(), favorite.getUrl());
                    break;
                case Favorite.CATALOG_CODE:
                    UIHelper.showUrlRedirect(getActivity(), favorite.getUrl());
                    break;
                case Favorite.CATALOG_NEWS:
                    UIHelper.showUrlRedirect(getActivity(), favorite.getUrl());
                    break;
                case Favorite.CATALOG_SOFTWARE:
                    UIHelper.showDetail(getActivity(), 1, favorite.getId(), favorite.getUrl());
                    //UIHelper.showUrlRedirect(getActivity(), favorite.getUrl());
                    break;
                case Favorite.CATALOG_TOPIC:
                    UIHelper.showUrlRedirect(getActivity(), favorite.getUrl());
                    break;

            }
        }

    }
}
