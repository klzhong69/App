package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.MessageAdapter;
import com.example.app.Entity.Message;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Messages extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.imageView78)
    ImageView imageView78;
    @BindView(R.id.rect_view)
    RelativeLayout rectView;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.textView66)
    TextView textView66;
    @BindView(R.id.textView67)
    TextView textView67;
    @BindView(R.id.imageView67)
    ImageView imageView67;
    @BindView(R.id.textView68)
    TextView textView68;
    @BindView(R.id.textView96)
    TextView textView96;
    @BindView(R.id.rect_views)
    RelativeLayout rectViews;
    @BindView(R.id.recycler10)
    RecyclerView recycler10;
    @BindView(R.id.recycler11)
    RecyclerView recycler11;
    private ArrayList<Message> mArrayList;
    private ArrayList<Message> mArrayLists;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mess_message, container, false);
        unbinder = ButterKnife.bind(this, view);

        initData();

        init();
        inits();

        return view;

    }

    private void init() {
        //适配器
        MessageAdapter mAdapter = new MessageAdapter(getContext(), mArrayList);
        //设置适配器adapter
        recycler10.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler10.setLayoutManager(layoutManager);
        recycler10.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent2 = new Intent(getContext(), chat.class);
                startActivity(intent2);
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(getContext(), position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler10.setItemAnimator(defaultItemAnimator);
    }

    private void inits() {
        //适配器
        MessageAdapter mAdapter = new MessageAdapter(getContext(), mArrayLists);
        //设置适配器adapter
        recycler11.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler11.setLayoutManager(layoutManager);
        recycler11.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(getContext(), position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler11.setItemAnimator(defaultItemAnimator);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Messages newInstance() {
        Bundle args = new Bundle();
        Messages fragment = new Messages();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initData() {
        mArrayList = new ArrayList<Message>();
        mArrayLists = new ArrayList<Message>();

        for (int i = 0; i < 4; i++) {

            Message i1 = new Message("Ema90", "hi伙伴，明天一起直播吗", "今天13:20", "3", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "");
            mArrayList.add(i1);
        }


        for (int i = 0; i < 4; i++) {
            Message i2 = new Message("陌生人消息", "hi伙伴，明天一起直播吗", "今天13:20", "3", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "");
            mArrayLists.add(i2);
        }


    }

    @OnClick({R.id.imageView78, R.id.rect_views})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView78:
                Intent intent2 = new Intent(getContext(), mess_friends.class);
                startActivity(intent2);
                break;
            case R.id.rect_views:
                break;
        }
    }
}
