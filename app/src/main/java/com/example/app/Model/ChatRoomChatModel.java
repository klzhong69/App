package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.RoomheadAdapter;
import com.example.app.Adapter.RoomheadchatAdapter;
import com.example.app.Adapter.RoomtxtAdapter;
import com.example.app.Entity.Roomhead;
import com.example.app.Entity.Roomheadchat;
import com.example.app.Entity.Roomtxt;
import com.example.app.chatroom_chat;

import java.util.ArrayList;

public class ChatRoomChatModel {

    private static ArrayList<Roomheadchat> mData;
    private static ArrayList<Roomtxt> mEntityList;
    private static RoomtxtAdapter mAdapter;

    public static void initData() {
        mData = new ArrayList<Roomheadchat>();
        mEntityList = new ArrayList<Roomtxt>();

        for(int i=0;i<10;i++){
            int num = i+1;
            Roomheadchat i1 = new Roomheadchat("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg",String.valueOf(num));
            mData.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView mRecyclerView) {
        //创建适配器，将数据传递给适配器
        mAdapter = new RoomtxtAdapter(context, mEntityList);
        //设置适配器adapter
        mRecyclerView.setAdapter(mAdapter);

       /* //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);*/

        /*//多列布局
        mLayoutManager = new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(mLayoutManager);*/


        //布局管理器
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(context);
        mLinearLayoutManager.setStackFromEnd(true);
        if (mAdapter.getItemCount() > 0) {
            mLinearLayoutManager.scrollToPositionWithOffset(mAdapter.getItemCount() - 1, Integer.MIN_VALUE);
        }

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mAdapter.setOnItemClickListener(new RoomtxtAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
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
        mRecyclerView.setItemAnimator(defaultItemAnimator);


    }


    public static void initrecyclers(Context context, RecyclerView gridview) {
        RoomheadchatAdapter mAdapters = new RoomheadchatAdapter(context, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gridview.setLayoutManager(layoutManager);

        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new RoomheadchatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
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
        gridview.setItemAnimator(defaultItemAnimator);


    }

    public static void Add(RecyclerView mRecyclerView,Roomtxt entity){
        mAdapter.addData(mEntityList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mEntityList.size());
    }

}
