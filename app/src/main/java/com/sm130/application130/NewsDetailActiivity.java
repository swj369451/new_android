package com.sm130.application130;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

public class NewsDetailActiivity extends Activity {

    private ImageButton share;
    private ImageButton text;
    private ImageButton menu;
    private WebView webView;
    WebSettings settings;
    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        share=findViewById(R.id.btn_share);
        text=findViewById(R.id.btn_text);
        menu=findViewById(R.id.btn_menu1);
        webView=findViewById(R.id.web_view);

         settings = webView.getSettings();

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        webView.loadUrl(url);


    }

    public void share(View view) {
        Intent intent = new Intent(this, ShareActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        this.startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
    public void text(View view) {
        showChooseDialog();
    }



    private int tempWhich;
    private int CurrentWhich = 2;

    private void showChooseDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("字体设置");

        String[] item = new String[]{"超大号字体","大号字体","正常字体","小号字体","超小号字体"};

        builder.setSingleChoiceItems(item, CurrentWhich, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempWhich = which;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

             switch (tempWhich){
                 case 0:
                     settings.setTextSize(WebSettings.TextSize.LARGEST);
                     break;
                 case 1:
                     settings.setTextSize(WebSettings.TextSize.LARGER);
                     break;
                 case 2:
                     settings.setTextSize(WebSettings.TextSize.NORMAL);
                     break;
                 case 3:
                     settings.setTextSize(WebSettings.TextSize.SMALLER);
                     break;
                 case 4:
                     settings.setTextSize(WebSettings.TextSize.SMALLEST);
                     break;
             }

             CurrentWhich = tempWhich;
            }
        });

        builder.setNegativeButton("取消",null);

        builder.show();
    }
}
