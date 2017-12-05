package com.example.myui.Animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myui.R;

/**
 * Created by i on 2016/3/22.
 */
public class FlowBlockActivity extends Activity {

    FlowBlockUI ui;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_block);

        initView();
        initData();
    }

    private void initView(){
        ui = (FlowBlockUI)findViewById(R.id.compare_load_ui);
        btn = (Button)findViewById(R.id.start_btn);
    }

    private void initData(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ui.startAnimations();
            }
        });
    }
}
