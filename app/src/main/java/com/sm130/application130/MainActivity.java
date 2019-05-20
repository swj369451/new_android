package com.sm130.application130;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
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
    ZutuFragment zutuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        jsonRootBean=GetMassage.getHome();
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
        fragment1 = Fragment1.newInstance(new Interface("智慧北京",false));
        fragment2 = Fragment1.newInstance(new Interface("新闻",true));
        fragment3 = Fragment1.newInstance(new Interface("生活",true));
        fragment4 = Fragment1.newInstance(new Interface("人口管理",true));
        fragment5 = Fragment1.newInstance(new Interface("设置",false));

//        新闻碎片
        newsFragment = NewsFragment.newInstance(jsonRootBean.getData().get(0).getChildren(),new Interface("新闻",true));

//        组图的碎片
        zutuFragment = ZutuFragment.newInstance(zutuData.getData(),1);


//        fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1,fragment1,"f0")
                .addToBackStack(Fragment1.class.getSimpleName())
                .commit();
//       初始化数据
        initData();


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
    

    private void initData() {
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
            navigationView.getMenu().add(1,i,1,d.getTitle());
            navigationView.getMenu().findItem(i).setIcon(R.drawable.back);
            i++;
        }

//      添加点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == 0) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container1,newsFragment,"f2")
                            .commit();
                    drawer.closeDrawers();
//                    System.out.println("1");
                }else  if (id == 1) {
                    // Handle the camera action
                    drawer.closeDrawers();
//                    biaoti = findViewById(R.id.biaoti);
//                    biaoti.setText("专题");
//                    viewPager.setCurrentItem(5);
                }else if (id == 2) {
                    // Handle the camera action
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container1,zutuFragment,"f6")
                            .commit();
                    drawer.closeDrawers();
//                    biaoti = findViewById(R.id.biaoti);
//                    biaoti.setText("组图");
//                    viewPager.setCurrentItem(6);
                }else if (id == 3) {
                    drawer.closeDrawers();
//                    biaoti = findViewById(R.id.biaoti);
//                    biaoti.setText("互动");
//                    viewPager.setCurrentItem(7);
                }
                    return true;
            }
        });
    }


    public void home(View view) {
//        biaoti = findViewById(R.id.biaoti);
//        biaoti.setText("首页");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1,fragment1,"f0")
                .commit();
    }

    public void news(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1,newsFragment,"f2")
                .commit();

    }
    public void smart(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1,fragment3,"f3")
                .commit();
    }

    public void gov(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1,fragment4,"f4")
                .commit();
    }

    public void set(View view) {

       getSupportFragmentManager().beginTransaction()
               .replace(R.id.container1,fragment5,"f5")
               .commit();
    }


    public void draw(View view) {
        drawer.openDrawer(Gravity.START);
    }
    public void changeRecycleView(View view) {

        if(COL){
            zutuFragment = ZutuFragment.newInstance(zutuData.getData(),2);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,zutuFragment,"f6")
                    .commit();
            drawer.closeDrawers();
            COL=false;
        }else{
            zutuFragment = ZutuFragment.newInstance(zutuData.getData(),1);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,zutuFragment,"f6")
                    .commit();
            drawer.closeDrawers();
            COL=true;
        }




    }
}

