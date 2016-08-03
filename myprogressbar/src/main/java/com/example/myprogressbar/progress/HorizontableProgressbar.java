package com.example.myprogressbar.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.myprogressbar.R;

/**
 * Created by Xiamin on 2016/8/3.
 */
public class HorizontableProgressbar extends ProgressBar {
    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0xfffc00d1;
    private static final int DEFAULT_UNREACH_COLOR = 0xFFD3D6DA;
    private static final int DEFAULT_UNREACH_HEIGHT= 10;
    private static final int DEFAULT_REACH_COLOR = 0xfffc00d1;
    private static final int DEFAULT_REACH_HEIGHT = 10;//dp
    private static final int DEFAULT_TEXT_OFFSET = 10;//dp

    private int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private int mUnreachColor = DEFAULT_UNREACH_COLOR;
    private int mReachColor = DEFAULT_REACH_COLOR;
    private int mUnreadHeight = dp2px(DEFAULT_UNREACH_HEIGHT);
    private int mReachHeight= dp2px(DEFAULT_REACH_HEIGHT);
    private int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    private int mRealWidth;
    private Paint mPaint = new Paint();

    public HorizontableProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        obaStyleAttrs(attrs);


    }


    private void obaStyleAttrs(AttributeSet attr)
    {
        TypedArray ta = getContext().obtainStyledAttributes(attr, R.styleable.MyHorizontableProgressbar);

        mTextSize = (int) ta.getDimension(R.styleable.MyHorizontableProgressbar_progress_text_size,mTextSize);
        mTextColor = ta.getColor(R.styleable.MyHorizontableProgressbar_progress_text_color,mTextColor);
        mUnreachColor = ta.getColor(R.styleable.MyHorizontableProgressbar_progress_reach_color,mUnreachColor);
        mUnreadHeight = (int) ta.getDimension(R.styleable.MyHorizontableProgressbar_progress_unreach_height,mUnreadHeight);
        mReachColor = ta.getColor(R.styleable.MyHorizontableProgressbar_progress_reach_color,mReachColor);
        mReachHeight = (int) ta.getDimension(R.styleable.MyHorizontableProgressbar_progress_reach_height,mReachHeight);
        mTextOffset = (int) ta.getDimension(R.styleable.MyHorizontableProgressbar_progress_text_offset,mTextOffset);
        Log.i("iii","获取自定义属性");
        ta.recycle();
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widVal = MeasureSpec.getSize(widthMeasureSpec);
        int height = measurHeight(heightMeasureSpec);

        /**
         * 必须告诉view我们已经测量好了
         */
        setMeasuredDimension(widVal,height);

        mRealWidth = getMeasuredWidth()  - getPaddingLeft() - getPaddingRight();
    }

    private int measurHeight(int height)
    {
        int res = 0;
        int mode = MeasureSpec.getMode(height);
        int size = MeasureSpec.getSize(height);

        if(mode == MeasureSpec.EXACTLY)
        {
            res = size;
        }
        else
        {
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            res = getPaddingTop() + getPaddingBottom() + Math.max(mReachHeight,mUnreadHeight)
                    + Math.abs(textHeight);

            if(mode == MeasureSpec.AT_MOST)
            {
                res = Math.min(res,size);
            }
        }
        return res;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();

        canvas.translate(getPaddingLeft(),getHeight()/2);

        boolean isNeedUnreach = false;
        String text = getProgress() + "%";
        int textwidth = (int) mPaint.measureText(text);
        float radio = getProgress()*1.0f/getMax();
        float endx = radio*mRealWidth - mTextOffset/2;
        float progressX = radio*mRealWidth;
        if(progressX + textwidth > mRealWidth)
        {
            progressX = mRealWidth -textwidth;
            isNeedUnreach = true;
        }

        if(endx > 0)
        {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0,0,endx,0,mPaint);
        }

        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() + mPaint.ascent()) / 2);
        canvas.drawText(text,progressX,y,mPaint);

        /**
         * 绘制unreachbar
         */
        if(!isNeedUnreach)
        {
            float start = progressX + mTextOffset / 2 + textwidth;
            mPaint.setColor(mUnreachColor);
            mPaint.setStrokeWidth(mUnreadHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }

        canvas.restore();
    }

    public HorizontableProgressbar(Context context) {
        this(context,null);
    }

    public HorizontableProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    private int dp2px(int dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,
                getResources().getDisplayMetrics());
    }


    private int sp2px(int spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,
                getResources().getDisplayMetrics());
    }

}
