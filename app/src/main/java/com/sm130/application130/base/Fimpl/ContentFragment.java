package com.sm130.application130.base.Fimpl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.sm130.application130.R;
import com.sm130.application130.base.BaseFrament;
import com.sm130.application130.base.BasePager;
import com.sm130.application130.base.impl.GovAffairsPager;
import com.sm130.application130.base.impl.HomePager;
import com.sm130.application130.base.impl.NewsPager;
import com.sm130.application130.base.impl.SettingPager;
import com.sm130.application130.base.impl.SmartServicePager;

import java.util.ArrayList;

public class ContentFragment extends BaseFrament {
    private ViewPager viewPager;
    private ArrayList<BasePager> mPagers;
    private RadioGroup rgGroup;


    @Override
    public void initData() {
        //        初始化数据
        mPagers = new ArrayList<>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsPager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));

        viewPager.setAdapter(new ContentAdapter());


    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.content_main, null);
        viewPager = view.findViewById(R.id.view_page);
        rgGroup = view.findViewById(R.id.rg_group);
        return view;
    }
    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = mPagers.get(position);
//            获取当前页面对象的布局
            View view = basePager.mRootView;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
