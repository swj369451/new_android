package com.sm130.application130.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm130.application130.MyFragmentManager;
import com.sm130.application130.R;
import com.sm130.application130.beijing_domain.Beijin;
import com.sm130.application130.beijing_domain.Data;
import com.sm130.application130.domain.Children;
import com.sm130.application130.domain.Interface;
import com.sm130.application130.beijing_domain.JsonRootBean;
import com.sm130.application130.global.GlobalConstants;
import com.sm130.application130.utils.URLUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    public static String TITLE = "title";
    public static String CONTENT ="content";
    private List<Children> data;
    private Interface in;
    MyFragmentManager myFragmentManager;
    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

//        获取数据
        data = new ArrayList<>();
        data = (List<Children>) getArguments().getSerializable("childrenList");
        in= (Interface) getArguments().getSerializable("Interface");
        myFragmentManager = (MyFragmentManager) getArguments().getSerializable("fragmentManager");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.viewpage_tablayout, container, false);
//        视图内的元素
        TextView textView = root.findViewById(R.id.tv_title);
        ImageButton imageButton = root.findViewById(R.id.btn_menu);


//        设置值
        textView.setText(in.getTitle());

        if(in.getCt()==true){
            imageButton.setVisibility(View.VISIBLE);
        }else {
            imageButton.setVisibility(View.INVISIBLE);
        }
        ViewPager viewPager = root.findViewById(R.id.news_viewpage);
        TabLayout tabLayout = root.findViewById(R.id.news_tablayout);

//        final List<DemoFragment> fragment1s = new ArrayList<>();
        final List<ViewPageFragment> viewPageFragments = new ArrayList<>();

//        设置数据
        for (Children c:data){
            tabLayout.addTab(tabLayout.newTab().setText(c.getTitle()));

//            fragment1s.add(DemoFragment.newInstance(c.getUrl()));

//            通过http请求获取数据
            String url = GlobalConstants.TOMCAT_URL + c.getUrl();
            JsonRootBean jsonRootBean = (JsonRootBean) URLUtils.getInstentForUrl(url, JsonRootBean.class);
            viewPageFragments.add(ViewPageFragment.newInstance(jsonRootBean.getData()));

        }

        viewPager.setAdapter(new FragmentPagerAdapter(myFragmentManager.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
//                return fragment1s.get(i);
                return viewPageFragments.get(i);
            }

            @Override
            public int getCount() {
//                return fragment1s.size();
                return viewPageFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getTitle();
            }
        });


        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

    public static NewsFragment newInstance(List<Children> childrenList, MyFragmentManager myFragmentManager, Interface in){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("childrenList", (Serializable) childrenList);
        bundle.putSerializable("fragmentManager", (Serializable) myFragmentManager);
        bundle.putSerializable("Interface", (Serializable) in);
        fragment.setArguments(bundle);
        return fragment;
    }
}
