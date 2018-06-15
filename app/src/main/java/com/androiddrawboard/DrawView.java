package com.androiddrawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class DrawView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private Canvas canvas;//画布
    private boolean isDrawing;

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        isDrawing = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDrawing){
                    /**取得更新之前的时间**/
                    long startTime = System.currentTimeMillis();

                    /**在这里加上线程安全锁**/
                    synchronized (holder) {
                        /**拿到当前画布 然后锁定**/
                        canvas =holder.lockCanvas();
                        if(canvas!=null){
                            drawing(canvas);
                            /**绘制结束后解锁显示在屏幕上**/
                            holder.unlockCanvasAndPost(canvas);
                        }
                    }

                    /**取得更新结束的时间**/
                    long endTime = System.currentTimeMillis();

                    /**计算出一次更新的毫秒数**/
                    int diffTime  = (int)(endTime - startTime);

                    /**确保每次更新时间为30帧**/
                    while(diffTime <=getDrawingSpeed()) {
                        diffTime = (int)(System.currentTimeMillis() - startTime);
                        /**线程等待**/
                        Thread.yield();
                    }
                }
            }
        }).start();
    }

    protected int getDrawingSpeed() {
        return 30;
    }

    protected abstract void drawing(Canvas canvas);

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }
}
