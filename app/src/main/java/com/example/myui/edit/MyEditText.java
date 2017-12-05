package com.example.myui.edit;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myui.R;

/**
 * Created by i on 2016/7/2.
 */

public class MyEditText extends RelativeLayout{

    private ImageView circularImg;
    private EditText editText;
    private ImageView searchImg;
    private ScaleAnimation animOpen;
    private ScaleAnimation animClose;
    private boolean isOpen = false;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){

        circularImg = new ImageView(getContext());
        circularImg.setImageResource(R.drawable.btn_search_circular);
        LayoutParams params1 = new LayoutParams(getResources().getDimensionPixelOffset(R.dimen.margin_35),getResources().getDimensionPixelOffset(R.dimen.margin_35));
        params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params1.addRule(RelativeLayout.CENTER_VERTICAL);
        params1.setMargins(0,0,getResources().getDimensionPixelOffset(R.dimen.margin_10),0);
        addView(circularImg,params1);

        editText = new EditText(getContext());
        editText.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        editText.setPadding(getResources().getDimensionPixelOffset(R.dimen.margin_10),0,0,0);
        editText.setTextColor(getResources().getColor(R.color.white));
        LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,getResources().getDimensionPixelOffset(R.dimen.margin_35));
        addView(editText,params2);
        editText.setVisibility(View.GONE);

        searchImg = new ImageView(getContext());
        LayoutParams params3 = new LayoutParams(getResources().getDimensionPixelOffset(R.dimen.margin_35),getResources().getDimensionPixelOffset(R.dimen.margin_35));
        params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params3.addRule(RelativeLayout.CENTER_VERTICAL);
        params3.setMargins(0,0,getResources().getDimensionPixelOffset(R.dimen.margin_10),0);
        addView(searchImg,params3);
        int padding = getResources().getDimensionPixelOffset(R.dimen.margin_5);
        searchImg.setPadding(padding,padding,padding,padding);
        searchImg.setImageResource(android.R.drawable.ic_menu_search);
        searchImg.setScaleType(ImageView.ScaleType.FIT_CENTER);

        initAnim();
        initListener();
    }

    private void initAnim(){
        animOpen = new ScaleAnimation(1.0f,30f,1.0f,10f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animOpen.setDuration(2000);
        animOpen.setInterpolator(new AccelerateInterpolator());
        animOpen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                editText.setVisibility(View.VISIBLE);
                showInputKeyBoard();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animClose = new ScaleAnimation(30f,1.0f,10f,1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animClose.setDuration(1000);
        animClose.setInterpolator(new AccelerateInterpolator());
        animClose.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editText.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void initListener(){
        searchImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen){
                    openEdit();
                }
            }
        });
    }



    public void openEdit(){
        circularImg.startAnimation(animOpen);
        isOpen = true;
    }

    public void closeEdit(){
        circularImg.startAnimation(animClose);
    }

    private void showInputKeyBoard(){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if(!isInit){
//            init();
//            isInit = true;
//        }

    }
}
