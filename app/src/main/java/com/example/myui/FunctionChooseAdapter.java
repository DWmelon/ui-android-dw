package com.example.myui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by i on 2016/3/21.
 */
public class FunctionChooseAdapter extends BaseAdapter {

    Context context;

    String[] strs = {"bomb sakalaka","回旋镖","圆形进度条","旋转圆形计数","比例扇形"};
    public FunctionChooseAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public Object getItem(int position) {
        return strs[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_function_choose,parent,false);
            holder = new Holder();
            holder.icon = (ImageView)convertView.findViewById(R.id.iv_grid_icon);
            holder.tip = (TextView)convertView.findViewById(R.id.tv_grid_tip);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }

        performData(holder,position);

        return convertView;
    }

    private void performData(Holder holder,int position){
        holder.tip.setText(strs[position]);
        holder.icon.setImageResource(R.drawable.icon_love);
    }

    public class Holder{
        ImageView icon;
        TextView tip;
    }
}
