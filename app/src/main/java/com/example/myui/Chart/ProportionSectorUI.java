package com.example.myui.Chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.myui.R;


/**
 * Created by i on 2016/2/19.
 */
public class ProportionSectorUI extends View {

    Context context;
    Paint paint1;
    Paint paint2;


    int interval = 45;//两个扇形的间距

    int disparity = 30;//两个扇形的大小差距

    int angle = 110;//左边扇形的角度


    public ProportionSectorUI(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProportionSectorUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }


    public void setCircularColor(int firstColor,int SecColor){
        paint1.setColor(firstColor);
        paint2.setColor(SecColor);
    }

    public void setIntervalValue(int size){
        interval = size;
    }

    public void setAngle(int angle){
        this.angle = angle;
    }

    public void setdisparity(int disparity){
        this.disparity = disparity;
    }

    public void init(){

        paint1 = new Paint();
        paint1.setColor(getResources().getColor(R.color.colorPrimary));
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setStrokeWidth(getResources().getDimension(R.dimen.margin_2));

        paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.primary_yellow_color));
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStrokeWidth(getResources().getDimension(R.dimen.margin_2));

    }



    @Override
    protected void onDraw(Canvas canvas) {


        float width = getWidth()-getPaddingLeft()-getPaddingRight();

        canvas.save();

        int adjustment = disparity/2+getPaddingLeft();//调整视图位置

        RectF rect1 = new RectF(adjustment, getHeight()/2-(width-interval)/2,adjustment+ width-interval , getHeight()/2-(width-interval)/2+width-interval);
        canvas.drawArc(rect1,//弧线所使用的矩形区域大小  
                180,//开始角度  
                angle,//扫过的角度  
                true,//是否使用中心  
                paint1);


        RectF rect2 = new RectF(adjustment+interval+disparity, getHeight()/2-(width-interval)/2+disparity,adjustment+ width-disparity, getHeight()/2-(width-interval)/2+width-interval-disparity);
        canvas.drawArc(rect2,//弧线所使用的矩形区域大小  
                0,//开始角度  
                angle - 180,//扫过的角度  
                true,//是否使用中心  
                paint2);

        canvas.restore();



    }
}
