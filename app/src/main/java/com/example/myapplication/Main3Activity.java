package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.cofig.RecyclerViewAdapter;
import com.example.myapplication.utils.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Main3Activity extends AppCompatActivity {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.button6)
    Button button6;
    private List<BaseEntity> mEntityList;
    private int a = 0;
    private RecyclerViewAdapter mAdapter;//适配器
    private LinearLayoutManager mLinearLayoutManager;//布局管理器
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        initData();
        //初始化数据

        //创建适配器，将数据传递给适配器
        mAdapter = new RecyclerViewAdapter(this, mEntityList);
        //设置适配器adapter
        mRecyclerView.setAdapter(mAdapter);

       /* //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);*/

        /*//多列布局
        mLayoutManager = new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(mLayoutManager);*/


        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        mLinearLayoutManager.scrollToPositionWithOffset(mAdapter.getItemCount() - 1, Integer.MIN_VALUE);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Main3Activity.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(Main3Activity.this, position + " Long click", Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });


    }

    private void initData() {
        mEntityList = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            BaseEntity entity = new BaseEntity("周润发", "进入房间" + a, "https://momeak.oss-cn-shenzhen.aliyuncs.com/l3.png");
            mEntityList.add(entity);
            a++;
        }
    }


    @OnClick(R.id.button6)
    public void onViewClicked() {
        BaseEntity entity = new BaseEntity("周润发", "进入房间" + a, "https://momeak.oss-cn-shenzhen.aliyuncs.com/l3.png");
        mAdapter.addData(mEntityList.size(),entity);
        a++;
        mRecyclerView.smoothScrollToPosition(mEntityList.size());
    }
}
