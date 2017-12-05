package com.example.myui.TouchFeedback;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myui.R;

/**
 * Created by i on 2016/3/24.
 */


public class MoveImageView extends ViewGroup implements OnDragListener{

    FlowImageView mFlowView;
    //常量
    int radius;
    float circularRadiusMax;
    float circularRadiusMin;
    float circularRadiusInner;
    float MaxDistance;
    //变量
    float circularRadiusPaint;
    float formX;
    float formY;

    Paint paint;
    Paint paintBlue;
    MyPoint point1;
    MyPoint point3;

    MyPoint point4;
    MyPoint point5;

    MyPoint point6;
    MyPoint point8;

    boolean flag = false;


    public MoveImageView(Context context) {
        super(context);
        init();
    }

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = (r-l-radius)/2;
        int top = (b-t-radius)/2;
        int right = radius + (r-l-radius)/2;
        int bottom = radius + (b-t-radius)/2;
        getChildAt(0).layout(left, top, right, bottom);
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //设置抗锯齿。
        paint.setAntiAlias(true);

        paintBlue = new Paint();
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStrokeWidth(1);

        radius = getResources().getDimensionPixelOffset(R.dimen.margin_20);
        circularRadiusMax = getResources().getDimension(R.dimen.margin_8);
        circularRadiusMin = getResources().getDimension(R.dimen.margin_3);
        circularRadiusInner = getResources().getDimension(R.dimen.margin_1);


        LayoutParams params = new LayoutParams(radius,radius);

        mFlowView = new FlowImageView(getContext());
        mFlowView.setBackgroundColor(0x00000000);
        mFlowView.setListener(this);


