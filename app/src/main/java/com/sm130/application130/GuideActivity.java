package com.sm130.application130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sm130.application130.utils.PrefUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager mViewPage;
    //小红点
    private ImageView ivRedpoint;
    private Button btnStart;
    private  int mPointDis;
//    引导图片的id组
    private  int[] mImageIds = new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };
    LinearLayout llContainer;

//    imageview的集合
    private ArrayList<ImageView> imageViewArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        去掉标题必须在setContentView之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        mViewPage = (ViewPager)findViewById(R.id.vp_guide);
        llContainer =(LinearLayout) findViewById(R.id.ll_container);
        ivRedpoint = (ImageView) findViewById(R.id.iv_red_point);
        btnStart = findViewById(R.id.btn_start);

//        先初始化数据
        initData();
//        设置数据
        mViewPage.setAdapter(new GuideAdapter());

        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//            当页面滑动过程中的回调
//                更新小红点的位置
                int leftMargin = (int)(mPointDis*v)+i*mPointDis;//计算左边边距

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedpoint.getLayoutParams();
                params.leftMargin = leftMargin;//修改左边距

//                重新设置布局参数
                ivRedpoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {
//            某个页面被选中
                if(i== imageViewArrayList.size()-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else {
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                页面发生变化的回调
            }
        });
        ivRedpoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                移除监听,避免重复回调
                ivRedpoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);

//                layout方法执行结束的回调
                mPointDis = llContainer.getChildAt(1).getLeft()-llContainer.getChildAt(0).getLeft();
                System.out.println("圆点距离"+mPointDis);
            }

        });
//        按钮点击事件
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        更新sp.已经不是第一次进入
                PrefUtils.setBoolean(getApplicationContext(),"is_first_enter",false);

//               跳到主页面
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }



    private  void initData(){
        imageViewArrayList = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view  = new ImageView(this);
//            通过设置背景可以让宽高填充布局
            view.setBackgroundResource(mImageIds[i]);
            imageViewArrayList.add(view);

//            初始化小圆点
            ImageView point = new ImageView(this);
//            设置图片shape形状
            point.setImageResource(R.drawable.shape_point_gray);
//            给容器加圆点
            llContainer.addView(point);
        }
    }

    class GuideAdapter extends PagerAdapter{

//        item个数
        @Override
        public int getCount() {
            return imageViewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

//        初始化item布局
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView view = imageViewArrayList.get(position);
            container.addView(view);
            return view;
        }

//        销毁item
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
