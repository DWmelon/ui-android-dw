package com.example.myui.Animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myui.R;
import com.example.myui.Util;


/**
 * Created by melon on 2016/1/14.
 */
public class RotateCircularUI extends RelativeLayout {

    Context mContext;

    ImageView ima;
    int width;
    RotateCircularLineUI circular1;
    RotateCircularLineUI circular2;

    TextView textView;
    int mNumber = 500;
    int mNumberSize = 50;

    String mRightTip = "人";
    int nRightTipSize = 25;

    int mDuration = 3000;
    ValueAnimator mAnimationCount;
    Animation mAnimaitionTra;
    Animation mAnimaitionSca1;
    Animation mAnimaitionSca2;

    Paint mPaint1;
    float magin1;
    float magin2;

    double windowWidth;
    double windowHeight;

    public RotateCircularUI(Context context) {
        super(context);
        this.mContext = context;
        initPaint();
        init();
    }

    public RotateCircularUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        initPaint();
        init();
    }

    private void initPaint(){

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.checkloading);
        width = bitmap.getWidth();

        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(R.color.gray_color));
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(getResources().getDimension(R.dimen.margin_2));

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void init(){

        magin1 = getResources().getDimension(R.dimen.margin_15);
        magin2 = getResources().getDimension(R.dimen.margin_25);
        windowWidth = (width+magin2*2)*1.1;
        windowHeight = (width+magin2*2)*1.1;

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.CENTER_IN_PARENT);

        ima = new ImageView(mContext);
        ima.setImageDrawable(getResources().getDrawable(R.drawable.checkloading));

        addView(ima, lp1);

        RelativeLayout.LayoutParams vlp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        circular1 = new RotateCircularLineUI(mContext,getResources().getColor(R.color.loading_ui_close_color),width/2+magin1);
        vlp1.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(circular1, vlp1);

        RelativeLayout.LayoutParams vlp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        circular2 = new RotateCircularLineUI(mContext,getResources().getColor(R.color.loading_ui_far_color),width/2+magin2);
        vlp2.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(circular2, vlp1);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.CENTER_IN_PARENT);

        RelativeLayout layout = new RelativeLayout(mContext);
        layout.setLayoutParams(lp2);

        addView(layout, lp2);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        textView = new TextView(mContext);
        textView.setText(mNumber + "");
        textView.setTextColor(getResources().getColor(R.color.loading_ui_text_color));
        textView.setTextSize(mNumberSize);
        textView.setLayoutParams(lp3);
        textView.setId(Util.generateViewId());
        layout.addView(textView, lp3);

        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp4.addRule(RelativeLayout.RIGHT_OF, textView.getId());
        lp4.addRule(RelativeLayout.ALIGN_BASELINE, textView.getId());

        TextView textView1 = new TextView(mContext);
        textView1.setText(mRightTip);
        textView1.setTextColor(getResources().getColor(R.color.loading_ui_text_color));
        textView1.setTextSize(15);
        textView1.setLayoutParams(lp4);

        layout.addView(textView1, lp4);



    }

    private void preformAnimation(){

        //数字动画
        mAnimationCount = ValueAnimator.ofInt(0, mNumber);

        mAnimationCount.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int num = (int) animation.getAnimatedValue();
                textView.setText(num + "");
            }
        });
        mAnimationCount.setDuration(mDuration);
        mAnimationCount.setInterpolator(new DecelerateInterpolator());

        //背景图片
        mAnimaitionTra = new RotateAnimation(0,360,ima.getWidth()/2,ima.getHeight()/2);
        mAnimaitionTra.setDuration(mDuration/2);
        mAnimaitionTra.setRepeatCount(1);
        mAnimaitionTra.setInterpolator(new LinearInterpolator());

        //小圆
        mAnimaitionSca1 = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimaitionSca1.setDuration(mDuration);
        mAnimaitionSca1.setInterpolator(new BounceInterpolator());

        //大圆
        mAnimaitionSca2 = new ScaleAnimation(1.0f, 1.20f, 1.0f, 1.20f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimaitionSca2.setDuration(mDuration);
        mAnimaitionSca2.setInterpolator(new BounceInterpolator());

    }

    public void show(){
        preformAnimation();
        mAnimationCount.start();
        ima.startAnimation(mAnimaitionTra);
        circular1.startAnimation(mAnimaitionSca1);
        circular2.startAnimation(mAnimaitionSca2);
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    public int getmNumberSize() {
        return mNumberSize;
    }

    public void setmNumberSize(int mNumberSize) {
        this.mNumberSize = mNumberSize;
    }

    public String getmRightTip() {
        return mRightTip;
    }

    public void setmRightTip(String mRightTip) {
        this.mRightTip = mRightTip;
    }

    public int getnRightTipSize() {
        return nRightTipSize;
    }

    public void setnRightTipSize(int nRightTipSize) {
        this.nRightTipSize = nRightTipSize;
    }
}
