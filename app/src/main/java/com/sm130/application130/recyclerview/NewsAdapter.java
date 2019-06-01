package com.sm130.application130.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;
import com.sm130.application130.NewsDetailActiivity;
import com.sm130.application130.R;
import com.sm130.application130.beijing_domain.Data;
import com.sm130.application130.beijing_domain.News;
import com.sm130.application130.global.GlobalConstants;
import com.sm130.application130.u.MyBitmapUtils;


import java.util.List;
import java.util.logging.LogRecord;

public class NewsAdapter extends RecyclerView.Adapter {

    public static final int HEAD_TYPE = 0;
    public static final int BODY_TYPE = 1;
    public static final int FOOT_TYPE = 2;

    private  int mPointDis;

    private List<News> data;
    private Data beijin;
    private Activity activity;
    Handler mHandler=null;

    public NewsAdapter(Data beijin,Activity activity) {
        this.data = beijin.getNews();
        this.beijin =beijin;
        this.activity = activity;

    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return HEAD_TYPE;
        }else if(position == getItemCount() -1){
            return FOOT_TYPE;
        }else{
            return BODY_TYPE;
        }
    }


//    public void zidon(final ViewPager viewPager){
//        //头条新闻自动轮播
//
//        if (mHandler == null) {
//            mHandler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    int currentItem = viewPager.getCurrentItem();
//                    currentItem++;
//
//                    if (currentItem > data.size() - 1) {
//                        currentItem = 0;//如果已经跳转到了最后一个页面，跳到第一页
//
//                    }
//                    viewPager.setCurrentItem(currentItem);
//
//                    mHandler.sendEmptyMessageDelayed(0, 3000);//继续发送延时3秒的消息，形成内循环
//                }
//            };
//            //保证启动自动轮播逻辑只执行一次
//            mHandler.sendEmptyMessageDelayed(0, 3000);//发送延时3秒的消息
////            viewPager.setOnTouchListener(new View.OnTouchListener() {
////                @Override
////                public boolean onTouch(View v, MotionEvent event) {
////                    switch (event.getAction()) {
////                        case MotionEvent.ACTION_DOWN://鼠标按下的时候
////                            System.out.println("ACTION_DOWN");
////                            //停止广告自动轮播
////                            //删除handler的所有消息
////                            mHandler.removeCallbacksAndMessages(null);
////
////                            break;
////                        case MotionEvent.ACTION_CANCEL://鼠标抬起的时候---取消事件
////                            //当按下viewpager后，直接滑动listview，导致抬起事件无法响应，但会走此事件
////                            System.out.println("ACTION_CANCEL");
////                            //启动广告
////                            mHandler.sendEmptyMessageDelayed(0, 3000);
////                            break;
////                        case MotionEvent.ACTION_UP:
////                            System.out.println("ACTION_UP");
////                            //启动广告
////                            mHandler.sendEmptyMessageDelayed(0, 3000);
////                            break;
////                        default:
////                            break;
////                    }
////                    return false;
////                }
////            });
//        }
//
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final ViewPager viewPager;

        if(i == HEAD_TYPE) {
            final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_recyclerview_head, viewGroup, false);
            viewPager = view.findViewById(R.id.news_content_viewpage);

            viewPager.setAdapter(new AdvertisePagerAdapter(beijin.getTopnews(),view));
            ViewPagerIndicator viewPagerIndicator = view.findViewById(R.id.indicator);
            viewPagerIndicator.setViewPager(viewPager,beijin.getTopnews().size());
            viewPager.setCurrentItem(10000);
//            zidon(viewPager);
            return new HeadViewHolder(view);

        }else if(i==FOOT_TYPE){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_recyclerview_foot, viewGroup, false);
            return new FootViewHolder(view);
        }else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_recyclerview_item, viewGroup, false);
            return new NewsViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        News news = data.get(i);
        NewsViewHolder newsViewHolder = (NewsViewHolder) viewHolder;

//        设置图片
//        Bitmap bitmap = URLUtils.getHttpBitmap(news.getListimage());
//        newsViewHolder.imageView.setImageBitmap(bitmap);
//        URLUtils.getHttpBitmap(newsViewHolder.imageView,GlobalConstants.TOMCAT_URL+news.getListimage().substring(25));
        new MyBitmapUtils().display(newsViewHolder.imageView,GlobalConstants.TOMCAT_URL+news.getListimage().substring(25));
        newsViewHolder.textView.setText(news.getTitle());
        newsViewHolder.textView2.setText(news.getPubdate());

    }

    @Override
    public int getItemCount() {
        return data.size()+2;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        int type = getItemViewType(position);
        if(type == HEAD_TYPE){

        }else if(type == FOOT_TYPE){

        }else {
            final News news = data.get(position-1);
            final NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
//            Bitmap bitmap = URLUtils.getHttpBitmap(news.getListimage());
//            newsViewHolder.imageView.setImageBitmap(bitmap);

//            URLUtils.getHttpBitmap(newsViewHolder.imageView,GlobalConstants.TOMCAT_URL+news.getListimage().substring(25));
            new MyBitmapUtils().display(newsViewHolder.imageView,GlobalConstants.TOMCAT_URL+news.getListimage().substring(25));
            newsViewHolder.textView.setText(news.getTitle());
            newsViewHolder.textView2.setText(news.getPubdate());

            newsViewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, NewsDetailActiivity.class);
                    intent.putExtra("url",GlobalConstants.TOMCAT_URL+news.getUrl().substring(25));
                    activity.startActivity(intent);
//                    修改为已读状态
                    newsViewHolder.textView.setTextColor(Color.GRAY);
                    newsViewHolder.textView2.setTextColor(Color.GRAY);
//                    打开详情页面
//                    WebViewFragment webViewFragment = WebViewFragment.newInstance(news.getUrl());
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.container1,webViewFragment,"f5")
//                            .commit();
//                    System.out.println(1);
                }
            });

        }
    }




    class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView textView2;
        View view;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_item);
            textView = itemView.findViewById(R.id.textView1_item);
            textView2 = itemView.findViewById(R.id.textView2_item);
            view = itemView.findViewById(R.id.view);
        }

    }
    class FootViewHolder extends RecyclerView.ViewHolder{

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class HeadViewHolder extends RecyclerView.ViewHolder{

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
