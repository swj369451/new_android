package com.sm130.application130;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private View view1,view2,view3,view4,view5;
    private ViewPager viewPager;

    private TextView biaoti;

    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Start 侧边栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//         侧边栏
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





//        StartViewPage
        viewPager= (ViewPager)findViewById(R.id.view_page);

        LayoutInflater inflater = getLayoutInflater();

        view1 = inflater.inflate(R.layout.base_page,null);
        view2 = inflater.inflate(R.layout.base_page1,null);
        view3 = inflater.inflate(R.layout.base_page2,null);
        view4 = inflater.inflate(R.layout.base_page3,null);
        view5 = inflater.inflate(R.layout.base_page4,null);


        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
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
//        EndViewPage

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
//        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void home(View view) {
        biaoti = findViewById(R.id.biaoti);
        biaoti.setText("首页");
        viewPager.setCurrentItem(0);
    }

    public void news(View view) {
        biaoti = findViewById(R.id.biaoti);
        biaoti.setText("新闻中心");
        viewPager.setCurrentItem(1);
    }
    public void smart(View view) {
        biaoti = findViewById(R.id.biaoti);
        biaoti.setText("智慧服务");
        viewPager.setCurrentItem(2);
    }

    public void gov(View view) {
        biaoti = findViewById(R.id.biaoti);
        biaoti.setText("政务");
        viewPager.setCurrentItem(3);
    }

    public void set(View view) {
        biaoti = findViewById(R.id.biaoti);
        biaoti.setText("设置");
        viewPager.setCurrentItem(4);
    }




}

