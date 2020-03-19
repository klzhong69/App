package com.example.hz52.app.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.FriendsAdapter;
import com.example.hz52.app.Entity.Friends;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.R;
import com.example.hz52.app.Sqlentity.Conver;
import com.example.hz52.app.chat;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mConverDao;
import com.example.hz52.app.homepage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

public class MessFriendsModel {

    private static ArrayList<Friends> mArrayList;
    public static FriendsAdapter mAdapter;
    private static JsonArray jsonArray;

    public static void initData(Context context, int a, RecyclerView recycler12) {
        mArrayList = new ArrayList<Friends>();
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        MyApp application = ((MyApp) context.getApplicationContext());
        String url = "";
        if (a == 1) {

            url = "/app/user/getFollows?token=" + token;

        } else if (a == 2) {

            url = "/app/user/getFans?token=" + token;

        }
        OkGo.<String>post(application.getUrl() + url)
                .params("userId", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            if (a == 1) {
                                jsonArray = prexiew.getData().getAsJsonArray("follows");
                            } else if (a == 2) {
                                jsonArray = prexiew.getData().getAsJsonArray("fans");
                            }
                            if (jsonArray != null) {
                                for (int i = 0; i < jsonArray.size(); i++) {
                                    Friends i1 = new Friends(jsonArray.get(i).getAsJsonObject().get("uniqueId").getAsLong(),jsonArray.get(i).getAsJsonObject().get("nickname").getAsString(), jsonArray.get(i).getAsJsonObject().get("signtureText").getAsString(), jsonArray.get(i).getAsJsonObject().get("avatarUrl").getAsString(), jsonArray.get(i).getAsJsonObject().get("gender").getAsString());
                                    mArrayList.add(i1);
                                }
                                initrecycler(context, recycler12);
                            }

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(context, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }



    public static void initrecycler(Context context, RecyclerView recycler12) {
        //适配器
        mAdapter = new FriendsAdapter(context, mArrayList);
        //设置适配器adapter
        recycler12.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler12.setLayoutManager(layoutManager);
        recycler12.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView = view.findViewById(R.id.imageView18);
                TextView textView = view.findViewById(R.id.textView66);
                TextView textView1 = view.findViewById(R.id.textView67);
                ImageView imageView1 = view.findViewById(R.id.imageView67);
                ImageView imageView2 = view.findViewById(R.id.imageView79);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent8 = new Intent(context, homepage.class);
                        intent8.putExtra("id", mArrayList.get(position).getId());
                        context.startActivity(intent8);

                    }
                });

                imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                        String userid = sp.getString("userid", "");
                        String usersrc = sp.getString("avatarUrl", "");
                        String username = sp.getString("nickname", "");
                        if (mConverDao.query(mArrayList.get(position).getId(), Long.valueOf(userid)).size() == 0) {

                            long data = System.currentTimeMillis() / 1000;
                            Conver convers = new Conver();
                            convers.setSendsrc(mArrayList.get(position).getImagesrc());
                            convers.setSendname(mArrayList.get(position).getName());
                            convers.setSendId(mArrayList.get(position).getId());
                            convers.setUserId(Long.valueOf(userid));
                            convers.setData(data);
                            convers.setSum(0);
                            convers.setTxt("我是" + username);
                            convers.setType(1);
                            mConverDao.insert(convers);
                            chat.send(userid, usersrc, username, mArrayList.get(position).getId(), "我是" + username, "user/" + mArrayList.get(position).getId());
                        }

                        Intent intent2 = new Intent(context, chat.class);
                        intent2.putExtra("conver", "user/" + mArrayList.get(position).getId());
                        intent2.putExtra("sendid", mArrayList.get(position).getId());
                        intent2.putExtra("sendname", mArrayList.get(position).getName());
                        context.startActivity(intent2);

                    }
                });

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
        recycler12.setItemAnimator(defaultItemAnimator);


    }

    public static void refule() {
        mAdapter.notifyDataSetChanged();
    }

    public static void Add(RecyclerView mRecyclerView, Friends entity) {
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

}
