package com.example.app.Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FriendsAdapter;
import com.example.app.Adapter.ListLeaderAdapter;
import com.example.app.Entity.Friends;
import com.example.app.Entity.Listleader;
import com.example.app.chat;
import com.example.app.mess_friends;

import java.util.ArrayList;

public class MessFriendsModel {

    private static ArrayList<Friends> mArrayList;
    private static FriendsAdapter mAdapter;

    public static void initData(int a) {
        mArrayList = new ArrayList<Friends>();
        for (int i = 0; i < 4; i++) {
            Friends i1 = new Friends("Ema90", "hi伙伴，明天一起直播吗",  "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "");
            mArrayList.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView recycler12,int a ) {
        //适配器
         mAdapter = new FriendsAdapter(context, mArrayList);
        //设置适配器adapter
        recycler12.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler12.setLayoutManager(layoutManager);
        recycler12.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(a==0){

                }else{

                }
                Toast.makeText(context, position + " click", Toast.LENGTH_SHORT).show();
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
        recycler12.setItemAnimator(defaultItemAnimator);


    }


    public static void Add(RecyclerView mRecyclerView,Friends entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

}
