package com.example.myui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.myui.Animation.BombActivity;
import com.example.myui.Animation.FlowBlockActivity;
import com.example.myui.Animation.RotateCircularActivity;
import com.example.myui.Chart.ProportionCircularActivity;
import com.example.myui.Chart.ProportionSectorActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView mGridView;

    //消息红按钮
    View mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        mGridView = (GridView)findViewById(R.id.gv_my_function);
        mIcon = findViewById(R.id.icon_yellow);

    }

    private void initData(){
        FunctionChooseAdapter adapter = new FunctionChooseAdapter(this);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        if(position == 0){
            i = new Intent(MainActivity.this, BombActivity.class);
            startActivity(i);
        }else if(position == 1){
            i = new Intent(MainActivity.this, FlowBlockActivity.class);
            startActivity(i);
        }else if(position == 2){
            i = new Intent(MainActivity.this, ProportionCircularActivity.class);
            startActivity(i);
        }else if(position == 3){
            i = new Intent(MainActivity.this, RotateCircularActivity.class);
            startActivity(i);
        }else if(position == 4){
            i = new Intent(MainActivity.this, ProportionSectorActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
