package com.sm130.application130.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sm130.application130.R;
import com.sm130.application130.adapter.ZutuAdapter;
import com.sm130.application130.zutu_domain.Data;

public class ZutuFragment extends Fragment {
    public static String DATA  = "data";
    private Data data;
    private Activity mActiviy;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActiviy = (Activity) context;
//        获取外部传送的数据
        data= (Data) getArguments().getSerializable(DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.zutu, container, false);
//        视图内的元素
        RecyclerView recyclerView = root.findViewById(R.id.zutu_recycleview);
//        设置值

        ZutuAdapter zutuAdapter = new ZutuAdapter(data.getNews());
        recyclerView.setAdapter(zutuAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));


        return root;
    }

    public static ZutuFragment newInstance(Data data){
        ZutuFragment frag = new ZutuFragment();
        Bundle bundle = new Bundle();
//        放数据
        bundle.putSerializable(DATA,data);

        frag.setArguments(bundle);
        return frag;
    }
}
