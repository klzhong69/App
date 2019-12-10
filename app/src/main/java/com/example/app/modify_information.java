package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Adapter.ModifyViewAdapter;
import com.example.app.Entity.Modify;
import com.example.app.Entity.Page;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class modify_information extends AppCompatActivity {

    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    private ArrayList<Modify> mData;
    private ModifyViewAdapter mAdapters;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_information);
        ButterKnife.bind(this);

        init();

        //创建适配器，将数据传递给适配器
        mAdapters = new ModifyViewAdapter(this, mData);
        //设置适配器adapter
        recycler2.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(this, 5);
        recycler2.setLayoutManager(mLayoutManager);


        recycler2.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new GridViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mData.size()-1;
                if(position == del){
                    Toast.makeText(modify_information.this, "添加图片", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(modify_information.this, position + " click", Toast.LENGTH_SHORT).show();
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
        recycler2.setItemAnimator(defaultItemAnimator);
    }

    private void init() {
        mData = new ArrayList<Modify>();
        for (int i = 0; i < 20; i++) {
            Modify i1 = new Modify("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
            mData.add(i1);
        }

        Modify i1 = new Modify("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png");
        mData.add(mData.size(),i1);


    }
}
