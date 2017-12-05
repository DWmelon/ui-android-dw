package com.example.myui.Search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by i on 2016/5/4.
 */
public class SearchRelativeLayout extends RelativeLayout {

    public SearchRelativeLayout(Context context) {
        super(context);
    }

    public SearchRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

}
