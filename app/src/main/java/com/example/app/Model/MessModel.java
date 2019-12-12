package com.example.app.Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.MessageAdapter;
import com.example.app.Entity.Message;
import com.example.app.Messages;
import com.example.app.chat;

import java.util.ArrayList;
import java.util.List;

public class MessModel {

    private static  ArrayList<Message> mArrayList;
    private static ArrayList<Message> mArrayLists;

    public static void initData() {
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

    public static void initrecycler(Context context, RecyclerView recycler10) {
        //适配器
        MessageAdapter mAdapter = new MessageAdapter(context, mArrayList);
        //设置适配器adapter
        recycler10.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
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
        recycler10.setItemAnimator(defaultItemAnimator);
    }

    public static void initrecyclers(Context context, RecyclerView recycler11) {
        //适配器
        MessageAdapter mAdapter = new MessageAdapter(context, mArrayLists);
        //设置适配器adapter
        recycler11.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
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
        recycler11.setItemAnimator(defaultItemAnimator);
    }

}
