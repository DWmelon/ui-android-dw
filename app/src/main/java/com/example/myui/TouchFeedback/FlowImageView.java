package com.example.myui.TouchFeedback;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.example.myui.R;

/**
 * Created by i on 2016/3/25.
 */
public class FlowImageView extends ViewGroup {


    com.example.myui.TouchFeedback.OnDragListener listen;

    Paint paint;
    int num =0;
    TextView mTvNumber;
    int radius;
    float radiusImg ;
    boolean flag=true;

    public FlowImageView(Context context) {
        super(context);
        init();
    }

    public FlowImageView(Context context, AttributeSet attrs) {
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
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        radius = getResources().getDimensionPixelOffset(R.dimen.margin_20);
//        radiusImg = (float) (getWidth()/Math.sqrt(2));
        radiusImg = radius/2;
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        mTvNumber = new TextView(getContext());
        mTvNumber.setTextColor(getResources().getColor(R.color.white));
        mTvNumber.setGravity(Gravity.CENTER);
        mTvNumber.setTextSize(14);
        mTvNumber.setText(99 + "");
        this.addView(mTvNumber, params);

        setOnTouchListener(new OnTouchIconMove());
    }

    public void setListener(com.example.myui.TouchFeedback.OnDragListener listener){
        this.listen = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);

    }

    public class OnTouchIconMove implements View.OnTouchListener{
        float x = 0,y = 0;
        float rowX = 0,rowY = 0;


        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(flag){
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        rowX = v.getX();
                        rowY = v.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.setX(v.getX() + event.getX() - x);
                        v.setY(v.getY() + event.getY() -y);
                        listen.onDrag(rowX + radiusImg, v.getX() + radiusImg,rowY + radiusImg , v.getY()+ radiusImg);
                        break;
                    case MotionEvent.ACTION_UP:
                        flag = false;
                        moveViewAnimation(v,rowX,rowY);
                        break;
                }
            }

            return true;
        }
    }

    private void moveViewAnimation(final View v, final float x, final float y){
        final float differX = x-v.getX();
        final float differY = y-v.getY();
        final float originalX = v.getX();
        final float originalY = v.getY();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(750);
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float num = (float) animation.getAnimatedValue();
                Log.d("mes", num + "");
                Log.d("mes", v.getX() + " " + v.getY());
                v.setX(originalX + differX * num);
                v.setY(originalY + differY * num);
                listen.onDrag(x + radiusImg, v.getX() + radiusImg,y + radiusImg , v.getY()+ radiusImg);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                flag = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.start();


    }
}
