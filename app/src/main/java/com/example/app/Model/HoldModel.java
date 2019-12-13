package com.example.app.Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.HoldpeopleAdapter;
import com.example.app.Adapter.ListLeaderAdapter;
import com.example.app.Entity.Holdpeople;
import com.example.app.Entity.Listleader;
import com.example.app.chat;

import java.util.ArrayList;

public class HoldModel {

    private static ArrayList<Holdpeople> mArrayList;
    private static HoldpeopleAdapter mAdapter;

    public static void initData() {
        mArrayList = new ArrayList<Holdpeople>();
        for (int i = 0; i < 10; i++) {
            String type;
            if(i==0){
                type = "房主";
            }else{
                type = "0";
            }
            Holdpeople i1 = new Holdpeople("ID2698456", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h5.jpg", "胡楠"+i, "", type);
            mArrayList.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
        mAdapter = new HoldpeopleAdapter(context, mArrayList);
        //设置适配器adapter
        recycler13.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler13.setLayoutManager(layoutManager);
        recycler13.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new HoldpeopleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(context, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler13.setItemAnimator(defaultItemAnimator);


    }


    public static void Add(RecyclerView mRecyclerView,Holdpeople entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }
}
