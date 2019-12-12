package com.example.app.Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Adapter.ListLeaderAdapter;
import com.example.app.Entity.Listleader;
import com.example.app.Entity.Page;
import com.example.app.chat;

import java.util.ArrayList;

public class ListModel {


    private static ArrayList<Listleader> mArrayList;
    private static ListLeaderAdapter mAdapter;

    public static void initData() {
        mArrayList = new ArrayList<Listleader>();
        for (int i = 0; i < 10; i++) {
            int sum = i + 4;
            Listleader i1 = new Listleader(sum + "", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h5.jpg", "胡楠", "", "财富值", "18630");
            mArrayList.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
         mAdapter = new ListLeaderAdapter(context, mArrayList);
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

        mAdapter.setOnItemClickListener(new ListLeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent2 = new Intent(context, chat.class);
                context.startActivity(intent2);
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


    public static void Add(RecyclerView mRecyclerView,Listleader entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

}
