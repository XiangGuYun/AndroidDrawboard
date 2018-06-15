package com.androiddrawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 绘制正弦曲线
 */
public class SineView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Canvas canvas;//画布
    private boolean isDrawing;
    private int x;
    private int y;
    private Path path;
    private Paint paint;

    public SineView(Context context) {
        this(context,null);
    }

    public SineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        path = new Path();
        path.moveTo(0,getHeight()/2);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
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
                        draw();
                        /**绘制结束后解锁显示在屏幕上**/
                        holder.unlockCanvasAndPost(canvas);
                    }

                    /**取得更新结束的时间**/
                    long endTime = System.currentTimeMillis();

                    /**计算出一次更新的毫秒数**/
                    int diffTime  = (int)(endTime - startTime);

                    /**确保每次更新时间为30帧**/
                    while(diffTime <=30) {
                        diffTime = (int)(System.currentTimeMillis() - startTime);
                        /**线程等待**/
                        Thread.yield();
                    }
                }
            }
        }).start();
    }

    private void draw() {
         x+=1;
         y=(int)(100*Math.sin(x*2*Math.PI/180)+400);
         path.lineTo(x,y);
         canvas.drawPath(path, paint);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }
}
