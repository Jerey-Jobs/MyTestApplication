package com.example.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Xiamin on 2016/11/26.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread mThread;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("iii","surfaceCreated");
        mThread = new DrawThread(getContext(), holder);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.stopThread();
    }


    class DrawThread extends Thread {
        SurfaceHolder surfaceHolder;
        Context context;
        Paint paint;
        private boolean isRunning = true;
        float r = 10;
        float diff = 0;

        public DrawThread(Context context, SurfaceHolder holder) {
            this.context = context;
            this.surfaceHolder = holder;
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
        }

        @Override
        public void run() {
            while (isRunning) {
                synchronized (surfaceHolder) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    draw(canvas);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        public void draw(Canvas canvas) {
            if (r <30 ) {
                diff = 5;
            } else if(r > 150)
            {
                diff = -5;
            }
            r += diff;
            canvas.drawColor(Color.WHITE);
            canvas.translate(200, 200);
            canvas.drawCircle(0, 0, r, paint);
        }

        private void stopThread() {
            Log.i("iii","stopThread()");
            isRunning = false;
        }
    }
}
