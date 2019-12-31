package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FamilyHomeAdapter;
import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Adapter.HonorAdapter;
import com.example.app.Entity.Familyhome;
import com.example.app.Entity.Page;
import com.example.app.homepage;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class HomePageModel {

    private static ArrayList<Page> mData;
    private static GridViewAdapter mAdapters;
    private static HonorAdapter mAdapter;
    private static List<String> mDatas;
    private static List<String> mDatat;

    public static void initData(JsonArray honor,JsonArray photo,JsonArray giftWall) {
        mData = new ArrayList<Page>();
        mDatas = new ArrayList<String>();
        mDatat = new ArrayList<String>();

        for(int i=0;i<honor.size();i++){

            mDatas.add(honor.get(i).getAsJsonObject().get("picUrl").getAsString());

        }
        for(int i=0;i<photo.size();i++){


            mDatat.add(photo.get(i).getAsJsonObject().get("photoUrl").getAsString());
        }
        for(int i=0;i<giftWall.size();i++){
            Page i1 = new Page("宝马  x  241", "");
            mData.add(i1);

        }

    }

    public static void initrecycler(Context context, RecyclerView gridview) {
        //创建适配器，将数据传递给适配器
        mAdapters = new GridViewAdapter(context, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 4);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new GridViewAdapter.OnItemClickListener() {
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

    public static void initrecyclers(Context context, RecyclerView gridview) {
        //创建适配器，将数据传递给适配器
        mAdapter = new HonorAdapter(context, mDatas);
        //设置适配器adapter
        gridview.setAdapter(mAdapter);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 5);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new HonorAdapter.OnItemClickListener() {
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

    public static void initrecyclert(Context context, RecyclerView gridview) {
        //创建适配器，将数据传递给适配器
        mAdapter = new HonorAdapter(context, mDatat);
        //设置适配器adapter
        gridview.setAdapter(mAdapter);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 5);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new HonorAdapter.OnItemClickListener() {
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


    public static void Add(RecyclerView mRecyclerView,Page entity){
        mAdapters.addData(mData.size(), entity);
        mRecyclerView.smoothScrollToPosition(mData.size());
    }
}
