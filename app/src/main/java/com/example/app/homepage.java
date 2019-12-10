package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Entity.Page;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class homepage extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView gridview;
    private GridViewAdapter mAdapters;//适配器
    private ArrayList<Page> mData;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mData = new ArrayList<Page>();
        for(int i=0;i<14;i++){
            Page i1 = new Page("宝马  x  241", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
            mData.add(i1);
        }

        //创建适配器，将数据传递给适配器
        mAdapters = new GridViewAdapter(this, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(this, 4);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new GridViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(homepage.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(homepage.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        gridview.setItemAnimator(defaultItemAnimator);
    }
}
