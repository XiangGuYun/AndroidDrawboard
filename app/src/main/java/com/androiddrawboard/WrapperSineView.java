package com.androiddrawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * 测试封装DrawView来实现SineView
 */
public class WrapperSineView extends DrawView {

    private Paint paint;
    private int x, y;
    private Path path;

    public WrapperSineView(Context context) {
        super(context);
    }

    public WrapperSineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperSineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        path = new Path();
        path.moveTo(0,getHeight()/2);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setStrokeJoin(Paint.Join.ROUND);//设置拐角处更为平滑
    }

    @Override
    protected void drawing(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        x+=1;
        y=(int)(100*Math.sin(x*2*Math.PI/180)+400);
        path.lineTo(x,y);
        canvas.drawPath(path, paint);
    }


}
