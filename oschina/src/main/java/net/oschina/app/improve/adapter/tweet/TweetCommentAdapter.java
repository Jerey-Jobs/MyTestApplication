package net.oschina.app.improve.adapter.tweet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import net.oschina.app.R;
import net.oschina.app.bean.Comment;
import net.oschina.app.emoji.InputHelper;
import net.oschina.app.improve.adapter.base.BaseRecyclerAdapter;
import net.oschina.app.util.StringUtils;
import net.oschina.app.util.UIHelper;
import net.oschina.app.widget.TweetTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by thanatos on 16/6/13.
 */
public class TweetCommentAdapter extends BaseRecyclerAdapter<Comment> {

    private RequestManager reqManager;
    private View.OnClickListener onPortraitClickListener;

    public TweetCommentAdapter(Context context) {
        super(context, ONLY_FOOTER);
        reqManager = Glide.with(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new TweetCommentHolderView(LayoutInflater.from(mContext).inflate(R.layout.list_item_tweet_comment, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, Comment item, int position) {
        TweetCommentHolderView h = (TweetCommentHolderView)holder;
        h.ivPortrait.setTag(null);
        if (TextUtils.isEmpty(item.getPortrait())){
            h.ivPortrait.setImageResource(R.drawable.widget_dface);
        }else{
            reqManager
                    .load(item.getPortrait())
                    .asBitmap()
                    .placeholder(mContext.getResources().getDrawable(R.drawable.widget_dface))
                    .error(mContext.getResources().getDrawable(R.drawable.widget_dface))
                    .into(h.ivPortrait);
        }
        h.ivPortrait.setTag(item);
        h.ivPortrait.setOnClickListener(getOnPortraitClickListener());

        h.tvName.setText(item.getAuthor());
        h.tvContent.setText(InputHelper.displayEmoji(mContext.getResources(), item.getContent()));
        h.tvTime.setText(StringUtils.friendly_time(item.getPubDate()));
    }

    private View.OnClickListener getOnPortraitClickListener(){
        if (onPortraitClickListener == null){
            onPortraitClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Comment comment = (Comment) v.getTag();
                    UIHelper.showUserCenter(mContext, comment.getAuthorId(), comment.getAuthor());
                }
            };
        }
        return onPortraitClickListener;
    }

    public static final class TweetCommentHolderView extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_avatar) public CircleImageView ivPortrait;
        @Bind(R.id.tv_name) public TextView tvName;
        @Bind(R.id.tv_pub_date) public TextView tvTime;
        @Bind(R.id.btn_comment) public ImageView btnReply;
        @Bind(R.id.tv_content) public TweetTextView tvContent;

        public TweetCommentHolderView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
