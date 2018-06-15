package com.androiddrawboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.zip.CheckedOutputStream;

/**
 * 画板
 */
public class DrawBoardView extends DrawView {

    private Path path;
    private Paint paint;
    private Canvas canvas;
    private boolean doClear;

    public DrawBoardView(Context context) {
        super(context);
    }

    public DrawBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setStrokeJoin(Paint.Join.ROUND);//设置拐角处更为平滑
        paint.setFilterBitmap(true);
        paint.setDither(true);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void drawing(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;//表示此View拦截处理触摸事件
    }

    public void reset() {
       doClear = true;
    }

    public void paint() {
        doClear = false;
    }

    public void setPaintColor(int color){
        paint.setColor(color);
    }
}
