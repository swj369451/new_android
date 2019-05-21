package com.sm130.application130.recyclerview;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sm130.application130.R;
import com.sm130.application130.beijing_domain.Topnews;
import com.sm130.application130.utils.URLUtils;

import java.util.ArrayList;
import java.util.List;

public class AdvertisePagerAdapter extends PagerAdapter {

    private List<Topnews> data;
    private View view;

    public AdvertisePagerAdapter(List<Topnews> data, View view) {
        this.data = data;
        this.view = view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        TextView textView = view.findViewById(R.id.biaoti_textView);
        textView.setText(data.get(position%data.size()).getTitle());


        ImageView imageView = new ImageView(container.getContext());
//        这里设置con1000开始所以要取模
        Bitmap bitmap = URLUtils.getHttpBitmap(data.get(position%data.size()).getTopimage());
        imageView.setImageBitmap(bitmap);
        container.addView(imageView);





        return imageView;
    }


}
