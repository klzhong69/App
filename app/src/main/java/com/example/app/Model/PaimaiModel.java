package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.HoldpeopleAdapter;
import com.example.app.Adapter.OnlinepeopleAdapter;
import com.example.app.Adapter.PaimaiAdapter;
import com.example.app.Entity.Holdpeople;
import com.example.app.Entity.Onlinepeople;
import com.example.app.Entity.Paimai;

import java.util.ArrayList;

public class PaimaiModel {

    public static ArrayList<Holdpeople> mArrayList= new ArrayList<Holdpeople>();
    private static PaimaiAdapter mAdapter;
    private static HoldpeopleAdapter mAdapters;


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




    public static void Adds(RecyclerView mRecyclerView,Holdpeople entity){
        mAdapters.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

    public static void Add(RecyclerView mRecyclerView,Holdpeople entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

    public static void Remove(int position){
        mAdapter.removeData(position);
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
