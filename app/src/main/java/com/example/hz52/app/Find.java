package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
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
import com.example.hz52.app.Entity.Findlist;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.leefeng.lfrecyclerview.LFRecyclerView;
import me.leefeng.lfrecyclerview.OnItemClickListener;

public class Find extends Fragment implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange{


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
    private Unbinder unbinder;
    private ArrayList<Findlist> mArrayList;
    private QMUITipDialog tipDialog;
    private Context context;
    private int a = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();

        tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();

        Window window = Objects.requireNonNull(getActivity()).getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);

        ViewGroup.LayoutParams para1;
        para1 = view2.getLayoutParams();
        para1.height = height;
        view2.setLayoutParams(para1);

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);

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
        FindListAdapter mAdapter = new FindListAdapter(getContext(), mArrayList);
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
        tipDialog.dismiss();

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
                                tipDialog.dismiss();

                            } else {
                                Toast.makeText(getContext(), prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

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
                okgos(0);
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
                //okgos(a);
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

    @OnClick(R.id.textView116)
    public void onViewClicked() {
        Intent intent2 = new Intent(getContext(), find_make.class);
        startActivity(intent2);
        Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
