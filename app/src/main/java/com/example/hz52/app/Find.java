package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.hz52.app.Adapter.FindListAdapter;
import com.example.hz52.app.Adapter.FindmakeAdapter;
import com.example.hz52.app.Entity.Findlist;
import com.example.hz52.app.Entity.Findmake;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.leefeng.lfrecyclerview.LFRecyclerView;
import me.leefeng.lfrecyclerview.OnItemClickListener;

public class Find extends Fragment implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange {


    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.textView115)
    TextView textView115;
    @BindView(R.id.textView116)
    TextView textView116;
    @BindView(R.id.relative9)
    RelativeLayout relative9;
    @BindView(R.id.recycler15)
    LFRecyclerView recycler15;
    @BindView(R.id.refreshLayout)
    ConstraintLayout refreshLayout;
    @BindView(R.id.textView117)
    TextView textView117;
    @BindView(R.id.textView118)
    TextView textView118;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    private Unbinder unbinder;
    private ArrayList<Findlist> mArrayList;
    private ArrayList<Findmake> mArrayLists = new ArrayList<Findmake>();
    private Context context;
    private int are = 0;
    private int bre = 0;
    private int card = 0;
    private String userid;
    private String token;
    private String nickname;
    private String avatarUrl;
    private FindListAdapter mAdapter;
    private FindmakeAdapter mAdapters;
    public static Observer<JsonObject> observer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();

        Window window = Objects.requireNonNull(getActivity()).getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);

        ViewGroup.LayoutParams para1;
        para1 = view2.getLayoutParams();
        para1.height = startup_page.height;
        view2.setLayoutParams(para1);

        SharedPreferences sp = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        token = sp.getString("token", "");
        nickname = sp.getString("nickname", "");
        avatarUrl = sp.getString("avatarUrl", "");

        if (card == 0) {
            textView115.setVisibility(View.VISIBLE);
            textView116.setVisibility(View.VISIBLE);
            textView117.setVisibility(View.GONE);
            textView118.setVisibility(View.GONE);
            imageView18.setVisibility(View.GONE);
        } else {
            textView115.setVisibility(View.GONE);
            textView116.setVisibility(View.GONE);
            textView117.setVisibility(View.VISIBLE);
            textView118.setVisibility(View.VISIBLE);
            imageView18.setVisibility(View.VISIBLE);
        }

        okgo(0, 0);
        initData();

        init();

        return view;

    }

    private void init() {

        recycler15.setLoadMore(true);//设置为可上拉加载,默认false,调用这个方法false可以去掉底部的“加载更多”
        recycler15.setRefresh(true);// 设置为可下拉刷新,默认true
        recycler15.setAutoLoadMore(true);//设置滑动到底部自动加载,默认false
        recycler15.setOnItemClickListener(this);// 条目点击,点击和长按监听
        recycler15.setLFRecyclerViewListener(this);//下拉刷新上拉加载监听
        recycler15.setScrollChangeListener(this);//滑动监听
        recycler15.setItemAnimator(new DefaultItemAnimator());

        if (card == 0) {
            mAdapter = new FindListAdapter(getContext(), mArrayList);
            //设置适配器adapter
            recycler15.setAdapter(mAdapter);
        } else {
            mAdapters = new FindmakeAdapter(getContext(), mArrayLists);
            //设置适配器adapter
            recycler15.setAdapter(mAdapters);
        }
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler15.setItemAnimator(defaultItemAnimator);

    }

    private void initData() {
        mArrayList = new ArrayList<Findlist>();
        for (int i = 0; i < 8; i++) {
            int sum;
            if (i > 5) {
                sum = i - 5;
            } else {
                sum = i + 1;
            }
            Findlist i1 = new Findlist("PHakamile Sikali", "Muria Moura", "2345", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h" + sum + ".jpg", "", "热门", "CV", "德国");
            mArrayList.add(i1);
        }

    }

    private void okgos(int page) {
        MyApp application = ((MyApp) getContext().getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/page/getHot")
                .params("page", page)
                .params("pageSize", 10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            JsonArray broadcasts = prexiew.getData().getAsJsonArray("hot");

                            if (broadcasts.size() > 0) {
                                for (int i = 0; i < broadcasts.size(); i++) {
                                    String hot = broadcasts.get(i).getAsJsonObject().get("hot").getAsString();
                                    String ownerId = broadcasts.get(i).getAsJsonObject().get("ownerId").getAsString();
                                    String uniqueId = broadcasts.get(i).getAsJsonObject().get("uniqueId").getAsString();
                                    String coverUrl = broadcasts.get(i).getAsJsonObject().get("coverUrl").getAsString();
                                    String roomName = broadcasts.get(i).getAsJsonObject().get("roomName").getAsString();
                                    String welcomeText = broadcasts.get(i).getAsJsonObject().get("welcomeText").getAsString();
                                    JsonArray tag = broadcasts.get(i).getAsJsonObject().get("tag").getAsJsonArray();
                                    String ownerName = broadcasts.get(i).getAsJsonObject().get("ownerName").getAsString();
                                    Findlist i1 = new Findlist(roomName, welcomeText, hot, coverUrl, "", tag.get(0).getAsString(), tag.get(1).getAsString(), tag.get(2).getAsString());
                                    mArrayList.add(i1);
                                }

                            } else {
                                recycler15.setFootText("没有数据");
                            }
                        } else {
                            Toast.makeText(getContext(), prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void okgo(int page, int inder) {
        MyApp application = ((MyApp) Objects.requireNonNull(getActivity()).getApplicationContext());

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

                            if (broadcasts.size() > 0) {
                                for (int i = 0; i < broadcasts.size(); i++) {
                                    String userId = broadcasts.get(i).getAsJsonObject().get("userId").getAsString();
                                    String nickname = broadcasts.get(i).getAsJsonObject().get("nickname").getAsString();
                                    String content = broadcasts.get(i).getAsJsonObject().get("content").getAsString();
                                    String avatarUrl = broadcasts.get(i).getAsJsonObject().get("avatarUrl").getAsString();
                                    String createdTime = broadcasts.get(i).getAsJsonObject().get("createdTime").getAsString();
                                    Findmake i1 = new Findmake(userId, avatarUrl, "", "", nickname, content);

                                    if (inder == 0) {
                                        mArrayLists.add(i1);
                                    } else {
                                        mAdapters.addData(mArrayLists.size(), i1);
                                    }


                                }
                            } else {
                                recycler15.setFootText("没有数据");
                            }

                        } else {

                            Toast.makeText(getContext(), prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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
                mAdapters.addData(0, i1);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static Find newInstance() {
        Bundle args = new Bundle();
        Find fragment = new Find();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycler15.stopRefresh(true);
                if (card == 0) {
                    okgos(0);
                } else {
                    okgo(0, 1);
                }

            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycler15.stopLoadMore();
                if (card == 0) {
                    are++;
                    //okgos(a);
                } else {
                    bre++;
                    okgo(bre, 1);
                }

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

    @OnClick({R.id.textView115, R.id.textView116, R.id.textView117, R.id.textView118, R.id.imageView18})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView115:
            case R.id.textView117:
                card = 0;
                init();
                textView115.setVisibility(View.VISIBLE);
                textView116.setVisibility(View.VISIBLE);
                textView117.setVisibility(View.GONE);
                textView118.setVisibility(View.GONE);
                imageView18.setVisibility(View.GONE);
                break;
            case R.id.textView116:
            case R.id.textView118:
                card = 1;
                init();
                textView117.setVisibility(View.VISIBLE);
                textView118.setVisibility(View.VISIBLE);
                textView115.setVisibility(View.GONE);
                textView116.setVisibility(View.GONE);
                imageView18.setVisibility(View.VISIBLE);
                break;
            case R.id.imageView18:
                Intent intent2 = new Intent(getContext(), find_make.class);
                startActivity(intent2);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }
}
