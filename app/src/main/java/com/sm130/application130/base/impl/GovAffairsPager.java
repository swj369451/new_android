package com.sm130.application130.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.sm130.application130.base.BasePager;

/**
 * 首页
 */
public class GovAffairsPager extends BasePager {

    public GovAffairsPager(Activity Activity) {
        super(Activity);
    }


    @Override
    public void initData() {
//        要给帧布局填充布局对象
        TextView textView = new TextView(mActivity);
        textView.setText("政服");
        textView.setTextColor(Color.red(1));
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        flContent.addView(textView);


    }
}
