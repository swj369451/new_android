package com.sm130.application130;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm130.application130.domain.Share;

import java.util.List;

public class ShareAdapter extends RecyclerView.Adapter {

    private List<Share> data;


    public ShareAdapter(List<Share> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.share_item, viewGroup, false);

        return new ShareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final Share share = data.get(i);
        final ShareViewHolder shareViewHolder = (ShareViewHolder) viewHolder;
        shareViewHolder.share_textview.setText(share.getBiaoti());
        shareViewHolder.share_imageview.setImageResource(share.getImageId());
        shareViewHolder.share_choice.setImageResource(R.drawable.skyblue_platform_checked_disabled);

        shareViewHolder.share_view.setOnClickListener(new View.OnClickListener() {
            boolean a = true;
            @Override
            public void onClick(View v) {
                if(a){
                    shareViewHolder.share_choice.setImageResource(R.drawable.skyblue_platform_checked);
                    a=false;
//                    把剩下的变成灰色
                    for(int i=0;i<data.size();i++){
                        shareViewHolder.share_imageview.setImageResource(share.getcImageId());
                    }
                    shareViewHolder.share_imageview.setImageResource(share.getImageId());


                }else{
                    shareViewHolder.share_choice.setImageResource(R.drawable.skyblue_platform_checked_disabled);
                    a=true;

                    for(int i=0;i<data.size();i++){
                        shareViewHolder.share_imageview.setImageResource(share.getImageId());
                    }
                }
            }
        });


    }



    @Override
    public int getItemCount() {
        return data.size();
    }



    class ShareViewHolder extends RecyclerView.ViewHolder{
        ImageView share_imageview;
        ImageView share_choice;
        TextView share_textview;
        View share_view;

        public ShareViewHolder(@NonNull View itemView) {
            super(itemView);
            share_choice = itemView.findViewById(R.id.share_choice);
            share_imageview = itemView.findViewById(R.id.share_imageview);
            share_textview = itemView.findViewById(R.id.share_textview);
            share_view = itemView.findViewById(R.id.share_view);
        }
    }
}
