package com.sm130.application130;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.sm130.application130.utils.PrefUtils;

public class SplashActivity extends Activity {


    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rlRoot = (RelativeLayout)findViewById(R.id.rl_root);

//        旋转动画，从0到360度的
        RotateAnimation animation = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        设置时间
        animation.setDuration(1000);
//        保持动画结束状态
        animation.setFillAfter(true);

//        缩放动画
        ScaleAnimation animScale = new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        设置时间
        animScale.setDuration(1000);

//        渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        animation.setDuration(2000);
        animation.setFillAfter(true);

//      动画集合
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animation);
        set.addAnimation(alphaAnimation);
        set.addAnimation(animScale);

//        启动动画
        rlRoot.startAnimation(set);


        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                动画结束，跳转页面
//                如果是第一次进入，跳过新手引导
//                否则跳主页面
                boolean is_first_enter = PrefUtils.getBoolean(SplashActivity.this, "is_first_enter", true);
                Intent intent;
                if(is_first_enter){
//                    新手引导
                    intent = new Intent(getApplicationContext(), GuideActivity.class);
                }else {
//                    主页面
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                }
                startActivity(intent);
//                结束当前页面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
