package com.sm130.application130;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sm130.application130.controller.GetMassage;
import com.sm130.application130.domain.Data;
import com.sm130.application130.domain.Interface;
import com.sm130.application130.domain.JsonRootBean;

import com.sm130.application130.fragment.Fragment1;
import com.sm130.application130.fragment.NewsFragment;
import com.sm130.application130.fragment.ZutuFragment;
import com.sm130.application130.global.GlobalConstants;
import com.sm130.application130.utils.URLUtils;


import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Boolean COL = true;
    private JsonRootBean jsonRootBean;
    private com.sm130.application130.zutu_domain.JsonRootBean zutuData;
    private SwipeRefreshLayout refreshLayout;
//    侧边栏自定义
    NavigationView navigationView;
    DrawerLayout drawer;

//    公共碎片
    Fragment1 fragment1,fragment2,fragment3,fragment4,fragment5;
    NewsFragment newsFragment;
    ZutuFragment zutuFragment,zutuFragment2;


    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        使用OkHttp发送get请求
        jsonRootBean= (JsonRootBean) URLUtils.getInstentForUrl(GlobalConstants.CATEGORY_URL,JsonRootBean.class);
        zutuData = (com.sm130.application130.zutu_domain.JsonRootBean)
                URLUtils.getInstentForUrl(GlobalConstants.TOMCAT_URL+"/photos/photos_1.json", com.sm130.application130.zutu_domain.JsonRootBean.class);

//        找到侧边栏
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

//        下拉刷新
        refreshLayout = findViewById(R.id.refreshLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//       首页碎片
        fragment1 = Fragment1.newInstance(new Interface("智慧北京",false,"首页"));
        fragment2 = Fragment1.newInstance(new Interface("新闻",true,""));
        fragment3 = Fragment1.newInstance(new Interface("生活",true,"智慧服务"));
        fragment4 = Fragment1.newInstance(new Interface("人口管理",true,"政务"));
        fragment5 = Fragment1.newInstance(new Interface("设置",false,"设置"));

//        新闻碎片
        newsFragment = NewsFragment.newInstance(jsonRootBean.getData().get(0).getChildren(),new Interface("新闻",true,""));
//        组图的碎片
        zutuFragment = ZutuFragment.newInstance(zutuData.getData(),1);
        zutuFragment2 = ZutuFragment.newInstance(zutuData.getData(),2);

//        预先加载fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1,fragment1)
                .add(R.id.container1,fragment2)
                .add(R.id.container1,fragment3)
                .add(R.id.container1,fragment4)
                .add(R.id.container1,fragment5)
                .add(R.id.container1,newsFragment)
                .add(R.id.container1,zutuFragment)
                .add(R.id.container1,zutuFragment2)
//                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment5)
                .hide(newsFragment)
                .hide(fragment4)
                .hide(zutuFragment)
                .hide(zutuFragment2)
                .commit()
        ;

//        当前的fragment
        currentFragment = new Fragment();
        currentFragment=fragment1;
//       初始化数据
        initData();

//        下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

    }
    


    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initData() {
//      初始化侧边栏
//        设置背景颜色
        navigationView.setBackgroundColor(Color.BLACK);

//        设置按钮风格
        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.cbl);
        navigationView.setItemTextColor(csl);
//        为侧边栏添加数据
        List<Data> data = jsonRootBean.getData();
        int i = 0;
        for(Data d:data){
            navigationView.getMenu().add(1,i,1,d.getTitle()).setIcon(R.drawable.menu_arr_normal);
            i++;
        }

//      添加点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == 0) {
                    getSupportFragmentManager().beginTransaction()
                    .hide(currentFragment)
                    .show(newsFragment)
                    .commit();
                     currentFragment=newsFragment;
                     drawer.closeDrawers();
                }else  if (id == 1) {
                    drawer.closeDrawers();
                    drawer.closeDrawers();
                }else if (id == 2) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(currentFragment)
                            .show(zutuFragment)
                            .commit();
                    currentFragment=zutuFragment;
                    drawer.closeDrawers();
                }else if (id == 3) {
                    drawer.closeDrawers();

                }
                    return true;
            }
        });
    }

//点击事件
    public void home(View view) {
        getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(fragment1)
                .commit();
        currentFragment=fragment1;
    }

    public void news(View view) {
        getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(newsFragment)
                .commit();
        currentFragment=newsFragment;
    }
    public void smart(View view) {
        getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(fragment3)
                .commit();
        currentFragment=fragment3;
    }

    public void gov(View view) {
        getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(fragment4)
                .commit();
        currentFragment=fragment4;
    }

    public void set(View view) {

        getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(fragment5)
                .commit();
        currentFragment=fragment5;
    }


    public void draw(View view) {
        drawer.openDrawer(Gravity.START);
    }
    public void changeRecycleView(View view) {
//        判断用户选择单列还是双列
        if(COL){
            getSupportFragmentManager().beginTransaction()
                    .hide(currentFragment)
                    .show(zutuFragment2)
                    .commit();
            currentFragment=zutuFragment2;
            drawer.closeDrawers();
            COL=false;
        }else{
            getSupportFragmentManager().beginTransaction()
                    .hide(currentFragment)
                    .show(zutuFragment)
                    .commit();
            currentFragment=zutuFragment;
            drawer.closeDrawers();
            COL=true;
        }
    }
}

