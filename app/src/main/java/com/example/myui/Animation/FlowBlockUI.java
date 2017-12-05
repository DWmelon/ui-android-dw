package com.example.myui.Animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.myui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i on 2016/3/1.
 */
public class FlowBlockUI extends RelativeLayout {

    List<ImageView> imageViews = new ArrayList<>();

    List<Integer> integersX = new ArrayList<>();
    List<Integer> integersY = new ArrayList<>();
    List<Animation> animationOuts = new ArrayList<>();
    List<Animation> animationIns = new ArrayList<>();
    List<Animation> animationRosOuts = new ArrayList<>();
    List<Animation> animationRosIns = new ArrayList<>();
    int radius = 250;

    Context context;

    public FlowBlockUI(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FlowBlockUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init(){

        integersX.add(-1);
        integersX.add((int) -Math.sqrt(2));
        integersX.add(0);
        integersX.add((int) Math.sqrt(2));
        integersX.add(1);
        integersX.add((int) Math.sqrt(2));
        integersX.add(0);
        integersX.add((int) -Math.sqrt(2));

        integersY.add(0);
        integersY.add((int) -Math.sqrt(2));
        integersY.add(-1);
        integersY.add((int) -Math.sqrt(2));
        integersY.add(0);
        integersY.add((int) Math.sqrt(2));
        integersY.add(1);
        integersY.add((int) Math.sqrt(2));

        
        for(int i = 0 ;i<9;i++){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.icon_square);
            LayoutParams paramn = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramn.addRule(RelativeLayout.CENTER_IN_PARENT);
            addView(imageView, paramn);
            imageViews.add(imageView);
        }

        initAnimation();
    }

    public void initAnimation(){

        for(int i = 0;i < 8;i++){
            TranslateAnimation animation = new TranslateAnimation(0,integersX.get(i)*radius,0,integersY.get(i)*radius);
            animation.setDuration(500);
            animation.setInterpolator(new DecelerateInterpolator());
            animationOuts.add(animation);
        }

        for(int i = 0;i < 8;i++){
            TranslateAnimation animation = new TranslateAnimation(integersX.get(i)*radius,0,integersY.get(i)*radius,0);
            animation.setDuration(1000);
            animation.setInterpolator(new AccelerateInterpolator());
            animationIns.add(animation);
        }

        for(int i = 0;i < 8;i++){
            RotateAnimation animation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(250);
            animation.setRepeatCount(2);
            animation.setInterpolator(new DecelerateInterpolator());
            animationRosOuts.add(animation);
        }

        for(int i = 0;i < 8;i++){
            RotateAnimation animation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(250);
            animation.setRepeatCount(4);
            animation.setInterpolator(new AccelerateInterpolator());
            animationRosIns.add(animation);
        }




        for(int i = 0 ;i < 8;i++){

            final int finalI = i;
            animationOuts.get(i).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if(animationOuts.size()>finalI+1){
                        AnimationSet set = new AnimationSet(false);
                        set.addAnimation(animationRosOuts.get(finalI + 1));
                        set.addAnimation(animationOuts.get(finalI + 1));

                        imageViews.get(finalI + 1).startAnimation(set);
//                        imageViews.get(finalI+1).startAnimation(animationRos.get(finalI + 1));
                    }
                    AnimationSet set = new AnimationSet(false);
                    set.addAnimation(animationRosIns.get(finalI));
                    set.addAnimation(animationIns.get(finalI));
                    imageViews.get(finalI).startAnimation(set);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

    }

    public void startAnimations(){
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(animationRosOuts.get(0));
        set.addAnimation(animationOuts.get(0));

        imageViews.get(0).startAnimation(set);

    }
    
    


}
