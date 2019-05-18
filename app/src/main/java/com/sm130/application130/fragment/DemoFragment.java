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
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm130.application130.R;
import com.sm130.application130.domain.Interface;

public class DemoFragment extends Fragment {
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
        View root = inflater.inflate(R.layout.content_viewpager_recyclerview, container, false);
//        视图内的元素
        TextView textView = root.findViewById(R.id.demo);


//        设置值
        textView.setText(data);

        return root;
    }

    public static DemoFragment newInstance(String data){
        DemoFragment frag = new DemoFragment();
        Bundle bundle = new Bundle();
//        放数据
        bundle.putString(DATA,data);

        frag.setArguments(bundle);
        return frag;
    }
}
