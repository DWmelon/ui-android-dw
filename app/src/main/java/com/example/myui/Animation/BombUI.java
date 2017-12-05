package com.example.myui.Animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.myui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by i on 2016/2/17.
 */
public class BombUI extends RelativeLayout {

    Context context;

    List<ValueAnimator> animators = new ArrayList<>();
    AnimationSet animationSet = new AnimationSet(true);


    public BombUI(Context context) {
        super(context);
        this.context =context;
    }

    public BombUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
    }

    public void init(){

        animationSet.setInterpolator(new DecelerateAccelerateInterpolator());
        int startWidth = getWidth()/2;
        int startHeight = getHeight()-200;
        float x1Distence = 100;

        Random random = new Random();
        for(int i = 0;i<100;i++){
            float k = random.nextFloat();
            boolean direction = random.nextBoolean();
            float x1 = direction?x1Distence*k:-x1Distence*k;
            final ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.icon_love);
            addView(imageView);
            imageView.setX(startWidth + x1);
            imageView.setY(startHeight);
            imageView.setTag(i);

            boolean flag = random.nextBoolean();
            final Point point = new Point(startWidth + x1,startHeight,flag);

            ValueAnimator animation = ValueAnimator.ofFloat(startWidth + x1, flag ? getWidth() : 0);
            animation.setDuration(1000);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    imageView.setX((float) animation.getAnimatedValue());
                    imageView.setY((float) point.getY((float) animation.getAnimatedValue()));
                }
            });
            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (getHandler() != null) {
                        getHandler().postDelayed(runnable, 100);
                    }

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (this != null) {
                        removeView(imageView);
                    }

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            //animation.setInterpolator(new DecelerateAccelerateInterpolator());
            animators.add(animation);
        }
    }

    public void startAnimation(){
//        for(int i=0;i<animators.size();i=i+2){
//            animators.get(i).start();
//        }
        animators.get(0).start();
        animators.remove(0);
    }



    Runnable runnable = new Runnable() {
        public void run() {

            Random random = new Random();
            if(animators.size()>0){
                int index = random.nextInt(animators.size());
                animators.get(index).start();
                animators.remove(index);
            }

        }
    };


    public class Point {


        private double a;
        private float x1;
        private float x2;

        private float y;

        Random random;

        public Point(float x,float y,boolean direction) {
            this.y = y;
            random = new Random();
            //boolean flag = random.nextBoolean();
            x1 = x;
            x2 = x1 + (direction?random.nextFloat()*(getWidth()/2-50)+50:-random.nextFloat()*(getWidth()/2-50)-50);
//            while(a>-0.04515){
//                a = -random.nextFloat()*0.04520;
//            }
            a = -0.0164;

        }


        public double getY(float x) {
            return -a*(x-x1)*(x-x2)+y;
        }

    }


    public class DecelerateAccelerateInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float input) {
            float result;
            if (input <= 0.5) {
                result = (float) (Math.sin(Math.PI * input)) / 2;
            } else {
                result = (float) (2 - Math.sin(Math.PI * input)) / 2;
            }
            return result;
        }

    }
}
