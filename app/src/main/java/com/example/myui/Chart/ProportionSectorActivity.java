package com.example.myui.Chart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myui.R;

/**
 * Created by i on 2016/3/23.
 */
public class ProportionSectorActivity extends Activity {

    ProportionSectorUI ui;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proportion_sector);
        initView();
        initData();

    }

    private void initView(){
        ui = (ProportionSectorUI) findViewById(R.id.proportion_sector_ui);
        btn = (Button)findViewById(R.id.start_btn);
    }

    private void initData(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
