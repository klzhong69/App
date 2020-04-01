package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.Package1Adapter;
import com.example.hz52.app.Adapter.Package2Adapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Package1;
import com.example.hz52.app.Entity.Package2;
import com.example.hz52.app.cofig.DateUtil;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_package extends AppCompatActivity {


    @BindView(R.id.recycler9)
    RecyclerView recycler9;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView71)
    TextView textView71;
    @BindView(R.id.recycler8)
    RecyclerView recycler8;
    @BindView(R.id.relativeLayout9)
    RelativeLayout relativeLayout9;
    @BindView(R.id.textView72)
    TextView textView72;
    @BindView(R.id.textView73)
    TextView textView73;
    @BindView(R.id.recycler10)
    RecyclerView recycler10;
    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;
    private ArrayList<Package1> mData;
    private Package1Adapter mAdapter;
    private Package2Adapter mAdapters;
    private Package2Adapter mAdaptert;
    private ArrayList<Package2> mDatas;
    private ArrayList<Package2> mDatat;
    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_package);
        ButterKnife.bind(this);
        initData();
        title.setText("我的包裹");
        subtitle.setText("");

        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();

        init();
        inits();
        //创建适配器，将数据传递给适配器
        mAdapter = new Package1Adapter(this, mData);
        //设置适配器adapter
        recycler8.setAdapter(mAdapter);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 5);
        recycler8.setLayoutManager(mLayoutManager);


        recycler8.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new Package1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mData.size() - 1;
                if (position == del) {
                    Toast.makeText(my_package.this, "添加图片", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(my_package.this, position + " click", Toast.LENGTH_SHORT).show();
                }

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
        recycler8.setItemAnimator(defaultItemAnimator);
    }

    private void init() {
        //创建适配器，将数据传递给适配器
        mAdapters = new Package2Adapter(this, mDatas);
        //设置适配器adapter
        recycler9.setAdapter(mAdapters);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        recycler9.setLayoutManager(mLayoutManager);


        recycler9.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new Package2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mDatas.size() - 1;
                if (position == del) {
                    Toast.makeText(my_package.this, "添加图片", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(my_package.this, position + " click", Toast.LENGTH_SHORT).show();
                }

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
        recycler9.setItemAnimator(defaultItemAnimator);
    }


    private void inits() {
        //创建适配器，将数据传递给适配器
        mAdaptert = new Package2Adapter(this, mDatat);
        //设置适配器adapter
        recycler10.setAdapter(mAdaptert);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        recycler10.setLayoutManager(mLayoutManager);


        recycler10.setItemAnimator(new DefaultItemAnimator());

        mAdaptert.setOnItemClickListener(new Package2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mDatat.size() - 1;
                if (position == del) {
                    Toast.makeText(my_package.this, "添加图片", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(my_package.this, position + " click", Toast.LENGTH_SHORT).show();
                }

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
        recycler10.setItemAnimator(defaultItemAnimator);
    }


    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.title:
                title.setText("我的包裹");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }


    private void initData() {
        mData = new ArrayList<Package1>();
        mDatas = new ArrayList<Package2>();
        mDatat = new ArrayList<Package2>();

        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        MyApp application = ((MyApp) getApplicationContext());

        OkGo.<String>post(application.getUrl() + "/app/user/getPackage?token=" + token)
                .params("userId", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {


                            JsonArray jsonArray1 = prexiew.getData().getAsJsonArray("friendCards");
                            JsonArray jsonArray2 = prexiew.getData().getAsJsonArray("gifts");
                            JsonArray jsonArray3 = prexiew.getData().getAsJsonArray("headwears");

                            if (jsonArray1 != null) {
                                for (int i = 0; i < jsonArray1.size(); i++) {
                                    Package1 i1 = new Package1(jsonArray1.get(i).getAsJsonObject().get("picUrl").getAsString(), jsonArray1.get(i).getAsJsonObject().get("cardName").getAsString());
                                    mData.add(i1);
                                }
                            }
                            if (jsonArray2 != null) {
                                for (int i = 0; i < jsonArray2.size(); i++) {
                                    Package2 i2 = new Package2(jsonArray2.get(i).getAsJsonObject().get("smallPicUrl").getAsString(), jsonArray2.get(i).getAsJsonObject().get("giftName").getAsString(), "数量:" + jsonArray2.get(i).getAsJsonObject().get("amount").getAsString());
                                    mDatas.add(i2);
                                }
                            }
                            if (jsonArray3 != null) {
                                for (int i = 0; i < jsonArray3.size(); i++) {
                                    Package2 i3 = new Package2(jsonArray3.get(i).getAsJsonObject().get("headwearPicUrl").getAsString(), jsonArray3.get(i).getAsJsonObject().get("headwearName").getAsString(), "有效期:" + DateUtil.stampToDate(jsonArray2.get(i).getAsJsonObject().get("endTime").getAsString()));
                                    mDatat.add(i3);
                                }
                            }
                            tipDialog.dismiss();

                        } else  {
                            Toast.makeText(my_package.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }


    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
