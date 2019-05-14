package com.sm130.application130.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm130.application130.R;

import org.w3c.dom.Text;

public class BasePager {

    public Activity mActivity;

    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent;
    //当前页面的布局对象
    public View mRootView;

    public BasePager(Activity Activity) {
        this.mActivity = Activity;
        mRootView = initView();
    }

//    初始化布局
    public View initView(){
        View view = View.inflate(mActivity, R.layout.base_page,null);
        tvTitle = view.findViewById(R.id.tv_title);
        btnMenu = view.findViewById(R.id.btn_menu);
        flContent = view.findViewById(R.id.fl_content);
        return view;
    }

//    初始化数据
    public void initData(){

    }
}
