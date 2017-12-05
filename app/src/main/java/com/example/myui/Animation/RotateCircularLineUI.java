package com.example.myui.Animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.example.myui.R;


/**
 * Created by i on 2016/1/15.
 */
public class RotateCircularLineUI extends View {

    Context mContext;
    int mColor;
    float mRadius;
    Paint mPaint;
    public RotateCircularLineUI(Context context) {
        super(context);
        this.mContext = mContext;
    }

    public RotateCircularLineUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = mContext;
    }

    public RotateCircularLineUI(Context mContext, int mColor, float mRadius){
        super(mContext);
        this.mContext = mContext;
        this.mColor = mColor;
        this.mRadius = mRadius;
        initPaint();
    }

    public void initPaint(){

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.line_width));

        mPaint.setColor(mColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);

        PathEffect effects = new DashPathEffect(new float[] { 4, 4, 4, 4}, 1);
        mPaint.setPathEffect(effects);
        canvas.drawCircle(0,0,mRadius,mPaint);


    }
}
