package net.oschina.app.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

import net.oschina.app.AppContext;
import net.oschina.app.R;
import net.oschina.app.adapter.TweetAdapter;
import net.oschina.app.api.OperationResponseHandler;
import net.oschina.app.api.remote.OSChinaApi;
import net.oschina.app.base.BaseListFragment;
import net.oschina.app.bean.Constants;
import net.oschina.app.bean.Result;
import net.oschina.app.bean.ResultBean;
import net.oschina.app.bean.Tweet;
import net.oschina.app.bean.TweetsList;
import net.oschina.app.interf.OnTabReselectListener;
import net.oschina.app.ui.empty.EmptyLayout;
import net.oschina.app.improve.activities.TweetDetailActivity;
import net.oschina.app.util.DialogHelp;
import net.oschina.app.util.HTMLUtil;
import net.oschina.app.util.TDevice;
import net.oschina.app.util.UIHelper;
import net.oschina.app.util.XmlUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import cz.msebera.android.httpclient.Header;

/**
 * @author kymjs (http://www.kymjs.com)
 */
public class TweetsFragment extends BaseListFragment<Tweet> implements
        OnItemLongClickListener, OnTabReselectListener {

    protected static final String TAG = TweetsFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "tweetslist_";

    private class DeleteTweetResponseHandler extends OperationResponseHandler {

        DeleteTweetResponseHandler(Object... args) {
            super(args);
        }

        @Override
        public void onSuccess(int code, ByteArrayInputStream is, Object[] args)
                throws Exception {
            try {
                Result res = XmlUtils.toBean(ResultBean.class, is).getResult();
                if (res != null && res.OK()) {
                    AppContext.showToastShort(R.string.delete_success);
                    Tweet tweet = (Tweet) args[0];
                    mAdapter.removeItem(tweet);
                    mAdapter.notifyDataSetChanged();
                } else {
                    onFailure(code, res.getErrorMessage(), args);
                }
            } catch (Exception e) {
                e.printStackTrace();
                onFailure(code, e.getMessage(), args);
            }
        }

        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            super.onSuccess(arg0, arg1, arg2);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                error) {
            AppContext.showToastShort(R.string.delete_faile);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tweets;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mCatalog > 0) {
            IntentFilter filter = new IntentFilter(
                    Constants.INTENT_ACTION_USER_CHANGE);
            filter.addAction(Constants.INTENT_ACTION_LOGOUT);
            getActivity().registerReceiver(mReceiver, filter);
        }
    }

    @Override
    public void onDestroy() {
        if (mCatalog > 0) {
            getActivity().unregisterReceiver(mReceiver);
        }
        super.onDestroy();
    }

    @Override
    protected TweetAdapter getListAdapter() {
        return new TweetAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String str = bundle.getString("topic");
            if (str != null) {
                return str;
            }
        }
        return CACHE_KEY_PREFIX + mCatalog;
    }

    public String getTopic() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String str = bundle.getString("topic");
            if (str != null) {
                return str;
            }
        }
        return "";
    }

    @Override
    protected TweetsList parseList(InputStream is) throws Exception {
        TweetsList list = XmlUtils.toBean(TweetsList.class, is);
        return list;
    }

    @Override
    protected TweetsList readList(Serializable seri) {
        return ((TweetsList) seri);
    }

    @Override
    protected void sendRequestData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String str = bundle.getString("topic");
            if (str != null) {
                OSChinaApi.getTweetTopicList(mCurrentPage, str, mHandler);
                return;
            }
        }
        OSChinaApi.getTweetList(mCatalog, mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Tweet tweet = mAdapter.getItem(position);
        if (tweet != null) {
//            UIHelper.showTweetDetail(view.getContext(), null, tweet.getId());
            TweetDetailActivity.show(getContext(), tweet);
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setupContent();
        }
    };

    private void setupContent() {
        if (AppContext.getInstance().isLogin()) {
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            requestData(true);
        } else {
            mCatalog = TweetsList.CATALOG_ME;
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            mErrorLayout.setErrorMessage(getString(R.string.unlogin_tip));
        }
    }

    @Override
    protected void requestData(boolean refresh) {
        if (mCatalog > 0) {
            if (AppContext.getInstance().isLogin()) {
                mCatalog = AppContext.getInstance().getLoginUid();
                super.requestData(refresh);
            } else {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                mErrorLayout.setErrorMessage(getString(R.string.unlogin_tip));
            }
        } else {
            super.requestData(refresh);
        }
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mListView.setOnItemLongClickListener(this);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCatalog > 0) {
                    if (AppContext.getInstance().isLogin()) {
                        mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                        requestData(true);
                    } else {
                        UIHelper.showLoginActivity(getActivity());
                    }
                } else {
                    mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                    requestData(true);
                }
            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        Tweet tweet = mAdapter.getItem(position);
        if (tweet != null) {
            handleLongClick(tweet);
            return true;
        }
        return false;
    }

    private void handleLongClick(final Tweet tweet) {
        String[] items;
        if (AppContext.getInstance().getLoginUid() == tweet.getAuthorid()) {
            items = new String[]{getString(R.string.copy),
                    getString(R.string.delete)};
        } else {
            items = new String[]{getString(R.string.copy)};
        }

        DialogHelp.getSelectDialog(getActivity(), items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case 0:
                        TDevice.copyTextToBoard(HTMLUtil.delHTMLTag(tweet.getBody()));
                        break;
                    case 1:
                        handleDeleteTweet(tweet);
                        break;
                }
            }
        }).show();
    }

    private void handleDeleteTweet(final Tweet tweet) {
        DialogHelp.getConfirmDialog(getActivity(), "是否删除该动弹?", new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                OSChinaApi.deleteTweet(tweet.getAuthorid(), tweet
                        .getId(), new DeleteTweetResponseHandler(tweet));
            }
        }).show();
    }

    @Override
    public void onTabReselect() {
        onRefresh();
    }

    @Override
    protected long getAutoRefreshTime() {
        // 最新动弹3分钟刷新一次
        if (mCatalog == TweetsList.CATALOG_LATEST) {
            return 3 * 60;
        }
        return super.getAutoRefreshTime();
    }
}