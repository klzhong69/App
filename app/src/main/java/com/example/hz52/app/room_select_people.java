package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.SelectPeopleAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Onlinepeople;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class room_select_people extends AppCompatActivity {

    private static ArrayList<Onlinepeople> mArrayList;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recycler16)
    RecyclerView recycler16;
    private SelectPeopleAdapter mAdapter;
    private int id;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select_people);
        ButterKnife.bind(this);

        subtitle.setText("");
        Intent intent = getIntent();
        id = intent.getIntExtra("type", 0);
        if(id==0){
            title.setText("管理员");
        }else if(id==1){
            title.setText("黑名单");
        }

        initData();
        initrecycler();
    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.title:
                break;
            case R.id.subtitle:
                break;
        }
    }

    public  void initData() {
        mArrayList = new ArrayList<Onlinepeople>();
        for (int i = 0; i < 10; i++) {
            Onlinepeople i1 = new Onlinepeople("ID2698456", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h5.jpg", "胡楠"+i, "", id+"");
            mArrayList.add(i1);
        }

    }


    //获取房间黑名单
    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String roomid = sp.getString("roomid", "");
        String token = sp.getString("token", "");
        if(id==0){
            url = application.getUrl() + "/app/room/getBlack?token=" + token;
        }else if(id==1){
            url = application.getUrl() + "/app/room/getAdmin?token=" + token;
        }
        OkGo.<String>post(url)
                .params("userId", userid)
                .params("roomId", roomid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            JsonArray blacks = prexiew.getData().getAsJsonArray("blacks");

                        } else {
                            Toast.makeText(room_select_people.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //添加房间黑名单
    private void okgos() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String roomid = sp.getString("roomid", "");
        String token = sp.getString("token", "");
        if(id==0){
            url = application.getUrl() + "/app/room/addBlack?token=" + token;
        }else if(id==1){
            url = application.getUrl() + "/app/room/addAdmin?token=" + token;
        }
        OkGo.<String>post(url)
                .params("userId", userid)
                .params("roomId", roomid)
                .params("who", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(room_select_people.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(room_select_people.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //删除房间黑名单
    private void okgod() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String roomid = sp.getString("roomid", "");
        String token = sp.getString("token", "");
        if(id==0){
            url = application.getUrl() + "/app/room/removeBlack?token=" + token;
        }else if(id==1){
            url = application.getUrl() + "/app/room/removeAdmin?token=" + token;
        }
        OkGo.<String>post(url)
                .params("userId", userid)
                .params("roomId", roomid)
                .params("who", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(room_select_people.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(room_select_people.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    public  void initrecycler() {
        //适配器
        mAdapter = new SelectPeopleAdapter(this, mArrayList);
        //设置适配器adapter
        recycler16.setAdapter(mAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler16.setLayoutManager(layoutManager);
        recycler16.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new SelectPeopleAdapter.OnItemClickListener() {
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
        recycler16.setItemAnimator(defaultItemAnimator);


    }



    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
