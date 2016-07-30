package com.view.myViewPagerIndicator;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.wechatindicator.R;

/**
 * Created by Xiamin on 2016/7/30.
 */
public class ViewPagerIndicator extends LinearLayout {

    private Paint mPaint;
    private Path mPath;
    private int mTriangleWidth;
    private int mTraiangeHeight;
    private static final float RADIO_WIDTH = 1/6F;

    private int mInitTranslationX;
    private int mTranslationX;

    private int mVisableTabCount;
    private static final int COUNT_DECLARE = 3;






    public ViewPagerIndicator(Context context) {
        super(context,null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

       TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);

        mVisableTabCount = a.getInt(R.styleable.ViewPagerIndicator_tabcount,COUNT_DECLARE);
        if(mVisableTabCount < 0)
        {
            mVisableTabCount = COUNT_DECLARE;
        }
        a.recycle();

        /**
         * 初始化画笔
         */
        mPaint = new Paint();
        mPaint.setAntiAlias(true); //  抗锯齿
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));


    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();


        canvas.translate(mInitTranslationX + mTranslationX,getHeight());
        canvas.drawPath(mPath,mPaint);

        canvas.restore();

        super.dispatchDraw(canvas);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (w/mVisableTabCount*RADIO_WIDTH);
        mInitTranslationX = w/2/mVisableTabCount - mTriangleWidth/2;

        initTrangle();

    }

    private void initTrangle()
    {
        mTraiangeHeight = mTriangleWidth/2;
        mPath = new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTraiangeHeight);
        mPath.close();
    }

    public void scroll(int pos,float offset)
    {
        int tabwidth = getWidth()/mVisableTabCount;
        mTranslationX = (int)(tabwidth * (offset + pos));

        /**
         * 当tab处于移动最后一个时候，
         */
        if(pos >= (mVisableTabCount - 2) && offset > 0 && getChildCount() > mVisableTabCount)
        {

            this.scrollTo((int) ((pos - (mVisableTabCount - 2))* tabwidth + (int)tabwidth*offset),0);
        }
        invalidate();
    }

    /**
     * 获取屏幕宽度
     */

    private int getScreenWidth()
    {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int cCount = getChildCount();
        if(cCount == 0){
            return;
        }
        else {
            for(int i = 0; i < cCount; i++)
            {
                View v = getChildAt(i);
                LinearLayout.LayoutParams lp = (LayoutParams) v.getLayoutParams();
                lp.weight = 0;
                lp.width = getScreenWidth()/mVisableTabCount;
                v.setLayoutParams(lp);
            }
        }

    }
}
