package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.hz52.app.Adapter.FindmakeAdapter;
import com.example.hz52.app.Entity.Findmake;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.leefeng.lfrecyclerview.LFRecyclerView;
import me.leefeng.lfrecyclerview.OnItemClickListener;

public class find_make extends AppCompatActivity implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.relativeLayout11)
    RelativeLayout relativeLayout11;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler15)
    LFRecyclerView recycler15;
    private ArrayList<Findmake> mArrayList = new ArrayList<Findmake>();
    private FindmakeAdapter mAdapter;
    private String userid;
    private String token;
    private String nickname;
    private String avatarUrl;
    private int a = 1;
    public static Observer<JsonObject> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_make);
        ButterKnife.bind(this);
        title.setText("广播");
        subtitle.setText("");

        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        token = sp.getString("token", "");
        nickname = sp.getString("nickname", "");
        avatarUrl = sp.getString("avatarUrl", "");

       okgo(0,0);
    }

    @Override
    public void onResume() {
        super.onResume();
        observer = new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject JsonObject) {
                System.out.println("广播" + JsonObject);
                Findmake i1 = new Findmake(JsonObject.get("userId").getAsString(), JsonObject.get("avatarUrl").getAsString(), "", "", JsonObject.get("nickname").getAsString(), JsonObject.get("content").getAsString());
                mAdapter.addData(0, i1);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };

    }

    private void init() {
        recycler15.setLoadMore(true);//设置为可上拉加载,默认false,调用这个方法false可以去掉底部的“加载更多”
        recycler15.setRefresh(true);// 设置为可下拉刷新,默认true
        recycler15.setAutoLoadMore(true);//设置滑动到底部自动加载,默认false
        recycler15.setOnItemClickListener(this);// 条目点击,点击和长按监听
        recycler15.setLFRecyclerViewListener(this);//下拉刷新上拉加载监听
        recycler15.setScrollChangeListener(this);//滑动监听
        recycler15.hideTimeView();//隐藏时间,默认显示时间
        recycler15.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FindmakeAdapter(this, mArrayList);
        //设置适配器adapter
        recycler15.setAdapter(mAdapter);



        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler15.setItemAnimator(defaultItemAnimator);
    }


    private void okgo(int page, int type) {
        MyApp application = ((MyApp) this.getApplicationContext());

        OkGo.<String>post(application.getUrl() + "/app/user/getBroadcast?token=" + token)
                .params("page", page)
                .params("pageSize", 10)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            JsonArray broadcasts = prexiew.getData().getAsJsonArray("broadcasts");

                            if(broadcasts.size()>0){
                                for (int i = 0; i < broadcasts.size(); i++) {
                                    String userId = broadcasts.get(i).getAsJsonObject().get("userId").getAsString();
                                    String nickname = broadcasts.get(i).getAsJsonObject().get("nickname").getAsString();
                                    String content = broadcasts.get(i).getAsJsonObject().get("content").getAsString();
                                    String avatarUrl = broadcasts.get(i).getAsJsonObject().get("avatarUrl").getAsString();
                                    String createdTime = broadcasts.get(i).getAsJsonObject().get("createdTime").getAsString();
                                    Findmake i1 = new Findmake(userId, avatarUrl, "", "", nickname, content);
                                    if (type == 0) {
                                        mArrayList.add(i1);
                                    } else {
                                        mAdapter.addData(mArrayList.size(), i1);
                                    }
                                }
                                if (type == 0) {
                                    init();
                                }
                            }else{
                                recycler15.setFootText("没有数据");
                            }

                        } else  {

                            Toast.makeText(find_make.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @OnClick({R.id.fold, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.but:
                okgos();
                editText.setText("");
                break;
        }
    }


    private void okgos() {
        MyApp application = ((MyApp) this.getApplicationContext());

        OkGo.<String>post(application.getUrl() + "/app/user/sendBroadcast?token=" + token)
                .params("userId", userid)
                .params("broadcast", editText.getText().toString())
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(find_make.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();


                        } else  {
                            Toast.makeText(find_make.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycler15.stopRefresh(true);
                okgo(0, 1);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycler15.stopLoadMore();
                a++;
                okgo(a, 1);
            }
        }, 2000);
    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }

    @Override
    public void onClick(int i) {

    }

    @Override
    public void onLongClick(int i) {

    }
}
