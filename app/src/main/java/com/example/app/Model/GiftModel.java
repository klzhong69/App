package com.example.app.Model;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.RoomgiftAdapter;
import com.example.app.Adapter.RoomheadAdapter;
import com.example.app.Entity.Roomgift;
import com.example.app.Entity.Roomhead;
import com.example.app.Entity.Roomtxt;
import com.example.app.chatroom;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class GiftModel {


    private static ArrayList<Roomgift> mData;
    private static RoomgiftAdapter mAdapters;

    public static void initData() {
        mData = new ArrayList<Roomgift>();

        for(int i=0;i<10;i++){

            Roomgift i1 = new Roomgift("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "玫瑰花", "10");
            mData.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView gridview) {
        //创建适配器，将数据传递给适配器
         mAdapters = new RoomgiftAdapter(context, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 4);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new RoomgiftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Observable<View> observable = Observable.defer(new Callable<ObservableSource<? extends View>>() {
                    @Override
                    public ObservableSource<? extends View> call() throws Exception {
                        return Observable.just(view);
                    }
                });
                observable.subscribe(chatroom.observers);


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

    public static void Add(RecyclerView mRecyclerView, Roomgift entity){
        mAdapters.addData(mData.size(), entity);
        mRecyclerView.smoothScrollToPosition(mData.size());
    }
}
