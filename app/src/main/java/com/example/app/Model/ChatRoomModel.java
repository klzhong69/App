package com.example.app.Model;

import android.app.DialogFragment;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.RoomheadAdapter;
import com.example.app.Adapter.RoomtxtAdapter;
import com.example.app.Entity.Roomhead;
import com.example.app.Entity.Roomtxt;
import com.example.app.R;
import com.example.app.chatroom;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import static java.security.AccessController.getContext;

public class ChatRoomModel {

    private static ArrayList<Roomhead> mData;
    private static ArrayList<Roomtxt> mEntityList;
    private static RoomtxtAdapter mAdapter;

    public static void initData() {
        mData = new ArrayList<Roomhead>();
        mEntityList = new ArrayList<Roomtxt>();

        Roomhead i1 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "陌生人1", "", "");
        mData.add(i1);
        Roomhead i2 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h3.jpg", "陌生人2", "", "");
        mData.add(i2);
        Roomhead i3 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "陌生人3", "", "");
        mData.add(i3);
        Roomhead i4 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h5.jpg", "陌生人4", "", "");
        mData.add(i4);
        Roomhead i5 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h6.jpg", "陌生人5", "", "");
        mData.add(i5);
        Roomhead i6 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "陌生人6", "", "");
        mData.add(i6);
        Roomhead i7 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "陌生人7", "", "");
        mData.add(i7);
        Roomhead i8 = new Roomhead("0", "空位", "", "");
        mData.add(i8);

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
        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mAdapter.setOnItemClickListener(new RoomtxtAdapter.OnItemClickListener() {
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
        mRecyclerView.setItemAnimator(defaultItemAnimator);


    }


    public static void initrecyclers(Context context, RecyclerView gridview) {
        //创建适配器，将数据传递给适配器
        RoomheadAdapter mAdapters = new RoomheadAdapter(context, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 4);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new RoomheadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(mData.get(position).getUsersrc().equals("0")){
                    Observable<View> observable = Observable.defer(new Callable<ObservableSource<? extends View>>() {
                        @Override
                        public ObservableSource<? extends View> call() throws Exception {
                            return Observable.just(view);
                        }
                    });
                    observable.subscribe(chatroom.observers);
                }else{
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(8);
                        }
                    });
                    observable.subscribe(chatroom.observer);
                }

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

    public static void Add(RecyclerView mRecyclerView,Roomtxt entity){
        mAdapter.addData(mEntityList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mEntityList.size());
    }

}
