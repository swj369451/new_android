package com.sm130.application130.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.sm130.application130.R;

public class WebViewFragment extends Fragment {
    public static String DATA  = "data";
    private String data;
    private Activity mActiviy;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActiviy = (Activity) context;
//        获取外部传送的数据
        data=getArguments().getString(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.web_view, container, false);
//        视图内的元素
        WebView webView = root.findViewById(R.id.web_view);


//        设置值
        webView.loadUrl(data);

        return root;
    }

    public static WebViewFragment newInstance(String data){
        WebViewFragment frag = new WebViewFragment();
        Bundle bundle = new Bundle();
//        放数据
        bundle.putString(DATA,data);

        frag.setArguments(bundle);
        return frag;
    }
}
