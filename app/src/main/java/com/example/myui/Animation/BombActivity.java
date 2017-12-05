package com.example.myui.Animation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.myui.R;

/**
 * Created by i on 2016/3/21.
 */
public class BombActivity extends Activity {

    Button btn;
    BombUI animationUI;



    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomb);

        animationUI = (BombUI)findViewById(R.id.myanimationui);


        // animationUI.init(getHoleWidth(),getHoleHeight());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationUI.init();
            }
        },150);


        btn = (Button)findViewById(R.id.start_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    animationUI.startAnimation();
                }

            }
        });
    }


}
