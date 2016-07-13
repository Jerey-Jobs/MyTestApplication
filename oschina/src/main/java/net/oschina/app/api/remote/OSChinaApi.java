package net.oschina.app.api.remote;

import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import net.oschina.app.AppContext;
import net.oschina.app.AppException;
import net.oschina.app.api.ApiHttpClient;
import net.oschina.app.bean.EventApplyData;
import net.oschina.app.bean.NewsList;
import net.oschina.app.bean.Report;
import net.oschina.app.bean.Tweet;
import net.oschina.app.team.bean.Team;
import net.oschina.app.util.StringUtils;

import org.kymjs.kjframe.utils.KJLoger;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

public class OSChinaApi {

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param handler
     */
    public static void login(String username, String password,
                             AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("pwd", password);
        params.put("keep_login", 1);
        String loginurl = "action/api/login_validate";
        ApiHttpClient.post(loginurl, params, handler);
    }

    public static void openIdLogin(String s) {

    }

    /**
     * 获取新闻列表
     *
     * @param catalog 类别 （1，2，3）
     * @param page    第几页
     * @param handler
     */
    public static void getNewsList(int catalog, int page,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        if (catalog == NewsList.CATALOG_WEEK) {
            params.put("show", "week");
        } else if (catalog == NewsList.CATALOG_MONTH) {
            params.put("show", "month");
        }
        ApiHttpClient.get("action/api/news_list", params, handler);
    }

    public static void getBlogList(String type, int pageIndex,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("type", type);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/blog_list", params, handler);
    }

    public static void getPostList(int catalog, int page,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/post_list", params, handler);
    }

    public static void getPostListByTag(String tag, int page,
                                        AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("tag", tag);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/post_list", params, handler);
    }

    public static void getTweetList(int uid, int page,
                                    AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/tweet_list", params, handler);
    }

    public static void getTweetTopicList(int page, String topic,
                                         AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("pageIndex", page);
        params.put("title", topic);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/tweet_topic_list", params, handler);
    }

    /**
     * get   TweetLikeList
     *
     * @param pageIndex begin 0
     * @param handler   asyncHttpResponseHandler
     */
    public static void getTweetLikeList(int pageIndex, AsyncHttpResponseHandler handler) {
        ApiHttpClient.get("action/api/my_tweet_like_list?pageIndex=" + pageIndex + "&pageSize=" + 20, handler);
    }

    public static void pubLikeTweet(int tweetId, int authorId,
                                    AsyncHttpResponseHandler handler) {

        RequestParams params = new RequestParams();
        params.put("tweetid", tweetId);
        params.put("uid", AppContext.getInstance().getLoginUid());
        params.put("ownerOfTweet", authorId);
        ApiHttpClient.post("action/api/tweet_like", params, handler);
    }

    public static void pubUnLikeTweet(int tweetId, int authorId,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("tweetid", tweetId);
        params.put("uid", AppContext.getInstance().getLoginUid());
        params.put("ownerOfTweet", authorId);
        ApiHttpClient.post("action/api/tweet_unlike", params, handler);
    }

    public static void getTweetLikeList(int tweetId, int page,
                                        AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("tweetid", tweetId);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/tweet_like_list", params, handler);

    }

    public static void getActiveList(int uid, int catalog, int page,
                                     AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("catalog", catalog);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/active_list", params, handler);
    }

    public static void getFriendList(int uid, int relation, int page,
                                     AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("relation", relation);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/friends_list", params, handler);
    }

    /**
     * 获取所有关注好友列表
     *
     * @param uid     指定用户UID
     * @param handler
     */
    public static void getAllFriendsList(int uid, int relation, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("relation", relation);
        params.put("all", 1);
        ApiHttpClient.get("action/api/friends_list", params, handler);
    }

    /**
     * 获取用户收藏
     *
     * @param uid     指定用户UID
     * @param type    收藏类型: 0:全部收藏　1:软件　2:话题　3:博客　4:新闻　5:代码
     * @param page
     * @param handler
     */
    public static void getFavoriteList(int uid, int type, int page,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("type", type);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/favorite_list", params, handler);
    }

    /**
     * 分类列表
     *
     * @param tag     第一级:0
     * @param handler
     */
    public static void getSoftwareCatalogList(int tag,
                                              AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("tag", tag);
        ApiHttpClient.get("action/api/softwarecatalog_list", params, handler);
    }

