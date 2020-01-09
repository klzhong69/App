package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.ChatAdapter;
import com.example.app.Entity.Chats;
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.Conver;
import com.example.app.cofig.DateUtil;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mConverDao;

import java.util.ArrayList;
import java.util.List;

public class ChatModel {

    private static ChatAdapter mAdapter;
    private static List<Chats> mArrayList;

    public static void initData(String conver,int set,int lim) {
        mArrayList = new ArrayList<Chats>();
        datas(conver,set,lim);


    }

    public static void datas(String conver,int set,int lim) {
        List<Chat> list = mChatDao.queryBuilder(conver,set,lim);
        for(int i=0;i<list.size();i++){
            if(i==0){
                Chats i1 = new Chats(list.get(i).getSendsrc(), list.get(i).getTxt(), DateUtil.stampToDates(String.valueOf((list.get(i).getData()*1000))), 0);
                mArrayList.add(i1);
            }else{
                Long longs = (list.get(i).getData())-(list.get(i-1).getData());
                if(longs>600000L){
                    Chats i1 = new Chats(list.get(i).getSendsrc(), list.get(i).getTxt(), DateUtil.stampToDates(String.valueOf((list.get(i).getData()*1000))), 0);
                    mArrayList.add(i1);
                }
            }
            Chats i1 = new Chats(list.get(i).getSendsrc(), list.get(i).getTxt(), list.get(i).getData().toString(), list.get(i).getState()+1);
            mArrayList.add(i1);
        }
    }

    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
        mAdapter = new ChatAdapter(context, mArrayList);
        //设置适配器adapter
        recycler13.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(context);
        mLinearLayoutManager.setStackFromEnd(true);
        if (mAdapter.getItemCount() > 0) {
            mLinearLayoutManager.scrollToPositionWithOffset(mAdapter.getItemCount() - 1, Integer.MIN_VALUE);

        }
        recycler13.scrollToPosition(mAdapter.getItemCount()-1);
        recycler13.setLayoutManager(mLinearLayoutManager);

        recycler13.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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
        recycler13.setItemAnimator(defaultItemAnimator);


    }



    public static void Add(RecyclerView mRecyclerView, Chats entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

    public static void recly(RecyclerView mRecyclerView,int poaition){
        if(poaition==0){
            mRecyclerView.smoothScrollToPosition(mArrayList.size());
        }else{
            mRecyclerView.smoothScrollToPosition(poaition);
        }

    }

}
