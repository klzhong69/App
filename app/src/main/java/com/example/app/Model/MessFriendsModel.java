package com.example.app.Model;

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

import com.example.app.Adapter.FriendsAdapter;
import com.example.app.Entity.Friends;
import com.example.app.Entity.MyApp;
import com.example.app.R;
import com.example.app.chat;
import com.example.app.cofig.Preview;
import com.example.app.homepage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;

import java.util.ArrayList;

public class MessFriendsModel {

    private static ArrayList<Friends> mArrayList;
    private static FriendsAdapter mAdapter;

    public static void initData(Context context,int a) {
        mArrayList = new ArrayList<Friends>();
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        String token = sp.getString("token","");
        MyApp application = ((MyApp) context.getApplicationContext());
        String url = "";
        if(a==1){

            url = "/app/user/getFollows?token="+token;

        }else if(a==2){

            url = "/app/user/getFans?token="+token;

        }
        OkGo.<String>post(application.getUrl() + url)
                .params("userId", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            JsonArray jsonArray = new JsonArray();
                            if(a==1){
                                 jsonArray = prexiew.getData().getAsJsonArray("follows");
                            }else if(a==2){
                                jsonArray = prexiew.getData().getAsJsonArray("fans");
                            }

                            for(int i=0;i<jsonArray.size();i++){
                                Friends i1 = new Friends(jsonArray.get(i).getAsJsonObject().get("nickname").getAsString(), jsonArray.get(i).getAsJsonObject().get("signtureText").getAsString(),  jsonArray.get(i).getAsJsonObject().get("avatarUrl").getAsString(), jsonArray.get(i).getAsJsonObject().get("gender").getAsString());
                                mArrayList.add(i1);
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
               ImageView imageView =  view.findViewById(R.id.imageView18);
                TextView textView =  view.findViewById(R.id.textView66);
                TextView textView1 =  view.findViewById(R.id.textView67);
                ImageView imageView1 =  view.findViewById(R.id.imageView67);

                imageView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(context, chat.class);
                            intent1.putExtra("sendname",mArrayList.get(position).getName());
                            intent1.putExtra("sendsrc",mArrayList.get(position).getImagesrc());
                            context.startActivity(intent1);

                        }
                    });

                Intent intent2 = new Intent(context, homepage.class);
                context.startActivity(intent2);
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

    public static void refule(){
        mAdapter.notifyDataSetChanged();
    }

    public static void Add(RecyclerView mRecyclerView,Friends entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

}
