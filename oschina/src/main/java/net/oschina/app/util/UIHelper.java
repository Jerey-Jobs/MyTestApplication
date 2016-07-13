package net.oschina.app.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ZoomButtonsController;

import com.dtr.zxing.activity.CaptureActivity;

import net.oschina.app.AppConfig;
import net.oschina.app.AppContext;
import net.oschina.app.base.BaseListFragment;
import net.oschina.app.bean.Active;
import net.oschina.app.bean.Banner;
import net.oschina.app.bean.Comment;
import net.oschina.app.bean.Constants;
import net.oschina.app.bean.News;
import net.oschina.app.bean.Notice;
import net.oschina.app.bean.ShakeObject;
import net.oschina.app.bean.SimpleBackPage;
import net.oschina.app.bean.Tweet;
import net.oschina.app.fragment.BrowserFragment;
import net.oschina.app.fragment.CommentFrament;
import net.oschina.app.fragment.FriendsFragment;
import net.oschina.app.fragment.MessageDetailFragment;
import net.oschina.app.fragment.QuestionTagFragment;
import net.oschina.app.fragment.SoftWareTweetsFrament;
import net.oschina.app.improve.activities.TweetDetailActivity;
import net.oschina.app.improve.detail.activities.BlogDetailActivity;
import net.oschina.app.improve.detail.activities.EventDetailActivity;
import net.oschina.app.improve.detail.activities.NewsDetailActivity;
import net.oschina.app.improve.detail.activities.QuestionDetailActivity;
import net.oschina.app.improve.detail.activities.SoftwareDetailActivity;
import net.oschina.app.improve.detail.activities.TranslateDetailActivity;
import net.oschina.app.improve.fragments.blog.UserBlogFragment;
import net.oschina.app.improve.fragments.tweet.SoftWareForTweetsFragment;
import net.oschina.app.interf.ICallbackResult;
import net.oschina.app.interf.OnWebViewImageListener;
import net.oschina.app.service.DownloadService;
import net.oschina.app.service.DownloadService.DownloadBinder;
import net.oschina.app.service.NoticeService;
import net.oschina.app.team.adapter.TeamMemberAdapter;
import net.oschina.app.team.bean.Team;
import net.oschina.app.team.bean.TeamActive;
import net.oschina.app.team.bean.TeamDiscuss;
import net.oschina.app.team.bean.TeamIssue;
import net.oschina.app.team.bean.TeamIssueCatalog;
import net.oschina.app.team.bean.TeamMember;
import net.oschina.app.team.bean.TeamProject;
import net.oschina.app.team.fragment.TeamActiveFragment;
import net.oschina.app.team.ui.TeamMainActivity;
import net.oschina.app.team.ui.TeamNewIssueActivity;
import net.oschina.app.ui.DetailActivity;
import net.oschina.app.ui.LoginActivity;
import net.oschina.app.ui.OSCPhotosActivity;
import net.oschina.app.ui.SimpleBackActivity;
import net.oschina.app.ui.TweetPubActivity;
import net.oschina.app.viewpagerfragment.FriendsViewPagerFragment;
import net.oschina.app.widget.AvatarView;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.utils.DensityUtils;

import java.net.URLDecoder;

/**
 * 界面帮助类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年10月10日 下午3:33:36
 */
public class UIHelper {

    /**
     * 全局web样式
     */
    // 链接样式文件，代码块高亮的处理
    public final static String linkCss = "<script type=\"text/javascript\" " +
            "src=\"file:///android_asset/shCore.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/brush.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/client.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/detail_page" +
            ".js\"></script>"
            + "<script type=\"text/javascript\">SyntaxHighlighter.all();</script>"
            + "<script type=\"text/javascript\">function showImagePreview(var url){window" +
            ".location.url= url;}</script>"
            + "<link rel=\"stylesheet\" type=\"text/css\" " +
            "href=\"file:///android_asset/shThemeDefault.css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shCore" +
            ".css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/css/common" +
            ".css\">";
    public final static String WEB_STYLE = linkCss;

