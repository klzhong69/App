package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FindListAdapter;
import com.example.app.Entity.Findlist;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Find extends Fragment {


    @BindView(R.id.textView115)
    TextView textView115;
    @BindView(R.id.textView116)
    TextView textView116;
    @BindView(R.id.relative9)
    RelativeLayout relative9;
    @BindView(R.id.recycler14)
    RecyclerView recycler14;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;
    private ArrayList<Findlist> mArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_hot, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();
        init();
        return view;

    }

    private void init() {
        //适配器
        FindListAdapter mAdapter = new FindListAdapter(getContext(), mArrayList);
        //设置适配器adapter
        recycler14.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler14.setLayoutManager(layoutManager);
        recycler14.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FindListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), chatroom.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);

            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler14.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Findlist>();
        for (int i = 0; i < 8; i++) {
            Findlist i1 = new Findlist("PHakamile Sikali", "Muria Moura", "2345", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "", "热门", "CV", "德国");
            mArrayList.add(i1);
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static Find newInstance() {
        Bundle args = new Bundle();
        Find fragment = new Find();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @OnClick(R.id.textView116)
    public void onViewClicked() {
        Intent intent2 = new Intent(getContext(), find_make.class);
        startActivity(intent2);
        getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
    }
}
