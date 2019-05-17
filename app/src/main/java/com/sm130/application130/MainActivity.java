package com.sm130.application130;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sm130.application130.controller.GetMassage;
import com.sm130.application130.domain.Data;
import com.sm130.application130.domain.JsonRootBean;

import com.sm130.application130.viewpage.NoScrollViewPager;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private View view1,view2,view3,view4,view5;
    private View view6,view7,view8;
    private NoScrollViewPager viewPager;

    private TextView biaoti;

    private List<View> viewList;

    private JsonRootBean jsonRootBean;
    private LayoutInflater inflater;
//    侧边栏自定义
    NavigationView navigationView;
    DrawerLayout drawer;

//    页面数据
    FrameLayout frameLayoutContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonRootBean=GetMassage.getHome();
//        Start 侧边栏
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        找到侧边栏
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

//        页面数据
//        frameLayoutContent = findViewById(R.id.fl_content);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//        fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1,Fragement1.newInstance("hello world"),"f1")
                .commit();






//         侧边栏
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//       初始化数据
        initData();


//        StartViewPage
        viewPage();
//        EndViewPagel'd



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
                    // Handle the camera action
//                    关闭抽屉
                    drawer.closeDrawers();
                    System.out.println("1");
                }else  if (id == 1) {
                    // Handle the camera action
                    drawer.closeDrawers();
//                    biaoti = findViewById(R.id.biaoti);
//                    biaoti.setText("专题");
                    viewPager.setCurrentItem(5);
                }else if (id == 2) {
                    // Handle the camera action
                    drawer.closeDrawers();
//                    biaoti = findViewById(R.id.biaoti);
//                    biaoti.setText("组图");
                    viewPager.setCurrentItem(6);
                }else if (id == 3) {
                    drawer.closeDrawers();
//                    biaoti = findViewById(R.id.biaoti);
//                    biaoti.setText("互动");
                    viewPager.setCurrentItem(7);
                }
                    return true;
            }
        });
    }

    private void viewPage() {
        viewPager= findViewById(R.id.view_page);

        LayoutInflater inflater = getLayoutInflater();

        view1 = inflater.inflate(R.layout.base_page,null);
        view2 = inflater.inflate(R.layout.base_page1,null);
        view3 = inflater.inflate(R.layout.base_page2,null);
        view4 = inflater.inflate(R.layout.base_page3,null);
        view5 = inflater.inflate(R.layout.base_page4,null);
        view6 = inflater.inflate(R.layout.base_page5,null);
        view7 = inflater.inflate(R.layout.base_page6,null);
        view8 = inflater.inflate(R.layout.base_page7,null);




        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);
        viewList.add(view7);
        viewList.add(view8);



        PagerAdapter pagerAdapter = new PagerAdapter() {




            @Override

            public boolean isViewFromObject(View arg0, Object arg1) {

                // TODO Auto-generated method stub

                return arg0 == arg1;

            }



            @Override

            public int getCount() {

                // TODO Auto-generated method stub

                return viewList.size();

            }



            @Override

            public void destroyItem(ViewGroup container, int position,

                                    Object object) {

                // TODO Auto-generated method stub

                container.removeView(viewList.get(position));

            }



            @Override

            public Object instantiateItem(ViewGroup container, int position) {

                // TODO Auto-generated method stub

                container.addView(viewList.get(position));





                return viewList.get(position);

            }

        };

        viewPager.setAdapter(pagerAdapter);
    }


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


    //    Start 侧边栏没有影响
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
//        return true;
//    }

//    End 侧边栏没有影响

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
////        // Handle navigation view item clicks here.
////        int id = item.getItemId();
////
////        if (id == R.id.nav_camera) {
////            // Handle the camera action
////            System.out.println("1");
////        } else if (id == R.id.nav_gallery) {
////            System.out.println("2");
////        } else if (id == R.id.nav_slideshow) {
////            System.out.println("3");
////        } else if (id == R.id.nav_manage) {
////            System.out.println("4");
////        }
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public void home(View view) {
//        biaoti = findViewById(R.id.biaoti);
//        biaoti.setText("首页");
        viewPager.setCurrentItem(0);
    }

    public void news(View view) {


//        biaoti = findViewById(R.id.biaoti);
//        biaoti.setText("新闻中心");
        viewPager.setCurrentItem(1);
//        getMassage.getData();
    }
    public void smart(View view) {
//        biaoti = findViewById(R.id.biaoti);
//        biaoti.setText("智慧服务");
        viewPager.setCurrentItem(2);
    }

    public void gov(View view) {
//        biaoti = findViewById(R.id.biaoti);
//        biaoti.setText("政务");
        viewPager.setCurrentItem(3);
    }

    public void set(View view) {

//        biaoti = findViewById(R.id.biaoti);
//        biaoti.setText("设置");
        viewPager.setCurrentItem(4);
    }


    public void draw(View view) {
        drawer.openDrawer(Gravity.START);
    }
}

