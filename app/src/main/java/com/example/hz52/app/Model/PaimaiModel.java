package com.example.hz52.app.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.HoldpeopleAdapter;
import com.example.hz52.app.Adapter.PaimaiAdapter;
import com.example.hz52.app.Entity.Holdpeople;
import com.example.hz52.app.Entity.Message;
import com.example.hz52.app.Sqlentity.Conver;
import com.example.hz52.app.chatroom;
import com.example.hz52.app.cofig.DateUtil;
import com.example.hz52.app.dao.mConverDao;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class PaimaiModel {

    public static ArrayList<Holdpeople> mArrayList= new ArrayList<Holdpeople>();
    public static PaimaiAdapter mAdapter;
    public static HoldpeopleAdapter mAdapters;

    public static void initData(JsonArray users) {
        try {
            for(int i=0;i<users.size();i++){
                String userId = users.get(i).getAsJsonObject().get("userId").getAsString();
                String nickname = users.get(i).getAsJsonObject().get("nickname").getAsString();
                String avatarUrl = users.get(i).getAsJsonObject().get("avatarUrl").getAsString();
                Holdpeople i1 = new Holdpeople(userId, avatarUrl, nickname, "", "0");
                mArrayList.add(i1);
            }
        } catch (Exception ignored) {}


    }

    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
        mAdapter = new PaimaiAdapter(context, mArrayList);
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

        mAdapter.setOnItemClickListener(new PaimaiAdapter.OnItemClickListener() {
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


    public static void initrecyclers(Context context, RecyclerView recycler13) {
        //适配器
        mAdapters = new HoldpeopleAdapter(context, mArrayList);
        //设置适配器adapter
        recycler13.setAdapter(mAdapters);

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

        mAdapters.setOnItemClickListener(new HoldpeopleAdapter.OnItemClickListener() {
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


    public static void Add(Holdpeople entity){
        mAdapter.addData(mArrayList.size(), entity);
        mAdapter.notifyItemChanged(mArrayList.size());
    }

    public static void Remove(int position){
        mAdapter.removeData(position);
        mAdapter.notifyItemChanged(position);
    }

    public static void Adds(Holdpeople entity){
        mAdapters.addData(mArrayList.size(), entity);
        mAdapters.notifyItemChanged(mArrayList.size());
    }

    public static void Removes(int position){
        mAdapters.removeData(position);
        mAdapters.notifyItemChanged(position);
    }

    public static int get(String id){
        for(int i=0;i<mArrayList.size();i++){
            if(mArrayList.get(i).getId().equals(id)){
                return i;
            }
        }
        return 0;
    }

}