        addView(mFlowView, params);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(flag){

            canvas.drawCircle(formX, formY, circularRadiusPaint, paint);

            Path path = new Path();
            path.moveTo(point1.x, point1.y);
            path.lineTo(point3.x, point3.y);
            path.quadTo(point5.x, point5.y, point8.x, point8.y);
            path.lineTo(point6.x, point6.y);
            path.quadTo(point4.x, point4.y, point1.x, point1.y);
            path.close();

            canvas.drawText("1", point1.x, point1.y, paintBlue);
            canvas.drawText("3", point3.x, point3.y, paintBlue);
            canvas.drawText("4", point4.x, point4.y, paintBlue);
            canvas.drawText("5", point5.x, point5.y, paintBlue);
            canvas.drawText("6", point6.x, point6.y, paintBlue);
            canvas.drawText("8", point8.x, point8.y, paintBlue);
            Log.v("point1", point1.x + " " + point1.y);
            Log.v("point3",point3.x+" "+point3.y);
            Log.v("point4",point4.x+" "+point4.y);
            Log.v("point5",point5.x+" "+point5.y);
            Log.v("point6",point6.x+" "+point6.y);
            Log.v("point8",point8.x+" "+point8.y);
            canvas.drawPath(path, paint);
            flag = false;
        }

    }

    @Override
    public void onDrag(float formX ,float toX, float formY , float toY) {
        float distanceX = toX - formX;
        float distanceY = toY - formY;

        MaxDistance = getWidth()/4;

        float CurrentDistance = (float) Math.sqrt(Math.pow(toX-formX,2)+Math.pow(toY-formY,2));

        if(CurrentDistance>=MaxDistance){
            circularRadiusPaint = circularRadiusMin;
        }else if(CurrentDistance==0){
            circularRadiusPaint = circularRadiusMax;
        }else{
            circularRadiusPaint = circularRadiusMax - CurrentDistance/MaxDistance*(circularRadiusMax-circularRadiusMin);
        }

        this.formX = formX;
        this.formY = formY;

        if(distanceX<0&&distanceY>0){

            point1 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point1.x = -Math.abs(point1.x);
            point1.y = -Math.abs(point1.y);
            point1.x += formX;
            point1.y += formY;

            point3 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point3.x = Math.abs(point3.x);
            point3.y = Math.abs(point3.y);
            point3.x += formX;
            point3.y += formY;

            point4 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point4.x = -Math.abs(point4.x);
            point4.y = -Math.abs(point4.y);
            point4.x += (toX - formX)/2;
            point4.y += (toY - formY)/2;
            point4.x += formX;
            point4.y += formY;

            point5 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point5.x = Math.abs(point5.x);
            point5.y = Math.abs(point5.y);
            point5.x += (toX - formX)/2;
            point5.y += (toY - formY)/2;
            point5.x += formX;
            point5.y += formY;




            flag = true;
        }else if(distanceX>0&&distanceY<0){
            point1 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point1.x = Math.abs(point1.x);
            point1.y = Math.abs(point1.y);
            point1.x += formX;
            point1.y += formY;

            point3 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point3.x = -Math.abs(point3.x);
            point3.y = -Math.abs(point3.y);
            point3.x += formX;
            point3.y += formY;

            point4 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point4.x = Math.abs(point4.x);
            point4.y = Math.abs(point4.y);
            point4.x += (toX - formX)/2;
            point4.y += (toY - formY)/2;
            point4.x += formX;
            point4.y += formY;

            point5 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point5.x = -Math.abs(point5.x);
            point5.y = -Math.abs(point5.y);
            point5.x += (toX - formX)/2;
            point5.y += (toY - formY)/2;
            point5.x += formX;
            point5.y += formY;

            flag = true;
        }else if(distanceX<0&&distanceY<0){
            point1 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point1.x = Math.abs(point1.x);
            point1.y = -Math.abs(point1.y);
            point1.x += formX;
            point1.y += formY;

            point3 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point3.x = -Math.abs(point3.x);
            point3.y = Math.abs(point3.y);
            point3.x += formX;
            point3.y += formY;

            point4 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point4.x = Math.abs(point4.x);
            point4.y = -Math.abs(point4.y);
            point4.x += (toX - formX)/2;
            point4.y += (toY - formY)/2;
            point4.x += formX;
            point4.y += formY;

            point5 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point5.x = -Math.abs(point5.x);
            point5.y = Math.abs(point5.y);
            point5.x += (toX - formX)/2;
            point5.y += (toY - formY)/2;
            point5.x += formX;
            point5.y += formY;

            flag = true;
        }else if(distanceX>0&&distanceY>0){
            point1 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point1.x = -Math.abs(point1.x);
            point1.y = Math.abs(point1.y);
            point1.x += formX;
            point1.y += formY;

            point3 = tanToXY(distanceY / distanceX, circularRadiusPaint);
            point3.x = Math.abs(point3.x);
            point3.y = -Math.abs(point3.y);
            point3.x += formX;
            point3.y += formY;

            point4 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point4.x = -Math.abs(point4.x);
            point4.y = Math.abs(point4.y);
            point4.x += (toX - formX)/2;
            point4.y += (toY - formY)/2;
            point4.x += formX;
            point4.y += formY;

            point5 = tanToXY(distanceY / distanceX, circularRadiusInner);
            point5.x = Math.abs(point5.x);
            point5.y = -Math.abs(point5.y);
            point5.x += (toX - formX)/2;
            point5.y += (toY - formY)/2;
            point5.x += formX;
            point5.y += formY;

            flag = true;
        }else if(distanceX==0){
            int sign;
            if(distanceY>0){
                sign = 1;
            }else{
                sign = -1;
            }

            point1 = new MyPoint();
            point1.x = - sign * circularRadiusPaint;
            point1.y = 0;
            point1.x += formX;
            point1.y += formY;

            point3 = new MyPoint();
            point3.x = sign * circularRadiusPaint;
            point3.y = 0;
            point3.x += formX;
            point3.y += formY;

            point4 = new MyPoint();
            point4.x = - sign * circularRadiusInner;
            point4.y = 0;
            point4.y += (toY - formY)/2;
            point4.x += formX;
            point4.y += formY;

            point5 = new MyPoint();
            point5.x = sign * circularRadiusInner;
            point5.y = 0;
            point5.y += (toY - formY)/2;
            point5.x += formX;
            point5.y += formY;

            flag = true;
        }else if(distanceY==0){
            int sign;
            if(distanceX>0){
                sign = -1;
            }else{
                sign = 1;
            }

            point1 = new MyPoint();
            point1.x = 0;
            point1.y = - sign * circularRadiusPaint;
            point1.x += formX;
            point1.y += formY;

            point3 = new MyPoint();
            point3.x = 0;
            point3.y = sign * circularRadiusPaint;
            point3.x += formX;
            point3.y += formY;

            point4 = new MyPoint();
            point4.x = 0;
            point4.y = - sign * circularRadiusInner;
            point4.y += (toY - formY)/2;
            point4.x += formX;
            point4.y += formY;

            point5 = new MyPoint();
            point5.x = 0;
            point5.y = sign * circularRadiusInner;
            point5.y += (toY - formY)/2;
            point5.x += formX;
            point5.y += formY;


        }

        point6 = new MyPoint();
        point6.x = point1.x + toX - formX;
        point6.y = point1.y + toY - formY;
        
        point8 = new MyPoint();
        point8.x = point3.x + toX - formX;
        point8.y = point3.y + toY - formY;

        flag = true;
        invalidate();

    }

    private MyPoint tanToXY(float value ,float radius){
        MyPoint point = new MyPoint();
        point.y = (float) Math.sqrt(Math.pow(radius,2)/(1+Math.pow(value,2)));
        point.x = point.y*value;
        return point;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public class MyPoint{
        public float x;
        public float y;
    }

}