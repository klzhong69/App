package com.example.app.Model;

import android.content.Context;
import android.preference.TwoStatePreference;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.RoomheadAdapter;
import com.example.app.Adapter.RoomtxtAdapter;
import com.example.app.Entity.Roomhead;
import com.example.app.Entity.Roomtxt;
import com.example.app.Sqlentity.User;
import com.example.app.chatroom;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class ChatRoomModel {

    private static ArrayList<Roomtxt> mEntityList = new ArrayList<Roomtxt>();
    public static ArrayList<Roomhead> mUserList = new ArrayList<Roomhead>();
    private static RoomtxtAdapter mAdapter;
    public static RoomheadAdapter mAdapters;

    public static void initData(){
        for(int i=0;i<8;i++){
            Roomhead i1 = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h1.jpg","","","",0,0,false,false);
            mUserList.add(i1);
        }
    }


    public static void initrecycler(Context context, RecyclerView mRecyclerView) {
        //创建适配器，将数据传递给适配器
        mAdapter = new RoomtxtAdapter(context, mEntityList);
        //设置适配器adapter
        mRecyclerView.setAdapter(mAdapter);


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



    public static void initrecyclers(Context context, RecyclerView mRecyclerView) {
        //创建适配器，将数据传递给适配器
        mAdapters = new RoomheadAdapter(context, mUserList);
        //设置适配器adapter
        mRecyclerView.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 4);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new RoomheadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (mUserList.get(position).getName().equals("")) {
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(position);
                        }
                    });
                    observable.subscribe(chatroom.observers);
                } else {
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

                Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> call() throws Exception {
                        return Observable.just(position);
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
        mRecyclerView.setItemAnimator(defaultItemAnimator);


    }

    /**
     * 上麦界面
     */
    public static void showBroadCast(RtcEngine mRtcEngine,Long mLocalUid,int position,Roomhead roomhead) {

        // 设置为主播
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        // role 改变后需要将自己添加到用户列表
        if (mLocalUid != 0) {
            mUserList.set(position,roomhead);
            mAdapters.notifyItemChanged(position);
            if(mRtcEngine.isSpeakerphoneEnabled()){
                mRtcEngine.setEnableSpeakerphone(true);
            }else{
                mRtcEngine.setEnableSpeakerphone(false);
            }
        }


    }

    /**
     * 下麦界面
     */
    public static void showAudience(RtcEngine mRtcEngine,int index) {
        //设为观众
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        mRtcEngine.muteAllRemoteAudioStreams(true);
        mRtcEngine.muteLocalAudioStream(true);
        mRtcEngine.enableLocalAudio(false);
        mUserList.remove(index);
        mAdapters.notifyItemChanged(index);


    }

    public static void Add(RecyclerView mRecyclerView,Roomtxt entity){
        mAdapter.addData(mEntityList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mEntityList.size());
    }
}
