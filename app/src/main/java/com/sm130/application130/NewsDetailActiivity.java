package com.sm130.application130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

public class NewsDetailActiivity extends Activity {

    private ImageButton share;
    private ImageButton text;
    private ImageButton menu;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        share=findViewById(R.id.btn_share);
        text=findViewById(R.id.btn_text);
        menu=findViewById(R.id.btn_menu1);
        webView=findViewById(R.id.web_view);


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);


    }

    public void back(View view) {

        finish();
    }
}
