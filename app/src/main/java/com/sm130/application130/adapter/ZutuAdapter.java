package com.sm130.application130.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm130.application130.R;
import com.sm130.application130.utils.URLUtils;
import com.sm130.application130.zutu_domain.News;

import java.util.ArrayList;
import java.util.List;

public class ZutuAdapter extends RecyclerView.Adapter {

    private List<News> data;

    public ZutuAdapter(List<News> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.zutu_item,viewGroup,false);

        return new ZutuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        News news = data.get(i);
        ZutuViewHolder zutuViewHolder = (ZutuViewHolder) viewHolder;
        Bitmap bitmap = URLUtils.getHttpBitmap(news.getListimage());
        zutuViewHolder.imageView.setImageBitmap(bitmap);
        zutuViewHolder.textView.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class ZutuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ZutuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.zutu_imageview);
            textView = itemView.findViewById(R.id.zutu_textview);
        }
    }

}
