package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.Package1Adapter;
import com.example.app.Adapter.Package2Adapter;
import com.example.app.Entity.Package1;
import com.example.app.Entity.Package2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_package extends AppCompatActivity {


    @BindView(R.id.recycler9)
    RecyclerView recycler9;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView71)
    TextView textView71;
    @BindView(R.id.recycler8)
    RecyclerView recycler8;
    @BindView(R.id.relativeLayout9)
    RelativeLayout relativeLayout9;
    @BindView(R.id.textView72)
    TextView textView72;
    private ArrayList<Package1> mData;
    private Package1Adapter mAdapter;
    private Package2Adapter mAdapters;
    private ArrayList<Package2> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_package);
        ButterKnife.bind(this);
        initData();

        init();
        //创建适配器，将数据传递给适配器
        mAdapter = new Package1Adapter(this, mData);
        //设置适配器adapter
        recycler8.setAdapter(mAdapter);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 5);
        recycler8.setLayoutManager(mLayoutManager);


        recycler8.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new Package1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mData.size() - 1;
                if (position == del) {
                    Toast.makeText(my_package.this, "添加图片", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(my_package.this, position + " click", Toast.LENGTH_SHORT).show();
                }

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
        recycler8.setItemAnimator(defaultItemAnimator);
    }

    private void init() {
        //创建适配器，将数据传递给适配器
        mAdapters = new Package2Adapter(this, mDatas);
        //设置适配器adapter
        recycler9.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        recycler9.setLayoutManager(mLayoutManager);


        recycler9.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new Package2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mDatas.size() - 1;
                if (position == del) {
                    Toast.makeText(my_package.this, "添加图片", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(my_package.this, position + " click", Toast.LENGTH_SHORT).show();
                }

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
        recycler9.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mData = new ArrayList<Package1>();
        mDatas = new ArrayList<Package2>();

        for (int i = 0; i < 5; i++) {
            Package1 i1 = new Package1("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "苗苗");
            mData.add(i1);
        }

        Package1 i1 = new Package1("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png", "");
        mData.add(mData.size(), i1);

        for (int i = 0; i < 4; i++) {
            Package2 i2 = new Package2("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "苗苗", "有效期:2019-12-30");
            mDatas.add(i2);
        }

        Package2 i2 = new Package2("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png", "", "");
        mDatas.add(mDatas.size(), i2);
    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.title:
                title.setText("我的包裹");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }
}
