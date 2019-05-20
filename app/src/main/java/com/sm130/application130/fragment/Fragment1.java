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

public class Fragment1 extends Fragment {
    public static String TITLE  = "title";
    public static String CT  = "ct";
    private  Interface data;
    private Activity mActiviy;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActiviy = (Activity) context;
//        获取外部传送的数据
        data=new Interface(getArguments().getString(TITLE),getArguments().getBoolean(CT));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.title_pager, container, false);
//        视图内的元素
        TextView textView = root.findViewById(R.id.tv_title);
        ImageButton imageButton = root.findViewById(R.id.btn_menu);

//        设置值
        textView.setText(data.getTitle());

        if(data.getCt()==true){
            imageButton.setVisibility(View.VISIBLE);
        }else {
            imageButton.setVisibility(View.INVISIBLE);

        }

        return root;
    }

    public static Fragment1 newInstance(Interface data){
        Fragment1 frag = new Fragment1();
        Bundle bundle = new Bundle();
//        放数据
        bundle.putString(TITLE,data.getTitle());
        bundle.putBoolean(CT,data.getCt());

        frag.setArguments(bundle);
        return frag;
    }
}
