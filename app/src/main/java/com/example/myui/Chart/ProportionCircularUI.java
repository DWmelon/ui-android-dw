package com.example.myui.Chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myui.R;


/**
 * Created by i on 2016/2/24.
 */
public class ProportionCircularUI extends RelativeLayout {

    Paint paintS;
    Paint paintH;

    int widthH;
    int widthS;

    int disparity = 80;
    int margin = 30;
    ImageView imageView;

    int angle = 360;
    ValueAnimator animator;

    boolean flag = true;

    public ProportionCircularUI(Context context) {
        super(context);
        init();
    }

    public ProportionCircularUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        paintS = new Paint();
        paintS.setStyle(Paint.Style.FILL_AND_STROKE);
        paintS.setColor(0xffffffff);
        paintS.setStrokeWidth(getResources().getDimension(R.dimen.line_width));

        paintH = new Paint();
        paintH.setStyle(Paint.Style.FILL_AND_STROKE);
        paintH.setColor(getResources().getColor(R.color.gray_color));
        paintH.setStrokeWidth(getResources().getDimension(R.dimen.line_width));



        int resImgId = R.drawable.icon_cir_ceping;

        imageView = new ImageView(getContext());
        imageView.setImageResource(resImgId);

        calculateRadius(resImgId);

        LayoutParams imgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(imageView,imgParams);

        initAnimation();

    }

    private void calculateRadius(int resId){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        widthS = bitmap.getWidth()/2+margin;
        widthH = widthS + disparity;
    }

    private void initAnimation(){

        animator =  ValueAnimator.ofInt(360, 0);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                angle = value;
                invalidate();
            }
        });

    }

    public void startAnimation(){
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawCircle(getWidth()/2,getHeight()/2,widthH,paintH);

        canvas.drawCircle(getWidth()/2, getHeight()/2,widthS,paintS);

        RectF rect = new RectF(getWidth()/2-widthH-2, getHeight()/2-widthH-2, getWidth()/2+widthH+2,getHeight()/2+widthH+2);

        if(angle != 0){
            canvas.drawArc(rect, //弧线所使用的矩形区域大小
                    0,  //开始角度
                    angle, //扫过的角度
                    true, //是否使用中心
                    paintS);
        }


    }
}
