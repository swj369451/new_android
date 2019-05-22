package com.sm130.application130;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sm130.application130.R;
import com.sm130.application130.domain.Share;

import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);

        List<Share> list = new ArrayList<>();
        list.add(new Share(R.drawable.skyblue_logo_sinaweibo_checked,R.drawable.skyblue_logo_sinaweibo,"新浪微博",false));
        list.add(new Share(R.drawable.skyblue_logo_qzone_checked,R.drawable.skyblue_logo_qzone,"QQ空间",false));
        list.add(new Share(R.drawable.skyblue_logo_wechat_checked,R.drawable.skyblue_logo_wechat,"微信好友",false));
        list.add(new Share(R.drawable.skyblue_logo_wechatmoments_checked,R.drawable.skyblue_logo_wechatmoments,"微信朋友圈",false));
        list.add(new Share(R.drawable.skyblue_logo_wechatfavorite_checked,R.drawable.skyblue_logo_wechatfavorite,"微信收藏",false));
        list.add(new Share(R.drawable.skyblue_logo_qq_checked,R.drawable.skyblue_logo_qq,"QQ",false));
        list.add(new Share(R.drawable.skyblue_logo_email_checked,R.drawable.skyblue_logo_email,"邮件",false));
        list.add(new Share(R.drawable.skyblue_logo_shortmessage_checked,R.drawable.skyblue_logo_shortmessage,"信息",false));


        recyclerView  = findViewById(R.id.share_recyclerView);

        ShareAdapter shareAdapter = new ShareAdapter(list);
        recyclerView.setAdapter(shareAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

    }

    public void back(View view){
        finish();
    }
}
