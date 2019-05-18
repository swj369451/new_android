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
import android.widget.TextView;
import android.widget.Toast;

import com.sm130.application130.R;
import com.sm130.application130.beijing_domain.Data;
import com.sm130.application130.recyclerview.NewsAdapter;

public class ViewPageFragment extends Fragment {
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
        View root = inflater.inflate(R.layout.content_viewpager_recyclerview, container, false);
//        视图内的元素
        RecyclerView view = root.findViewById(R.id.content_recyclerView);


//        设置属性
        final NewsAdapter newsAdapter = new NewsAdapter(data);
        view.setAdapter(newsAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        view.setLayoutManager(linearLayoutManager);
        view.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition + 1 == newsAdapter.getItemCount()){
//                    没有更新的了
                    Toast.makeText(getContext(), "已经没有更新的了", Toast.LENGTH_SHORT).show();
                    newsAdapter.notifyItemRemoved(newsAdapter.getItemCount());
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


//        返回root
        return root;
    }

    public static ViewPageFragment newInstance(Data data){
        ViewPageFragment frag = new ViewPageFragment();
        Bundle bundle = new Bundle();
//        放数据
        bundle.putSerializable(DATA,data);

        frag.setArguments(bundle);
        return frag;
    }
}
