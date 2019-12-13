package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.HoldpeopleAdapter;
import com.example.app.Adapter.OnlinepeopleAdapter;
import com.example.app.Entity.Holdpeople;
import com.example.app.Entity.Onlinepeople;

import java.util.ArrayList;

public class OnlineModel {

    private static ArrayList<Onlinepeople> mArrayList;
    private static OnlinepeopleAdapter mAdapter;

    public static void initData() {
        mArrayList = new ArrayList<Onlinepeople>();
        for (int i = 0; i < 10; i++) {
            String type;
            if(i==0){
                type = "管理员";
            }else{
                type = "0";
            }
            Onlinepeople i1 = new Onlinepeople("ID2698456", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h5.jpg", "胡楠"+i, "", type);
            mArrayList.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
        mAdapter = new OnlinepeopleAdapter(context, mArrayList);
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

        mAdapter.setOnItemClickListener(new OnlinepeopleAdapter.OnItemClickListener() {
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


    public static void Add(RecyclerView mRecyclerView,Onlinepeople entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }
}
