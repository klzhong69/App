package com.example.app.Model;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.MessageAdapter;
import com.example.app.Entity.Message;
import com.example.app.Entity.MyApp;
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

    public static void initData(Context context) {
        mArrayList = new ArrayList<Message>();
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        MyApp application = ((MyApp) context.getApplicationContext());
        try{
            if(application.getUsermess().size()>0){
                System.out.println("大雨");
                List<Conver> listall =  mConverDao.queryAll(Long.valueOf(userid));
                for(int i = 0; i<mConverDao.queryAll(Long.valueOf(userid)).size(); i++){
                    Conver conver = new Conver();
                    Conver convers = new Conver();
                    int a=0;
                    int b=0;
                    for(int j=0;j<application.getUsermess().size();j++){
                        if(application.getUsermess().get(j).getSendId().equals(listall.get(i).getSendId())){
                            a++;
                            conver.setSendsrc(application.getUsermess().get(j).getSendsrc());
                            conver.setSendname(application.getUsermess().get(j).getSendsrc());
                            conver.setSendId(application.getUsermess().get(j).getSendId());
                            conver.setData(application.getUsermess().get(j).getData());
                            conver.setSum(a);
                            conver.setUserId(Long.valueOf(userid));
                            conver.setTxt(application.getUsermess().get(j).getTxt());
                            conver.setType(1);
                        }else{
                            b++;
                            convers.setSendsrc(application.getUsermess().get(j).getSendsrc());
                            convers.setSendname(application.getUsermess().get(j).getSendsrc());
                            convers.setSendId(application.getUsermess().get(j).getSendId());
                            convers.setData(application.getUsermess().get(j).getData());
                            convers.setSum(b);
                            convers.setUserId(Long.valueOf(userid));
                            convers.setTxt(application.getUsermess().get(j).getTxt());
                            convers.setType(1);
                        }
                    }
                    if(a!=0){
                        mConverDao.update(conver);
                    }
                    if(b!=0){
                        mConverDao.insert(convers);
                    }



                }

            }

            List<Conver> listalls = mConverDao.queryAll(Long.valueOf(userid));
            System.out.println("数量："+listalls.size());
            for(int i=0;i<listalls.size();i++){
                Message i1 = new Message(listalls.get(i).getSendId(), listalls.get(i).getSendname(), listalls.get(i).getTxt(), DateUtil.stampToDates(String.valueOf((listalls.get(i).getData()*1000))), listalls.get(i).getSum(), listalls.get(i).getSendsrc(),"1");
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
                    intent2.putExtra("conver","user/"+mArrayList.get(position).getUserid());
                    intent2.putExtra("sendid",mArrayList.get(position).getUserid());
                    intent2.putExtra("sendname",mArrayList.get(position).getName());
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