    public static final String WEB_LOAD_IMAGES = "<script type=\"text/javascript\"> var " +
            "allImgUrls = getAllImgSrc(document.body.innerHTML);</script>";

    private static final String SHOWIMAGE = "ima-api:action=showImage&data=";

    /**
     * 显示登录界面
     *
     * @param context
     */
    public static void showLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示Team界面
     *
     * @param context
     */
    public static void showTeamMainActivity(Context context) {
        Intent intent = new Intent(context, TeamMainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 获取一个环形进度条等待窗口
     */
    public static ProgressDialog getprogress(Activity aty, String msg) {
        // 实例化一个ProgressBarDialog
        ProgressDialog progressDialog = new ProgressDialog(aty);
        progressDialog.setMessage(msg);
        progressDialog.getWindow().setLayout(
                DensityUtils.getScreenW(aty),
                DensityUtils.getScreenH(aty));
        progressDialog.setCancelable(true);
        // 设置ProgressBarDialog的显示样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 显示新闻详情
     *
     * @param context
     * @param newsId
     */
    public static void showNewsDetail(Context context, long newsId,
                                      int commentCount) {
        /*
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", newsId);
        intent.putExtra("comment_count", commentCount);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_NEWS);
        context.startActivity(intent);
        */
        NewsDetailActivity.show(context, newsId);
    }


    /**
     * 显示博客详情
     *
     * @param context
     * @param blogId
     */
    public static void showBlogDetail(Context context, long blogId) {
        BlogDetailActivity.show(context, blogId);
    }

    /**
     * 显示帖子详情
     *
     * @param context
     * @param postId
     */
    public static void showPostDetail(Context context, long postId, int count) {
        /*
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", postId);
        intent.putExtra("comment_count", count);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_POST);
        context.startActivity(intent);
        */
        QuestionDetailActivity.show(context, postId);
    }

    /**
     * 显示活动详情
     *
     * @param context
     * @param eventId
     */
    public static void showEventDetail(Context context, long eventId) {
        /*
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", eventId);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_EVENT);
        context.startActivity(intent);
        */
        EventDetailActivity.show(context, eventId);
    }

    /**
     * 显示相关Tag帖子列表
     *
     * @param context
     * @param tag
     */
    public static void showPostListByTag(Context context, String tag) {
        Bundle args = new Bundle();
        args.putString(QuestionTagFragment.BUNDLE_KEY_TAG, tag);
        showSimpleBack(context, SimpleBackPage.QUESTION_TAG, args);
    }

    /**
     * 显示动弹详情
     *
     * @param context context
     * @param tweetid 动弹的id
     */
    public static void showTweetDetail(Context context, Tweet tweet, long tweetid) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("tweet_id", (int) tweetid);
        bundle.putInt(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_TWEET);
        if (tweet != null) {
            bundle.putParcelable("tweet", tweet);
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 显示软件详情
     *
     * @param context
     * @param ident
     */
    public static void showSoftwareDetail(Context context, String ident) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("ident", ident);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_SOFTWARE);
        context.startActivity(intent);
    }

    public static void showSoftwareDetailById(Context context, int id) {
        /*
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("ident", "");
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_SOFTWARE);
        context.startActivity(intent);
        */
        SoftwareDetailActivity.show(context, id);
    }

    /**
     * 新闻超链接点击跳转
     *
     * @param context context
     */
    public static void showNewsRedirect(Context context, News news) {
        String url = news.getUrl();
        // 如果是活动则直接跳转活动详情页面
        String eventUrl = news.getNewType().getEventUrl();
        if (!StringUtils.isEmpty(eventUrl)) {
            showEventDetail(context,
                    StringUtils.toInt(news.getNewType().getAttachment()));
            return;
        }
        // url为空-旧方法
        if (StringUtils.isEmpty(url)) {
            int newsId = news.getId();
            int newsType = news.getNewType().getType();
            String objId = news.getNewType().getAttachment();
            switch (newsType) {
                case News.NEWSTYPE_NEWS:
                    showNewsDetail(context, newsId, news.getCommentCount());
                    break;
                case News.NEWSTYPE_SOFTWARE:
                    showSoftwareDetail(context, objId);
                    break;
                case News.NEWSTYPE_POST:
                    showPostDetail(context, StringUtils.toInt(objId),
                            news.getCommentCount());
                    break;
                case News.NEWSTYPE_BLOG:
                    showBlogDetail(context, Long.valueOf(objId));
                    break;
                default:
                    break;
            }
        } else {
            showUrlRedirect(context, url);
        }
    }

    /**
     * show detail  method
     *
     * @param context context
     * @param type    type
     * @param id      id
     */
    public static void showDetail(Context context, int type, long id, String href) {
        switch (type) {
            case 0:
                //新闻链接
                showUrlRedirect(context, id, href);
                break;
            case 1:
                //软件推荐
                SoftwareDetailActivity.show(context, id);
                //UIHelper.showSoftwareDetailById(context, (int) id);
                break;
            case 2:
                //问答
                QuestionDetailActivity.show(context, id);
                break;
            case 3:
                //博客
                BlogDetailActivity.show(context, id);
                break;
            case 4:
                //4.翻译
                TranslateDetailActivity.show(context, id);
                break;
            case 5:
                //活动
                EventDetailActivity.show(context, id);
                break;
            default:
                //6.资讯
                NewsDetailActivity.show(context, id);
                break;
        }
    }

    public static void showBannerDetail(Context context, Banner banner) {
        long newsId = banner.getId();
        switch (banner.getType()) {
            case Banner.BANNER_TYPE_URL:
                showNewsDetail(context, Integer.parseInt(String.valueOf(newsId)), 0);
                break;
            case Banner.BANNER_TYPE_SOFTWARE:
                showSoftwareDetailById(context, Integer.parseInt(String.valueOf(newsId)));
                break;
            case Banner.BANNER_TYPE_POST:
                showPostDetail(context, StringUtils.toInt(String.valueOf(newsId)),
                        0);
                break;
            case Banner.BANNER_TYPE_BLOG:
                showBlogDetail(context, StringUtils.toLong(String.valueOf(newsId)));
                break;
            case Banner.BANNER_TYPE_EVENT:
                EventDetailActivity.show(context, newsId);
                break;
            case Banner.BANNER_TYPE_NEWS:
            default:
                showUrlRedirect(context, banner.getHref());
                break;
        }
    }

    /**
     * 动态点击跳转到相关新闻、帖子等
     *
     * @param context context
     * @param active  动态实体类
     *                0其他 1新闻 2帖子 3动弹 4博客
     */
    public static void showActiveRedirect(Context context, Active active) {
        String url = active.getUrl();
        // url为空-旧方法
        if (StringUtils.isEmpty(url)) {
            int id = active.getObjectId();
            int catalog = active.getCatalog();
            switch (catalog) {
                case Active.CATALOG_OTHER:
                    // 其他-无跳转
                    break;
                case Active.CATALOG_NEWS:
                    showNewsDetail(context, id, active.getCommentCount());
                    break;
                case Active.CATALOG_POST:
                    showPostDetail(context, id, active.getCommentCount());
                    break;
                case Active.CATALOG_TWEET:
                    Tweet tweet = new Tweet();
                    tweet.setId(id);
                    TweetDetailActivity.show(context, tweet);
//                    showTweetDetail(context, null, id);
                    break;
                case Active.CATALOG_BLOG:
                    showBlogDetail(context, id);
                    break;
                default:
                    break;
            }
        } else {
            showUrlRedirect(context, url);
        }
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    public static void initWebView(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setDefaultFontSize(14);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        int sysVersion = Build.VERSION.SDK_INT;
        if (sysVersion >= 11) {
            settings.setDisplayZoomControls(false);
        } else {
            ZoomButtonsController zbc = new ZoomButtonsController(webView);
            zbc.getZoomControls().setVisibility(View.GONE);
        }
        //webView.setWebViewClient(UIHelper.getWebViewClient());
    }

    /**
     * 添加网页的点击图片展示支持
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @JavascriptInterface
    public static void addWebImageShow(final Context cxt, WebView wv) {
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new OnWebViewImageListener() {
            @Override
            @JavascriptInterface
            public void showImagePreview(String bigImageUrl) {
                if (bigImageUrl != null && !StringUtils.isEmpty(bigImageUrl)) {
                    OSCPhotosActivity.showImagePreview(cxt, bigImageUrl);
                }
            }
        }, "mWebViewImageListener");
    }

    public static String setHtmlCotentSupportImagePreview(String body) {
        // 读取用户设置：是否加载文章图片--默认有wifi下始终加载图片
        if (AppContext.get(AppConfig.KEY_LOAD_IMAGE, true)
                || TDevice.isWifiOpen()) {
            // 过滤掉 img标签的width,height属性
            body = body.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
            body = body.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");
            // 添加点击图片放大支持
            // 添加点击图片放大支持
            body = body.replaceAll("(<img[^>]+src=\")(\\S+)\"",
                    "$1$2\" onClick=\"showImagePreview('$2')\"");
        } else {
            // 过滤掉 img标签
            body = body.replaceAll("<\\s*img\\s+([^>]*)\\s*>", "");
        }

        // 过滤table的内部属性
        body = body.replaceAll("(<table[^>]*?)\\s+border\\s*=\\s*\\S+", "$1");
        body = body.replaceAll("(<table[^>]*?)\\s+cellspacing\\s*=\\s*\\S+", "$1");
        body = body.replaceAll("(<table[^>]*?)\\s+cellpadding\\s*=\\s*\\S+", "$1");

        return body;
    }

    /**
     * 摇一摇点击跳转
     *
     * @param obj
     */
    public static void showUrlShake(Context context, ShakeObject obj) {
        if (StringUtils.isEmpty(obj.getUrl())) {
            if (ShakeObject.RANDOMTYPE_NEWS.equals(obj.getRandomtype())) {
                UIHelper.showNewsDetail(context,
                        StringUtils.toInt(obj.getId()),
                        StringUtils.toInt(obj.getCommentCount()));
            }
        } else {
            if (!StringUtils.isEmpty(obj.getUrl())) {
                UIHelper.showUrlRedirect(context, obj.getUrl());
            }
        }
    }

    private static void showUrlRedirect(Context context, long id, String url) {
        if (url == null) {
            if (id != 0) {
                NewsDetailActivity.show(context, id);
            }
            return;
        }

        if (url.contains("city.oschina.net/")) {
            if (id == 0)
                id = StringUtils.toInt(url.substring(url.lastIndexOf('/') + 1));
            UIHelper.showEventDetail(context, id);
            return;
        }

        if (url.startsWith(SHOWIMAGE)) {
            String realUrl = url.substring(SHOWIMAGE.length());
            try {
                JSONObject json = new JSONObject(realUrl);
                int idx = json.optInt("index");
                String[] urls = json.getString("urls").split(",");
                OSCPhotosActivity.showImagePreview(context, urls[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }
        URLsUtils urls = URLsUtils.parseURL(url);
        if (urls != null) {
            showLinkRedirect(context, urls.getObjType(), urls.getObjId() != 0 ? urls.getObjId() : id, urls.getObjKey());
        } else {
            openBrowser(context, url);
        }
    }


    /**
     * url跳转
     *
     * @param context
     * @param url
     */
    public static void showUrlRedirect(Context context, String url) {
        showUrlRedirect(context, 0, url);
    }

    public static void showLinkRedirect(Context context, int objType,
                                        long objId, String objKey) {
        switch (objType) {
            case URLsUtils.URL_OBJ_TYPE_NEWS:
                showNewsDetail(context, objId, -1);
                break;
            case URLsUtils.URL_OBJ_TYPE_QUESTION:
                showPostDetail(context, objId, 0);
                break;
            case URLsUtils.URL_OBJ_TYPE_QUESTION_TAG:
                showPostListByTag(context, objKey);
                break;
            case URLsUtils.URL_OBJ_TYPE_SOFTWARE:
                showSoftwareDetail(context, objKey);
                break;
            case URLsUtils.URL_OBJ_TYPE_ZONE:
                showUserCenter(context, objId, objKey);
                break;
            case URLsUtils.URL_OBJ_TYPE_TWEET:
                showTweetDetail(context, null, objId);
                break;
            case URLsUtils.URL_OBJ_TYPE_BLOG:
                showBlogDetail(context, objId);
                break;
            case URLsUtils.URL_OBJ_TYPE_OTHER:
                openBrowser(context, objKey);
                break;
            case URLsUtils.URL_OBJ_TYPE_TEAM:
                openSysBrowser(context, objKey);
                break;
            case URLsUtils.URL_OBJ_TYPE_GIT:
                openSysBrowser(context, objKey);
                break;
        }
    }

    /**
     * 打开内置浏览器
     *
     * @param context
     * @param url
     */
    public static void openBrowser(Context context, String url) {

        if (StringUtils.isImgUrl(url)) {
            OSCPhotosActivity.showImagePreview(context, url);
            return;
        }

        if (url.startsWith("http://www.oschina.net/tweet-topic/")) {
            Bundle bundle = new Bundle();
            int i = url.lastIndexOf("/");
            if (i != -1) {
                bundle.putString("topic",
                        URLDecoder.decode(url.substring(i + 1)));
            }
            UIHelper.showSimpleBack(context, SimpleBackPage.TWEET_TOPIC_LIST,
                    bundle);
            return;
        }
        try {
            // 启用外部浏览器
            // Uri uri = Uri.parse(url);
            // Intent it = new Intent(Intent.ACTION_VIEW, uri);
            // context.startActivity(it);
            Bundle bundle = new Bundle();
            bundle.putString(BrowserFragment.BROWSER_KEY, url);
            showSimpleBack(context, SimpleBackPage.BROWSER, bundle);
        } catch (Exception e) {
            e.printStackTrace();
            AppContext.showToastShort("无法浏览此网页");
        }
    }

    /**
     * 打开系统中的浏览器
     *
     * @param context
     * @param url
     */
    public static void openSysBrowser(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
            AppContext.showToastShort("无法浏览此网页");
        }
    }

    public static void showSimpleBackForResult(Fragment fragment,
                                               int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(fragment.getActivity(),
                SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBackForResult(Activity context,
                                               int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        context.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBackForResult(Activity context,
                                               int requestCode, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivityForResult(intent, requestCode);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page,
                                      Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showTweetActivity(Context context, int actionType, Bundle bundle) {
        Intent intent = new Intent(context, TweetPubActivity.class);
        intent.putExtra(TweetPubActivity.ACTION_TYPE, actionType);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void showComment(Context context, int id, int catalog) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(CommentFrament.BUNDLE_KEY_ID, id);
        intent.putExtra(CommentFrament.BUNDLE_KEY_CATALOG, catalog);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_COMMENT);
        context.startActivity(intent);
    }

    public static void showSoftWareTweets(Context context, int id) {
        Bundle args = new Bundle();
        args.putInt(SoftWareTweetsFrament.BUNDLE_KEY_ID, id);
        showSimpleBack(context, SimpleBackPage.SOFTWARE_TWEETS, args);
    }

    /**
     * show new software tweet dlist
     *
     * @param context context
     * @param id      id
     */
    public static void showSoftwareTweets(Context context, int id) {
        Bundle args = new Bundle();
        args.putInt(SoftWareForTweetsFragment.BUNDLE_KEY_ID, id);
        showSimpleBack(context, SimpleBackPage.SOFTWARE_TWEETS_NEW, args);
    }

    public static void showBlogComment(Context context, int id, int ownerId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(CommentFrament.BUNDLE_KEY_ID, id);
        intent.putExtra(CommentFrament.BUNDLE_KEY_OWNER_ID, ownerId);
        intent.putExtra(CommentFrament.BUNDLE_KEY_BLOG, true);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_COMMENT);
        context.startActivity(intent);
    }

    public static SpannableString parseActiveAction(int objecttype,
                                                    int objectcatalog, String objecttitle) {
        String title = "";
        int start = 0;
        int end = 0;
        if (objecttype == 32 && objectcatalog == 0) {
            title = "加入了开源中国";
        } else if (objecttype == 1 && objectcatalog == 0) {
            title = "添加了开源项目 " + objecttitle;
        } else if (objecttype == 2 && objectcatalog == 1) {
            title = "在讨论区提问：" + objecttitle;
        } else if (objecttype == 2 && objectcatalog == 2) {
            title = "发表了新话题：" + objecttitle;
        } else if (objecttype == 3 && objectcatalog == 0) {
            title = "发表了博客 " + objecttitle;
        } else if (objecttype == 4 && objectcatalog == 0) {
            title = "发表一篇新闻 " + objecttitle;
        } else if (objecttype == 5 && objectcatalog == 0) {
            title = "分享了一段代码 " + objecttitle;
        } else if (objecttype == 6 && objectcatalog == 0) {
            title = "发布了一个职位：" + objecttitle;
        } else if (objecttype == 16 && objectcatalog == 0) {
            title = "在新闻 " + objecttitle + " 发表评论";
        } else if (objecttype == 17 && objectcatalog == 1) {
            title = "回答了问题：" + objecttitle;
        } else if (objecttype == 17 && objectcatalog == 2) {
            title = "回复了话题：" + objecttitle;
        } else if (objecttype == 17 && objectcatalog == 3) {
            title = "在 " + objecttitle + " 对回帖发表评论";
        } else if (objecttype == 18 && objectcatalog == 0) {
            title = "在博客 " + objecttitle + " 发表评论";
        } else if (objecttype == 19 && objectcatalog == 0) {
            title = "在代码 " + objecttitle + " 发表评论";
        } else if (objecttype == 20 && objectcatalog == 0) {
            title = "在职位 " + objecttitle + " 发表评论";
        } else if (objecttype == 101 && objectcatalog == 0) {
            title = "回复了动态：" + objecttitle;
        } else if (objecttype == 100) {
            title = "更新了动态";
        }
        SpannableString sp = new SpannableString(title);
        // 设置标题字体大小、高亮
        if (!StringUtils.isEmpty(objecttitle)) {
            start = title.indexOf(objecttitle);
            if (objecttitle.length() > 0 && start > 0) {
                end = start + objecttitle.length();
                sp.setSpan(new AbsoluteSizeSpan(14, true), start, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                sp.setSpan(
                        new ForegroundColorSpan(Color.parseColor("#0e5986")),
                        start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return sp;
    }

    /**
     * 组合动态的回复文本
     *
     * @param name
     * @param body
     * @return
     */
    public static SpannableStringBuilder parseActiveReply(String name,
                                                          String body) {
        Spanned span = Html.fromHtml(body.trim());
        SpannableStringBuilder sp = new SpannableStringBuilder(name + "：");
        sp.append(span);
        // 设置用户名字体加粗、高亮
        // sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
        // name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#008000")), 0,
                name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }

    /**
     * 发送App异常崩溃报告
     *
     * @param context
     */
    public static void sendAppCrashReport(final Context context) {

        DialogHelp.getConfirmDialog(context, "程序发生异常", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 退出
                //   System.exit(-1);
            }
        }).show();
    }

    /**
     * 发送通知广播
     *
     * @param context
     * @param notice
     */
    public static void sendBroadCast(Context context, Notice notice) {
        if (!((AppContext) context.getApplicationContext()).isLogin()
                || notice == null)
            return;
        Intent intent = new Intent(Constants.INTENT_ACTION_NOTICE);
        Bundle bundle = new Bundle();
        bundle.putSerializable("notice_bean", notice);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

    /**
     * 发送通知广播
     *
     * @param context
     */
    public static void sendBroadcastForNotice(Context context) {
        Intent intent = new Intent(NoticeService.INTENT_ACTION_BROADCAST);
        context.sendBroadcast(intent);
    }

    /**
     * 显示用户中心页面
     *
     * @param context
     * @param hisuid
     * @param hisuid
     * @param hisname
     */
    public static void showUserCenter(Context context, long hisuid,
                                      String hisname) {
        if (hisuid == 0 && hisname.equalsIgnoreCase("匿名")) {
            AppContext.showToast("提醒你，该用户为非会员");
            return;
        }
        Bundle args = new Bundle();
        args.putInt("his_id", (int) hisuid);
        args.putString("his_name", hisname);
        showSimpleBack(context, SimpleBackPage.USER_CENTER, args);
    }

    /**
     * 显示用户的博客列表
     *
     * @param context
     * @param uid
     */
    public static void showUserBlog(Context context, int uid) {
        Bundle args = new Bundle();
        args.putInt(UserBlogFragment.USER_ID, uid);
        showSimpleBack(context, SimpleBackPage.USER_BLOG, args);
    }

    /**
     * 显示用户头像大图
     *
     * @param context
     * @param avatarUrl
     */
    public static void showUserAvatar(Context context, String avatarUrl) {
        if (StringUtils.isEmpty(avatarUrl)) {
            return;
        }
        String url = AvatarView.getLargeAvatar(avatarUrl);
        OSCPhotosActivity.showImagePreview(context, url);
    }

    /**
     * 显示登陆用户的个人中心页面
     *
     * @param context
     */
    public static void showMyInformation(Context context) {
        showSimpleBack(context, SimpleBackPage.MY_INFORMATION);
    }

    /**
     * 显示我的所有动态
     *
     * @param context
     */
    public static void showMyActive(Context context) {
        showSimpleBack(context, SimpleBackPage.MY_ACTIVE);
    }

    /**
     * 显示扫一扫界面
     *
     * @param context
     */
    public static void showScanActivity(Context context) {
        Intent intent = new Intent(context, CaptureActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示用户的消息中心
     *
     * @param context
     */
    public static void showMyMes(Context context) {
        showSimpleBack(context, SimpleBackPage.MY_MES);
    }

    /**
     * 显示用户收藏界面
     *
     * @param context
     */
    public static void showUserFavorite(Context context, int uid) {

        Bundle args = new Bundle();
        args.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, uid);
        showSimpleBack(context, SimpleBackPage.USER_FAVORITE);
    }

    /*
     * 显示用户的关注/粉丝列表
     * 
     * @param context
     */
    public static void showFriends(Context context, int uid, int tabIdx) {
        Bundle args = new Bundle();
        args.putInt(FriendsViewPagerFragment.BUNDLE_KEY_TABIDX, tabIdx);
        args.putInt(FriendsFragment.BUNDLE_KEY_UID, uid);
        showSimpleBack(context, SimpleBackPage.MY_FRIENDS, args);
    }

    /**
     * 显示留言对话页面
     *
     * @param context
     * @param friendid
     * @param friendid
     */
    public static void showMessageDetail(Context context, int friendid,
                                         String friendname) {
        Bundle args = new Bundle();
        args.putInt(MessageDetailFragment.BUNDLE_KEY_FID, friendid);
        args.putString(MessageDetailFragment.BUNDLE_KEY_FNAME, friendname);
        showSimpleBack(context, SimpleBackPage.MESSAGE_DETAIL, args);
    }

    /**
     * 显示设置界面
     *
     * @param context
     */
    public static void showSetting(Context context) {
        showSimpleBack(context, SimpleBackPage.SETTING);
    }

    /**
     * 显示通知设置界面
     *
     * @param context
     */
    public static void showSettingNotification(Context context) {
        showSimpleBack(context, SimpleBackPage.SETTING_NOTIFICATION);
    }

    /**
     * 显示关于界面
     *
     * @param context
     */
    public static void showAboutOSC(Context context) {
        showSimpleBack(context, SimpleBackPage.ABOUT_OSC);
    }

    /**
     * 清除app缓存
     *
     * @param activity
     */
    public static void clearAppCache(Activity activity) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    AppContext.showToastShort("缓存清除成功");
                } else {
                    AppContext.showToastShort("缓存清除失败");
                }
            }
        };
        new Thread() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    AppContext.getInstance().clearAppCache();
                    msg.what = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = -1;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    public static void openDownLoadService(Context context, String downurl,
                                           String tilte) {
        final ICallbackResult callback = new ICallbackResult() {

            @Override
            public void OnBackResult(Object s) {
            }
        };
        ServiceConnection conn = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownloadBinder binder = (DownloadBinder) service;
                binder.addCallback(callback);
                binder.start();

            }
        };
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, downurl);
        intent.putExtra(DownloadService.BUNDLE_KEY_TITLE, tilte);
        context.startService(intent);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 发送广播告知评论发生变化
     *
     * @param context
     * @param isBlog
     * @param id
     * @param catalog
     * @param operation
     * @param replyComment
     */
    public static void sendBroadCastCommentChanged(Context context,
                                                   boolean isBlog, int id, int catalog, int
                                                           operation,
                                                   Comment replyComment) {
        Intent intent = new Intent(Constants.INTENT_ACTION_COMMENT_CHANGED);
        Bundle args = new Bundle();
        args.putInt(Comment.BUNDLE_KEY_ID, id);
        args.putInt(Comment.BUNDLE_KEY_CATALOG, catalog);
        args.putBoolean(Comment.BUNDLE_KEY_BLOG, isBlog);
        args.putInt(Comment.BUNDLE_KEY_OPERATION, operation);
        args.putParcelable(Comment.BUNDLE_KEY_COMMENT, replyComment);
        intent.putExtras(args);
        context.sendBroadcast(intent);
    }

    public static void showCreateNewIssue(Context context, Team team,
                                          TeamProject project, TeamIssueCatalog catalog) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("team", team);
        if (project != null) {
            bundle.putSerializable("project", project);
        }
        if (catalog != null) {
            bundle.putSerializable("catalog", catalog);
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, TeamNewIssueActivity.class);
        context.startActivity(intent);
    }

    /***
     * 显示任务详情
     *
     * @param context
     * @param team
     * @param issue
     * @return void
     * @author 火蚁 2015-1-30 下午2:59:57
     */
    public static void showTeamIssueDetail(Context context, Team team,
                                           TeamIssue issue, TeamIssueCatalog catalog) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("teamid", team.getId());
        bundle.putInt("issueid", issue.getId());
        bundle.putSerializable("team", team);
        bundle.putSerializable("issue", issue);
        bundle.putSerializable("issue_catalog", catalog);
        bundle.putInt(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_TEAM_ISSUE_DETAIL);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 显示讨论贴详情
     *
     * @param context
     * @param team
     * @param discuss
     * @return void
     * @author 火蚁 2015-2-2 下午6:37:53
     */
    public static void showTeamDiscussDetail(Context context, Team team,
                                             TeamDiscuss discuss) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("teamid", team.getId());
        bundle.putInt("discussid", discuss.getId());
        bundle.putInt(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_TEAM_DISCUSS_DETAIL);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 显示周报详情
     *
     * @param context
     * @param data
     */
    public static void showDiaryDetail(Context context, Bundle data) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("diary", data);
        intent.putExtra(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_TEAM_DIARY);
        context.startActivity(intent);
    }

    public static void showTeamMemberInfo(Context context, int teamId,
                                          TeamMember teamMember) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TeamMemberAdapter.TEAM_MEMBER_KEY, teamMember);
        bundle.putInt(TeamMemberAdapter.TEAM_ID_KEY, teamId);
        UIHelper.showSimpleBack(context, SimpleBackPage.TEAM_USER_INFO, bundle);
    }

    /***
     * 显示团队动态详情
     *
     * @param contex
     * @param teamId
     * @param active
     * @return void
     * @author 火蚁 2015-3-13 下午5:34:50
     */
    public static void showTeamActiveDetail(Context contex, int teamId,
                                            TeamActive active) {
        Intent intent = new Intent(contex, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(TeamActiveFragment.DYNAMIC_FRAGMENT_KEY, active);
        bundle.putInt(TeamActiveFragment.DYNAMIC_FRAGMENT_TEAM_KEY, teamId);
        bundle.putInt(DetailActivity.BUNDLE_KEY_DISPLAY_TYPE,
                DetailActivity.DISPLAY_TEAM_TWEET_DETAIL);
        intent.putExtras(bundle);
        contex.startActivity(intent);
    }
}
