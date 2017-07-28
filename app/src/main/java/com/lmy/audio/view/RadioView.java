package com.lmy.audio.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lmy on 2017/7/7
 */

public class RadioView extends View {

    private Paint mPaint;
    private Path circlePath;
    private Path circlePath2;
    String string = "测试文字偏移的参数";


    public RadioView(Context context) {
        this(context, null);
    }

    public RadioView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        circlePath = new Path();
        circlePath2 = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        circlePath.addCircle(getWidth() / 2, getHeight() / 2, 250, Path.Direction.CCW);
        canvas.drawPath(circlePath, mPaint);//绘制出路径原形

//        circlePath2.addCircle(550, 200, 100, Path.Direction.CCW);
//        canvas.drawPath(circlePath2, mPaint);//绘制出路径原形

        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(30);
//
//        //hoffset、voffset参数值全部设为0，看原始状态是怎样的

        canvas.drawTextOnPath(string, circlePath, 10, 10, mPaint);
//        //第二个路径，改变hoffset、voffset参数值
//        canvas.drawTextOnPath(string, circlePath2, 80, 30, mPaint);

    }
}
