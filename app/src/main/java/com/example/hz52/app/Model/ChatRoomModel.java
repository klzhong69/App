package com.example.hz52.app.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Adapter.RoomheadAdapter;
import com.example.hz52.app.Adapter.RoomtxtAdapter;
import com.example.hz52.app.Entity.Holdpeople;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Roomhead;
import com.example.hz52.app.Entity.Roomtxt;
import com.example.hz52.app.R;
import com.example.hz52.app.chatroom;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.homepage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class ChatRoomModel {

    private static ArrayList<Roomtxt> mEntityList = new ArrayList<Roomtxt>();
    public static ArrayList<Roomhead> mUserList;
    private static RoomtxtAdapter mAdapter;
    public static RoomheadAdapter mAdapters;
    public static GridLayoutManager mLayoutManager;

    public static void initData(){
        mUserList = new ArrayList<Roomhead>();
        for(int i=0;i<8;i++){
            Roomhead i1 = new Roomhead("","","","",0L,0,false,false);
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
         mLayoutManager = new GridLayoutManager(context, 4);
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
        mRtcEngine.muteAllRemoteAudioStreams(false);
        mRtcEngine.muteLocalAudioStream(false);
        mRtcEngine.enableLocalAudio(true);
        // role 改变后需要将自己添加到用户列表
        if (mLocalUid != 0) {
            locsuser(position,roomhead);
            if(mRtcEngine.isSpeakerphoneEnabled()){
                mRtcEngine.setEnableSpeakerphone(true);
            }else{
                mRtcEngine.setEnableSpeakerphone(false);
            }
        }


    }


    /**
     * 禁言
     */
    public static void self(RtcEngine mRtcEngine,int id) {
        //mRtcEngine.muteLocalAudioStream(true);
        mRtcEngine.muteAllRemoteAudioStreams (false);
        mRtcEngine.muteRemoteAudioStream(id,true);
    }


    public static void locsuser(int position,Roomhead roomhead){
        System.out.println("坑位"+position);
        mUserList.set(position,roomhead);
        mAdapters.notifyItemChanged(position);
    }

    /**
     * 下麦界面
     */
    public static void showAudience(RtcEngine mRtcEngine,int position,Long userid) {
        //设为观众
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        mRtcEngine.muteAllRemoteAudioStreams(true);
        mRtcEngine.muteLocalAudioStream(true);
        mRtcEngine.enableLocalAudio(false);
        int uids = 0;
        if(position == 0){
            uids = getUserIndex(mUserList.get(position).getUid());
        }else{
            uids = getUserIndex(userid);
        }

        // 当用户离开时，从用户列表中清除
        Roomhead i1 = new Roomhead("", "", "", "", 0L, 0, false, false);
        mUserList.set(uids, i1);
        mAdapters.notifyDataSetChanged();


    }

    private static int getUserIndex(Long uid) {

        for (int i = 0; i < mUserList.size(); i++) {
            if (mUserList.get(i).getUid().equals(uid)) {
                return i;
            }
        }
        return -1;
    }

    public static void Add(RecyclerView mRecyclerView,Roomtxt entity){
        mAdapter.addData(mEntityList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mEntityList.size());
    }

    public static void okgo(Context context,Long roomid) {
        MyApp application = ((MyApp) context.getApplicationContext());
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        String nickname = sp.getString("nickname", "");
        String avatarUrl = sp.getString("avatarUrl", "");
        String gender = sp.getString("gender", "");
        OkGo.<String>post(application.getUrl() + "/app/room/applyUpperWheat?token=" + token)
                .params("userId", userid)
                .params("roomId", roomid)
                .params("nickname", nickname)
                .params("avatarUrl", avatarUrl)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(context, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public static void okgoall(Context context,RecyclerView mRecyclerView,Long roomid,int type) {
        MyApp application = ((MyApp) context.getApplicationContext());
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/room/getUpperWheatApplication?token=" + token)
                .params("userId", userid)
                .params("roomId", roomid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray application = prexiew.getData().getAsJsonArray("users");
                        if(prexiew.getCode()==0){
                            PaimaiModel.initData(application);
                            if(type == 0){
                                PaimaiModel.initrecycler(context, mRecyclerView);
                            }else{
                                PaimaiModel.initrecyclers(context, mRecyclerView);
                            }

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(context, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public static void okgodel(Context context,Long roomid,Long userid) {
        MyApp application = ((MyApp) context.getApplicationContext());
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/room/cancelUpperWheat?token=" + token)
                .params("userId", userid)
                .params("roomId", roomid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray application = prexiew.getData().getAsJsonArray("users");
                        if(prexiew.getCode()==0){
                            PaimaiModel.initData(application);

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(context, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
