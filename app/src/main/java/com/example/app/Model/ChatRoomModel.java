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

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;
import static java.security.AccessController.getContext;

public class ChatRoomModel {

    private static ArrayList<Roomhead> mData;
    private static ArrayList<Roomtxt> mEntityList;
    private static RoomtxtAdapter mAdapter;
    private static RoomheadAdapter mAdapters;

    private static boolean bIsBroadCaster;

    private static int mLocalUid;

    public static void initData( Boolean bIsBroadCasters) {
        bIsBroadCaster =  bIsBroadCasters;
        mData = new ArrayList<Roomhead>();
        mEntityList = new ArrayList<Roomtxt>();


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
         mAdapters = new RoomheadAdapter(context, mData);
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

    /**
     * 声网频道内业务回调
     */
    public static final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {

        @Override
        public void onUserJoined(final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 当有用户加入时，添加到用户列表
                    mData.add(new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "陌生人1", "", "",uid, 0, false, false));
                    mAdapters.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onUserOffline(final int uid, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 当用户离开时，从用户列表中清除
                    mData.remove(getUserIndex(uid));
                    mAdapters.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onUserMuteAudio(final int uid, final boolean muted) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 收到某个uid mute 状态后刷新人员列表
                    int index = getUserIndex(uid);
                    mData.get(index).setAudioMute(muted);
                    mAdapters.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onJoinChannelSuccess(final String channel, final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    // onJoinChannelSuccess 回调中，uid 不会为0
                    // 当 joinChannel api 中填入 0 时，agora 服务器会生成一个唯一的随机数，并在 onJoinChannelSuccess 回调中返回
                    mLocalUid = uid;
                    mData.clear();
                    /** 进入频道，主播状态下将自己加入到 user 列表**/
                    if (bIsBroadCaster) {
                        mData.add(new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "陌生人1", "", "",uid, 0, false, true));
                    }
                    if (mAdapters != null)
                        mAdapters.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onAudioVolumeIndication(final IRtcEngineEventHandler.AudioVolumeInfo[] speakers, int totalVolume) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (speakers != null) {
                        for (IRtcEngineEventHandler.AudioVolumeInfo audioVolumeInfo : speakers) {

                            /**
                             * 根据uid判断是他人还是自己， uid 0 默认是自己，根据 uid = 0 的取本地音量值，和joinchannelsuccess 内
                             * 本地的 LocalUid 对应
                             *
                             */
                            if (audioVolumeInfo.uid != 0) {
                                int index = getUserIndex(audioVolumeInfo.uid);
                                if (index >= 0) {
                                    mData.get(index).setAudioVolum(audioVolumeInfo.volume);
                                }
                            } else {
                                int index = getUserIndex(mLocalUid);
                                if (index >= 0) {
                                    mData.get(index).setAudioVolum(audioVolumeInfo.volume);
                                }
                            }
                        }
                        mAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
    };

    private static int getUserIndex(int uid) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getUid() == uid) {
                return i;
            }
        }
        return -1;
    }

    public static void Add(RecyclerView mRecyclerView,Roomtxt entity){
        mAdapter.addData(mEntityList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mEntityList.size());
    }

}
