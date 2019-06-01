package com.sm130.application130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sm130.application130.R;
import com.sm130.application130.domain.Share;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class ShareActivity extends Activity {

    private RecyclerView recyclerView;
    private Tencent mTencent;
    private String url;
    private String title;

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

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");


        recyclerView  = findViewById(R.id.share_recyclerView);

        ShareAdapter shareAdapter = new ShareAdapter(list);
        recyclerView.setAdapter(shareAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        mTencent = Tencent.createInstance("101584928", this.getApplicationContext());
    }

    public void back(View view){
        finish();
    }


    public void share(View view){
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "QQ分享");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "清明11家游客接待");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,url);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "智慧北京");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "其他附加功能");
        mTencent.shareToQQ(ShareActivity.this,params,qqShareListener);
    }

    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {

        }
        @Override
        public void onComplete(Object response) {

        }

        @Override
        public void onError(UiError uiError) {

        }

    };
}