    public static void getSoftwareTagList(int searchTag, int page,
                                          AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("searchTag", searchTag);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/softwaretag_list", params, handler);
    }

    /**
     * @param searchTag 　　软件分类　　推荐:recommend 最新:time 热门:view 国产:list_cn
     * @param page
     * @param handler
     */
    public static void getSoftwareList(String searchTag, int page,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("searchTag", searchTag);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/software_list", params, handler);
    }

    /**
     * 获取评论列表
     *
     * @PARAM ID
     * @PARAM CATALOG
     * 1新闻 2帖子 3动弹 4动态
     * @PARAM PAGE
     * @PARAM HANDLER
     */
    public static void getCommentList(int id, int catalog, int page,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        params.put("clientType", "android");
        ApiHttpClient.get("action/api/comment_list", params, handler);
    }

    public static void getBlogCommentList(int id, int page,
                                          AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/blogcomment_list", params, handler);
    }

    public static void getChatMessageList(int friendId, int page, AsyncHttpResponseHandler
            handler) {
        RequestParams params = new RequestParams();
        params.put("id", friendId);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/message_detail", params, handler);
    }

    public static void getUserInformation(int uid, int hisuid, String hisname,
                                          int pageIndex, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("hisuid", hisuid);
        params.put("hisname", hisname);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/user_information", params, handler);
    }

    @SuppressWarnings("deprecation")
    public static void getUserBlogList(int authoruid, final String authorname,
                                       final int uid, final int pageIndex,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("authoruid", authoruid);
        params.put("authorname", URLEncoder.encode(authorname));
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/userblog_list", params, handler);
    }

    public static void updateRelation(long uid, long hisuid, int newrelation,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("hisuid", hisuid);
        params.put("newrelation", newrelation);
        ApiHttpClient.post("action/api/user_updaterelation", params, handler);
    }

    public static void getMyInformation(int uid,
                                        AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        ApiHttpClient.get("action/api/my_information", params, handler);
    }

    /**
     * 获取新闻明细
     *
     * @param id      新闻的id
     * @param handler
     */
    public static void getNewsDetail(int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("id", id);
        ApiHttpClient.get("action/api/news_detail", params, handler);
    }

    public static void getBlogDetail(int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("id", id);
        ApiHttpClient.get("action/api/blog_detail", params, handler);
    }

    /**
     * 获取软件详情
     *
     * @param ident
     * @param handler
     */
    public static void getSoftwareDetail(String ident,
                                         AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("ident",
                ident);
        ApiHttpClient.get("action/api/software_detail", params, handler);
    }

    /***
     * 通过id获取软件详情
     *
     * @param id
     * @param handler
     */
    public static void getSoftwareDetail(int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("id",
                id);
        ApiHttpClient.get("action/api/software_detail", params, handler);
    }

    public static void getPostDetail(int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("id", id);
        ApiHttpClient.get("action/api/post_detail", params, handler);
    }

    public static void getTweetDetail(int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams("id", id);
        ApiHttpClient.get("action/api/tweet_detail", params, handler);
    }

    /**
     * 用户针对某个新闻，帖子，动弹，消息发表评论的接口，参数使用POST方式提交
     *
     * @param catalog        　　 1新闻　　2 帖子　　３　动弹　　４消息中心
     * @param id             被评论的某条新闻，帖子，动弹或者某条消息的id
     * @param uid            当天登陆用户的UID
     * @param content        发表的评论内容
     * @param isPostToMyZone 是否转发到我的空间，０不转发　　１转发到我的空间（注意该功能之对某条动弹进行评论是有效，其他情况下服务器借口可以忽略该参数）
     * @param handler
     */
    public static void publicComment(int catalog, long id, int uid,
                                     String content, int isPostToMyZone, AsyncHttpResponseHandler
                                             handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("uid", uid);
        params.put("content", content);
        params.put("isPostToMyZone", isPostToMyZone);
        ApiHttpClient.post("action/api/comment_pub", params, handler);
    }

    public static void replyComment(int id, int catalog, int replyid,
                                    int authorid, int uid, String content,
                                    AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("uid", uid);
        params.put("content", content);
        params.put("replyid", replyid);
        params.put("authorid", authorid);
        ApiHttpClient.post("action/api/comment_reply", params, handler);
    }

