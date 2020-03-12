package com.example.hz52.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.FamilyHomeAdapter;
import com.example.hz52.app.Entity.Familyhome;

import java.util.ArrayList;

public class FamilyHomeModel {


    private static ArrayList<Familyhome> mData;
    private static FamilyHomeAdapter mAdapters;

    public static void initData() {
        mData = new ArrayList<Familyhome>();
        for (int i = 0; i < 12; i++) {
            Familyhome i1 = new Familyhome("芭比uu","https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
            mData.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView recycler4) {
        //创建适配器，将数据传递给适配器
        mAdapters = new FamilyHomeAdapter(context, mData);
        //设置适配器adapter
        recycler4.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 5);
        recycler4.setLayoutManager(mLayoutManager);


        recycler4.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new FamilyHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, position + " click", Toast.LENGTH_SHORT).show();
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
        recycler4.setItemAnimator(defaultItemAnimator);


    }


    public static void Add(RecyclerView mRecyclerView,Familyhome entity){
        mAdapters.addData(mData.size(), entity);
        mRecyclerView.smoothScrollToPosition(mData.size());
    }

}
