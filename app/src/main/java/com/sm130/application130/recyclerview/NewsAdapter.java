package com.sm130.application130.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm130.application130.NewActivity;
import com.sm130.application130.NewsDetailActiivity;
import com.sm130.application130.R;
import com.sm130.application130.beijing_domain.Data;
import com.sm130.application130.beijing_domain.News;
import com.sm130.application130.fragment.DemoFragment;
import com.sm130.application130.fragment.WebViewFragment;
import com.sm130.application130.global.GlobalConstants;
import com.sm130.application130.utils.URLUtils;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {

    public static final int HEAD_TYPE = 0;
    public static final int BODY_TYPE = 1;
    public static final int FOOT_TYPE = 2;


    private List<News> data;
    private Data beijin;
    private Activity activity;

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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == HEAD_TYPE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_recyclerview_head, viewGroup, false);

            ViewPager viewPager = view.findViewById(R.id.news_content_viewpage);

            viewPager.setAdapter(new AdvertisePagerAdapter(beijin.getTopnews()));
            viewPager.setCurrentItem(10000);
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
        Bitmap bitmap = URLUtils.getHttpBitmap(news.getListimage());
        newsViewHolder.imageView.setImageBitmap(bitmap);

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
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            Bitmap bitmap = URLUtils.getHttpBitmap(news.getListimage());
            newsViewHolder.imageView.setImageBitmap(bitmap);
            newsViewHolder.textView.setText(news.getTitle());
            newsViewHolder.textView2.setText(news.getPubdate());

            newsViewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, NewsDetailActiivity.class);
                    intent.putExtra("url",news.getUrl());
                    activity.startActivity(intent);

//                    打开详情页面
//                    WebViewFragment webViewFragment = WebViewFragment.newInstance(news.getUrl());
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.container1,webViewFragment,"f5")
//                            .commit();
                    System.out.println(1);
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
