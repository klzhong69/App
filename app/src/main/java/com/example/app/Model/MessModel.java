package com.example.app.Model;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.MessageAdapter;
import com.example.app.Entity.Message;
import com.example.app.Messages;
import com.example.app.R;
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.Conver;
import com.example.app.chat;
import com.example.app.chatroom;
import com.example.app.cofig.DateUtil;
import com.example.app.cofig.Initialization;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mConverDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class MessModel {

    private static  ArrayList<Message> mArrayList;
    private static List<Conver> list;

    public static void initData() {
        mArrayList = new ArrayList<Message>();

        try{
           // list = mConverDao.queryAll();
            for(int i=0;i<5;i++){
                int sum = 0;
                if(i==0){
                    sum = 3;
                }
                Message i1 = new Message("苗苗"+i, "你好", "19:20", sum+"", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", i+"");
                mArrayList.add(i1);

            }
        }catch (Exception ignored){
        }


    }

    public static void initrecycler(Context context, FragmentActivity fragmentActivity,RecyclerView recycler10, int a) {
        //适配器
        MessageAdapter mAdapter = new MessageAdapter(context, mArrayList);
        //设置适配器adapter
        recycler10.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler10.setLayoutManager(layoutManager);
        recycler10.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(a==0){
                    Intent intent2 = new Intent(context, chat.class);
                    //intent2.putExtra("conver",list.get(position).getConversations());
                    //intent2.putExtra("sendid",list.get(position).getSendId());
                    intent2.putExtra("sendname","苗苗"+position);
                    intent2.putExtra("sendsrc","https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg");
                    context.startActivity(intent2);
                    fragmentActivity.overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                }else{
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(5);
                        }
                    });
                    observable.subscribe(chatroom.observer);
                }

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
        recycler10.setItemAnimator(defaultItemAnimator);
    }


}