    public static void publicBlogComment(long blog, int uid, String content,
                                         AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("blog", blog);
        params.put("uid", uid);
        params.put("content", content);
        ApiHttpClient.post("action/api/blogcomment_pub", params, handler);
    }

    public static void replyBlogComment(long blog, long uid, String content,
                                        long reply_id, long objuid, AsyncHttpResponseHandler
                                                handler) {
        RequestParams params = new RequestParams();
        params.put("blog", blog);
        params.put("uid", uid);
        params.put("content", content);
        params.put("reply_id", reply_id);
        params.put("objuid", objuid);
        ApiHttpClient.post("action/api/blogcomment_pub", params, handler);
    }

    public static void pubTweet(Tweet tweet, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", tweet.getAuthorid());
        params.put("msg", tweet.getBody());

        // Map<String, File> files = new HashMap<String, File>();
        if (!TextUtils.isEmpty(tweet.getImageFilePath())) {
            try {
                params.put("img", new File(tweet.getImageFilePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(tweet.getAudioPath())) {
            try {
                params.put("amr", new File(tweet.getAudioPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        ApiHttpClient.post("action/api/tweet_pub", params, handler);
    }

    public static void pubSoftWareTweet(Tweet tweet, int softid,
                                        AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", tweet.getAuthorid());
        params.put("msg", tweet.getBody());
        params.put("project", softid);
        ApiHttpClient.post("action/api/software_tweet_pub", params, handler);
    }

    public static void deleteTweet(int uid, int tweetid,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("tweetid", tweetid);
        ApiHttpClient.post("action/api/tweet_delete", params, handler);
    }

    public static void deleteComment(int id, int catalog, int replyid,
                                     int authorid, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("catalog", catalog);
        params.put("replyid", replyid);
        params.put("authorid", authorid);
        ApiHttpClient.post("action/api/comment_delete", params, handler);
    }

    public static void deleteBlog(int uid, int authoruid, int id,
                                  AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("authoruid", authoruid);
        params.put("id", id);
        ApiHttpClient.post("action/api/userblog_delete", params, handler);
    }

    public static void deleteBlogComment(int uid, int blogid, int replyid,
                                         int authorid, int owneruid, AsyncHttpResponseHandler
                                                 handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("blogid", blogid);
        params.put("replyid", replyid);
        params.put("authorid", authorid);
        params.put("owneruid", owneruid);
        ApiHttpClient.post("action/api/blogcomment_delete", params, handler);
    }

    /**
     * 用户添加收藏
     *
     * @param uid   用户UID
     * @param objid 比如是新闻ID 或者问答ID 或者动弹ID
     * @param type  1:软件 2:话题 3:博客 4:新闻 5:代码
     */
    public static void addFavorite(int uid, long objid, int type,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("objid", objid);
        params.put("type", type);
        ApiHttpClient.post("action/api/favorite_add", params, handler);
    }

    public static void delFavorite(int uid, long objid, int type,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("objid", objid);
        params.put("type", type);
        ApiHttpClient.post("action/api/favorite_delete", params, handler);
    }

    public static void getSearchList(String catalog, String content,
                                     int pageIndex, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("content", content);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/search_list", params, handler);
    }

    public static void publicMessage(int uid, int receiver, String content,
                                     AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("receiver", receiver);
        params.put("content", content);

        ApiHttpClient.post("action/api/message_pub", params, handler);
    }

    public static void deleteMessage(int uid, int friendid,
                                     AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("friendid", friendid);
        ApiHttpClient.post("action/api/message_delete", params, handler);
    }

    public static void forwardMessage(int uid, String receiverName,
                                      String content, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("receiverName", receiverName);
        params.put("content", content);
        ApiHttpClient.post("action/api/message_pub", params, handler);
    }

    public static void getMessageList(int uid, int pageIndex,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/message_list", params, handler);
    }

    public static void updatePortrait(int uid, File portrait,
                                      AsyncHttpResponseHandler handler) throws
            FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("portrait", portrait);
        ApiHttpClient.post("action/api/portrait_update", params, handler);
    }

    public static void getNotices(AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", AppContext.getInstance().getLoginUid());
        ApiHttpClient.get("action/api/user_notice", params, handler);
    }

    /**
     * 清空通知消息
     *
     * @param uid
     * @param type 1:@我的信息 2:未读消息 3:评论个数 4:新粉丝个数
     * @return
     * @throws AppException
     */
    public static void clearNotice(int uid, int type,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("type", type);
        ApiHttpClient.post("action/api/notice_clear", params, handler);
    }

    public static void singnIn(String url, AsyncHttpResponseHandler handler) {
        ApiHttpClient.getDirect(url, handler);
    }

    /**
     * 获取软件的动态列表
     *
     * @param softid
     * @param handler
     */
    public static void getSoftTweetList(int softid, int page,
                                        AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("project", softid);
        params.put("pageIndex", page);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/software_tweet_list", params, handler);
    }

    public static void checkUpdate(AsyncHttpResponseHandler handler) {
        ApiHttpClient.get("MobileAppVersion.xml", handler);
    }

    /**
     * 查找用户
     *
     * @param username
     * @param handler
     */
    public static void findUser(String username,
                                AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("name", username);
        ApiHttpClient.get("action/api/find_user", params, handler);
    }

    /**
     * 获取活动列表
     *
     * @param pageIndex
     * @param uid       <= 0 近期活动 实际的用户ID 则获取用户参与的活动列表，需要已登陆的用户
     * @param handler
     */
    public static void getEventList(int pageIndex, int uid,
                                    AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("pageIndex", pageIndex);
        params.put("uid", uid);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/event_list", params, handler);
    }

    /**
     * 获取某活动已出席的人员列表
     *
     * @param eventId
     * @param pageIndex
     * @param handler
     */
    public static void getEventApplies(int eventId, int pageIndex,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("pageIndex", pageIndex);
        params.put("event_id", eventId);
        params.put("pageSize", AppContext.PAGE_SIZE);
        ApiHttpClient.get("action/api/event_attend_user", params, handler);
    }

    /**
     * 举报
     *
     * @param report
     * @param handler
     */
    public static void report(Report report, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("obj_id", report.getObjId());
        params.put("url", report.getUrl());
        params.put("obj_type", report.getObjType());
        params.put("reason", report.getReason());
        if (report.getOtherReason() != null
                && !StringUtils.isEmpty(report.getOtherReason())) {
            params.put("memo", report.getOtherReason());
        }
        ApiHttpClient.post("action/communityManage/report", params, handler);
    }

    /**
     * 摇一摇，随机数据
     *
     * @param handler
     */
    public static void shake(AsyncHttpResponseHandler handler) {
        shake(-1, handler);
    }

    /**
     * 摇一摇指定请求类型
     */
    public static void shake(int type, AsyncHttpResponseHandler handler) {
        String inter = "action/api/rock_rock";
        if (type > 0) {
            inter = (inter + "/?type=" + type);
        }
        ApiHttpClient.get(inter, handler);
    }

    /**
     * 活动报名
     *
     * @param data
     * @param handler
     */
    public static void eventApply(EventApplyData data,
                                  AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("event", data.getEvent());
        params.put("user", data.getUser());
        params.put("name", data.getName());
        params.put("gender", data.getGender());
        params.put("mobile", data.getPhone());
        params.put("company", data.getCompany());
        params.put("job", data.getJob());
        if (!StringUtils.isEmpty(data.getRemark())) {
            params.put("misc_info", data.getRemark());
        }
        ApiHttpClient.post("action/api/event_apply", params, handler);
    }

    private static void uploadLog(String data, String report,
                                  AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("app", "1");
        params.put("report", report);
        params.put("msg", data);
        ApiHttpClient.post("action/api/user_report_to_admin", params, handler);
    }

    /**
     * BUG上报
     *
     * @param data
     * @param handler
     */
    public static void uploadLog(String data, AsyncHttpResponseHandler handler) {
        uploadLog(data, "1", handler);
    }

    /**
     * team动态
     *
     * @param team
     * @param page
     * @param handler
     */
    public static void teamDynamic(Team team, int page,
                                   AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        // int uid = AppContext.getInstance().getLoginUid();
        // params.put("uid", uid);
        params.put("teamid", team.getId());
        params.put("pageIndex", page);
        params.put("pageSize", 20);
        params.put("type", "all");
        ApiHttpClient.get("action/api/team_active_list", params, handler);
    }

    /**
     * 获取team列表
     *
     * @param handler
     */
    public static void teamList(AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", AppContext.getInstance().getLoginUid());
        ApiHttpClient.get("action/api/team_list", params, handler);
    }

    /**
     * 获取team成员列表
     *
     * @param handler
     */
    public static void getTeamMemberList(int teamid,
                                         AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        ApiHttpClient.get("action/api/team_member_list", params, handler);
    }

    /**
     * 获取team成员个人信息
     *
     * @param handler
     */
    public static void getTeamUserInfo(String teamid, String uid,
                                       int pageIndex, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", 20);
        ApiHttpClient.get("action/api/team_user_information", params, handler);
    }

    /**
     * 获取我的任务中进行中、未完成、已完成等状态的数量
     */
    public static void getMyIssueState(String teamid, String uid,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("uid", uid);
        ApiHttpClient.get("action/api/team_user_issue_information", params,
                handler);
    }

    /**
     * 获取指定用户的动态
     */
    public static void getUserDynamic(int teamid, String uid, int pageIndex,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", 20);
        params.put("type", "git");
        params.put("uid", uid);
        ApiHttpClient.get("action/api/team_active_list", params, handler);
    }

    /**
     * 动态详情
     *
     * @param activeid
     * @param teamid
     * @param uid
     * @param handler
     */
    public static void getDynamicDetail(int activeid, int teamid, int uid,
                                        AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("uid", uid);
        params.put("activeid", activeid);
        ApiHttpClient.get("action/api/team_active_detail", params, handler);
    }

    /**
     * 获取指定用户的任务
     */
    public static void getMyIssue(String teamid, String uid, int pageIndex,
                                  String type, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", 20);
        params.put("state", type);
        params.put("projectid", "-1");
        ApiHttpClient.get("action/api/team_issue_list", params, handler);
    }

    /**
     * 获取指定周周报
     *
     * @param teamid
     * @param year
     * @param week
     * @param handler
     */
    public static void getDiaryFromWhichWeek(int teamid, int year, int week,
                                             AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("year", year);
        params.put("week", week);
        ApiHttpClient.get("action/api/team_diary_list", params, handler);
    }

    /**
     * 删除一个便签
     *
     * @param id  便签id
     * @param uid 用户id
     */
    public static void deleteNoteBook(int id, int uid,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("id", id); // 便签id
        ApiHttpClient
                .get("action/api/team_stickynote_recycle", params, handler);
    }

    public static void getNoteBook(int uid, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        ApiHttpClient.get("action/api/team_sticky_list", params, handler);
    }

    /**
     * 获取指定周报的详细信息
     *
     * @param teamid
     * @param diaryid
     * @param handler
     */
    public static void getDiaryDetail(int teamid, int diaryid,
                                      AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("diaryid", diaryid);
        ApiHttpClient.get("action/api/team_diary_detail", params, handler);
    }

    /**
     * diary评论列表
     *
     * @param teamid
     * @param diaryid
     * @param handler
     */
    public static void getDiaryComment(int teamid, int diaryid,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("teamid", teamid);
        params.put("id", diaryid);
        params.put("type", "diary");
        params.put("pageIndex", 0);
        params.put("pageSize", "20");
        KJLoger.debug(teamid + "==getDiaryComment接口=" + diaryid);
        ApiHttpClient
                .get("action/api/team_reply_list_by_type", params, handler);
    }

    /**
     * 周报评论（以后可改为全局评论）
     *
     * @param uid
     * @param teamid
     * @param diaryId
     * @param content
     * @param handler
     */
    public static void sendComment(int uid, int teamid, int diaryId,
                                   String content, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("teamid", teamid);
        params.put("type", "118");
        params.put("tweetid", diaryId);
        params.put("content", content);
        ApiHttpClient.post("action/api/team_tweet_reply", params, handler);
    }

    /***
     * 客户端扫描二维码登陆
     *
     * @param url
     * @param handler
     * @return void
     * @author 火蚁 2015-3-13 上午11:45:47
     */
    public static void scanQrCodeLogin(String url,
                                       AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        String uuid = url.substring(url.lastIndexOf("=") + 1);
        params.put("uuid", uuid);
        ApiHttpClient.getDirect(url, handler);
    }

    /***
     * 使用第三方登陆
     *
     * @param catalog    类别
     * @param openIdInfo 第三方的info
     * @param handler    handler
     */
    public static void open_login(String catalog, String openIdInfo, AsyncHttpResponseHandler
            handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("openid_info", openIdInfo);
        ApiHttpClient.post("action/api/openid_login", params, handler);
    }

    /***
     * 第三方登陆账号绑定
     *
     * @param catalog    类别（QQ、wechat）
     * @param openIdInfo 第三方info
     * @param userName   用户名
     * @param pwd        密码
     * @param handler    handler
     */
    public static void bind_openid(String catalog, String openIdInfo, String userName, String
            pwd, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("openid_info", openIdInfo);
        params.put("username", userName);
        params.put("pwd", pwd);
        ApiHttpClient.post("action/api/openid_bind", params, handler);
    }

    /***
     * 使用第三方账号注册
     *
     * @param catalog    类别（qq、wechat）
     * @param openIdInfo 第三方info
     * @param handler    handler
     */
    public static void openid_reg(String catalog, String openIdInfo, AsyncHttpResponseHandler
            handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        params.put("openid_info", openIdInfo);
        ApiHttpClient.post("action/api/openid_reg", params, handler);
    }

    /**
     * 意见反馈
     */
    public static void feedback(String content, File file, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        if (file != null && file.exists())
            try {
                params.put("file", file);
            } catch (FileNotFoundException e) {
            }
        params.put("uid", AppContext.getInstance().getLoginUid());
        params.put("receiver", "2609904");
        params.put("content", content);
        ApiHttpClient.post("action/api/message_pub", params, handler);
    }


    public static final int CATALOG_BANNER_NEWS = 1; // 资讯Banner
    public static final int CATALOG_BANNER_BLOG = 2; // 博客Banner
    public static final int CATALOG_BANNER_EVENT = 3; // 活动Banner

    /**
     * 请求Banner列表接口
     *
     * @param catalog Banner类别 {@link #CATALOG_BANNER_NEWS, #CATALOG_BANNER_BLOG, #CATALOG_BANNER_EVENT}
     * @param handler AsyncHttpResponseHandler
     */
    public static void getBannerList(int catalog, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        ApiHttpClient.get("action/apiv2/banner", params, handler);
    }


    /**
     * 请求资讯列表
     *
     * @param pageToken 请求上下页数据令牌
     * @param handler   AsyncHttpResponseHandler
     */
    public static void getNewsList(String pageToken, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        if (!TextUtils.isEmpty(pageToken)) {
            params.put("pageToken", pageToken);
        }

        ApiHttpClient.get("action/apiv2/news", params, handler);
    }


    public static final String CATALOG_NEWS_DETAIL = "news";
    public static final String CATALOG_TRANSLATE_DETAIL = "translation";
    public static final String CATALOG_SOFTWARE_DETAIL = "software";

    /**
     * 请求资讯详情
     *
     * @param id      请求该资讯详情页
     * @param handler AsyncHttpResponseHandler
     */
    public static void getNewsDetail(long id, String type, AsyncHttpResponseHandler handler) {
        if (id <= 0) return;
        RequestParams params = new RequestParams();
        params.put("id", id);
        ApiHttpClient.get("action/apiv2/" + type, params, handler);
    }

    public static final int CATALOG_BLOG_NORMAL = 1; // 最近
    public static final int CATALOG_BLOG_HEAT = 2; // 最热

    /**
     * 请求博客列表
     *
     * @param catalog   博客类别 {@link #CATALOG_BLOG_NORMAL, #CATALOG_BLOG_HEAT}
     * @param pageToken 请求上下页数据令牌
     * @param handler   AsyncHttpResponseHandler
     */
    public static void getBlogList(int catalog, String pageToken, AsyncHttpResponseHandler handler) {
        if (catalog <= 0)
            catalog = 1;
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        if (!TextUtils.isEmpty(pageToken)) {
            params.put("pageToken", pageToken);
        }

        ApiHttpClient.get("action/apiv2/blog", params, handler);
    }

    /**
     * 请求用户自己的博客列表
     *
     * @param catalog   博客类别 {@link #CATALOG_BLOG_NORMAL, #CATALOG_BLOG_HEAT}
     * @param pageToken 请求上下页数据令牌
     * @param handler   AsyncHttpResponseHandler
     */
    public static void getUserBlogList(int catalog, String pageToken, int userId, AsyncHttpResponseHandler handler) {
        if (catalog <= 0)
            catalog = 1;
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        if (!TextUtils.isEmpty(pageToken)) {
            params.put("pageToken", pageToken);
        }
        if (userId <= 0) return;
        params.put("userId", userId);
        ApiHttpClient.get("action/apiv2/blog", params, handler);
    }


    /**
     * 请求博客详情
     *
     * @param id      博客id
     * @param handler AsyncHttpResponseHandler
     */
    public static void getBlogDetail(long id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        ApiHttpClient.get("action/apiv2/blog", params, handler);
    }


    /**
     * 请求活动列表
     *
     * @param pageToken 请求上下页数据令牌
     * @param handler   AsyncHttpResponseHandler
     */
    public static void getEventList(String pageToken, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        if (!TextUtils.isEmpty(pageToken)) {
            params.put("pageToken", pageToken);
        }

        ApiHttpClient.get("action/apiv2/event", params, handler);
    }

    /**
     * 请求活动详情
     *
     * @param id      id
     * @param handler handler
     */
    public static void getEventDetail(long id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        ApiHttpClient.get("action/apiv2/event", params, handler);
    }

    /**
     * 更改收藏状态
     *
     * @param id      id
     * @param type    type
     * @param handler handler
     */
    public static void getFavReverse(long id, int type, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("type", type);
        ApiHttpClient.get("action/apiv2/favorite_reverse", params, handler);
    }

    /**
     * 更改关注状态（关注／取消关注）
     *
     * @param id      id
     * @param handler handler
     */
    public static void addUserRelationReverse(long id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        ApiHttpClient.get("action/apiv2/user_relation_reverse", params, handler);
    }

    public static final int CATALOG_QUESTION_QUESTION = 1; // 提问
    public static final int CATALOG_QUESTION_SHARE = 2; // 最热
    public static final int CATALOG_QUESTION_MULTI = 3; // 综合
    public static final int CATALOG_QUESTION_OCC = 4; // 职业
    public static final int CATALOG_QUESTION_DEPOT = 5; // 站务

    /**
     * 请求问答列表
     *
     * @param catalog   问答类型  {@link #CATALOG_QUESTION_QUESTION, #CATALOG_QUESTION_SHARE, #CATALOG_QUESTION_MULTI},
     *                  {@link #CATALOG_QUESTION_OCC, #CATALOG_QUESTION_DEPOT}
     * @param pageToken 请求上下页数据令牌
     * @param handler   AsyncHttpResponseHandler
     */
    public static void getQuestionList(int catalog, String pageToken, AsyncHttpResponseHandler handler) {
        if (catalog <= 0)
            catalog = 1;
        RequestParams params = new RequestParams();
        params.put("catalog", catalog);
        if (!TextUtils.isEmpty(pageToken)) {
            params.put("pageToken", pageToken);
        }

        ApiHttpClient.get("action/apiv2/question", params, handler);
    }

    /**
     * 请求问答详情
     *
     * @param id      问答id
     * @param handler AsyncHttpResponseHandler
     */
    public static void getQuestionDetail(long id, AsyncHttpResponseHandler handler) {
        if (id <= 0) return;
        RequestParams params = new RequestParams();
        params.put("id", id);
        ApiHttpClient.get("action/apiv2/question", params, handler);
    }

    /**
     * 请求评论详情
     *
     * @param id      评论Id
     * @param handler AsyncHttpResponseHandler
     */
    public static void getComment(long id, long aid, int type, TextHttpResponseHandler handler) {
        if (id <= 0) return;
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("authorId", aid);
        params.put("type", type);
        ApiHttpClient.get("action/apiv2/comment", params, handler);
    }

    public static final int COMMENT_SOFT = 1; // 软件推荐-不支持
    public static final int COMMENT_QUESTION = 2; // 讨论区帖子
    public static final int COMMENT_BLOG = 3; // 博客
    public static final int COMMENT_TRANSLATION = 4; // 翻译文章
    public static final int COMMENT_EVENT = 5; // 活动类型
    public static final int COMMENT_NEWS = 6; // 资讯类型

    /**
     * 请求评论列表
     *
     * @param sourceId  目标Id，该sourceId为资讯、博客、问答等文章的Id
     * @param type      问答类型  {@link #COMMENT_SOFT, #COMMENT_QUESTION, #COMMENT_BLOG},
     *                  {@link #COMMENT_TRANSLATION, #COMMENT_EVENT, #COMMENT_NEWS}
     * @param parts     请求的数据节点 parts="refer,reply"
     * @param pageToken 请求上下页数据令牌
     * @param handler   AsyncHttpResponseHandler
     */
    public static void getComments(long sourceId, int type, String parts, String pageToken, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("sourceId", sourceId);
        params.put("type", type);
        if (!TextUtils.isEmpty(parts)) {
            params.put("parts", parts);
        }
        if (!TextUtils.isEmpty(pageToken)) {
            params.put("pageToken", pageToken);
        }
        ApiHttpClient.get("action/apiv2/comment", params, handler);
    }

    /**
     * 发表评论
     *
     * @param sid     文章id
     * @param referId 引用的评论的id
     * @param replyId 回复的评论的id
     * @param oid     引用、回复的发布者id
     * @param type    文章类型 1:软件推荐, 2:问答帖子, 3:博客, 4:翻译文章, 5:活动, 6:资讯
     * @param content 内容
     * @param handler 你懂得
     */
    public static void publishComment(long sid, long referId, long replyId, long oid,
                                      int type, String content, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("sourceId", sid);
        params.put("type", type);
        params.put("content", content);
        if (referId > 0)
            params.put("referId", referId);
        if (replyId > 0)
            params.put("replyId", replyId);
        if (oid > 0)
            params.put("reAuthorId", oid);
        ApiHttpClient.post("action/apiv2/comment_pub", params, handler);
    }


    /**
     * 发表资讯评论
     *
     * @see {{@link #publicComment(int, long, int, String, int, AsyncHttpResponseHandler)}}
     */
    public static void pubNewsComment(long sid, long commentId, long commentAuthorId, String comment, TextHttpResponseHandler handler) {

        if (commentId == 0 || commentId == sid) {
            commentId = 0;
            commentAuthorId = 0;
        }
        publishComment(sid, 0, commentId, commentAuthorId, 6, comment, handler);
    }

    /**
     * 发表资讯评论
     *
     * @see {{@link #publicComment(int, long, int, String, int, AsyncHttpResponseHandler)}}
     */
    public static void pubQuestionComment(long sid, long commentId, long commentAuthorId, String comment, TextHttpResponseHandler handler) {

        if (commentId == 0 || commentId == sid) {
            commentId = 0;
            commentAuthorId = 0;
        }
        publishComment(sid, 0, commentId, commentAuthorId, 2, comment, handler);
    }


    /**
     * 发表翻译评论
     *
     * @see {{@link #publicComment(int, long, int, String, int, AsyncHttpResponseHandler)}}
     */
    public static void pubTranslateComment(long sid, long commentId, long commentAuthorId, String comment, TextHttpResponseHandler handler) {

        if (commentId == 0 || commentId == sid) {
            commentId = 0;
            commentAuthorId = 0;
        }
        publishComment(sid, 0, commentId, commentAuthorId, 4, comment, handler);
    }

    /**
     * 发布博客评论
     *
     * @see {{@link #publicComment(int, long, int, String, int, AsyncHttpResponseHandler)}}
     */
    public static void pubBlogComment(long sid, long commentId, long commentAuthorId, String comment, TextHttpResponseHandler handler) {
        if (commentId == 0 || commentId == sid) {
            commentId = 0;
            commentAuthorId = 0;
        }

        publishComment(sid, 0, commentId, commentAuthorId, 3, comment, handler);
    }

    /**
     * 问答的回答, 顶\踩
     *
     * @param sid     source id 问答的id
     * @param cid     回答的id
     * @param opt     操作类型 0:取消, 1:顶, 2:踩
     * @param handler
     */
    public static void questionVote(long sid, long cid, int opt, TextHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("sourceId", sid);
        params.put("commentId", cid);
        params.put("voteOpt", opt);
        ApiHttpClient.post("action/apiv2/question_vote", params, handler);
    }
}
