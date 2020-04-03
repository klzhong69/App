package com.example.hz52.app.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.MessageAdapter;
import com.example.hz52.app.Entity.Message;
import com.example.hz52.app.R;
import com.example.hz52.app.Sqlentity.Conver;
import com.example.hz52.app.chat;
import com.example.hz52.app.chatroom;
import com.example.hz52.app.cofig.DateUtil;
import com.example.hz52.app.dao.mConverDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class MessModel {

    public static ArrayList<Message> mArrayList;
    private static MessageAdapter mAdapter;

    public static void initData(Context context) {
        mArrayList = new ArrayList<Message>();
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        try {
            List<Conver> listalls = mConverDao.queryAll(Long.valueOf(userid));
            System.out.println("好友"+listalls.size());
            for (int i = 0; i < listalls.size(); i++) {
                Message i1 = new Message(listalls.get(i).getSendId(), listalls.get(i).getSendname(), listalls.get(i).getTxt(), DateUtil.stampToDates(String.valueOf((listalls.get(i).getData() * 1000))), listalls.get(i).getSum(), listalls.get(i).getSendsrc(), "1");
                mArrayList.add(i1);

            }
        } catch (Exception ignored) {}


    }

    public static void initrecycler(Context context, FragmentActivity fragmentActivity, RecyclerView recycler10, int a) {
        //适配器
         mAdapter = new MessageAdapter(context, mArrayList);
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
                if (a == 0) {
                    Intent intent2 = new Intent(context, chat.class);
                    intent2.putExtra("conver", "user/" + mArrayList.get(position).getUserid());
                    intent2.putExtra("sendid", mArrayList.get(position).getUserid());
                    intent2.putExtra("sendname", mArrayList.get(position).getName());
                    context.startActivity(intent2);
                } else {
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(position);
                        }
                    });
                    observable.subscribe(chatroom.observermess);
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

    public static void recly(){
        mAdapter.notifyDataSetChanged();

    }

}
