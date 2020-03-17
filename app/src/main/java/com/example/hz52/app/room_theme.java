package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.ThemeAdapter;
import com.example.hz52.app.Entity.Findlist;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Theme;
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

public class room_theme extends AppCompatActivity {

    private static ArrayList<Theme> mArrayList;
    private static ThemeAdapter mAdapter;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recyclerc12)
    RecyclerView recyclerc12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_theme);
        ButterKnife.bind(this);
        title.setText("主题背景");
        subtitle.setText("保存");
        initData();
        initrecycler();
    }

    @OnClick({R.id.fold, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.subtitle:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
        }
    }

    public static void initData() {
        mArrayList = new ArrayList<Theme>();
        for (int i = 0; i < 4; i++) {
            int type;
            if(i==0){
                type = 0;
            }else{
                type = 1;
            }
            Theme i1 = new Theme("https://momeak.oss-cn-shenzhen.aliyuncs.com/h5.jpg", "默认主题", "免费", type);
            mArrayList.add(i1);
        }

    }
//获取房间所有背景
    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String roomid = sp.getString("roomid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/room/getAllBackground?token=" + token)
                .params("userId", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            JsonArray broadcasts = prexiew.getData().getAsJsonArray("backgrounds");

                            if (broadcasts.size() > 0) {
                                for (int i = 0; i < broadcasts.size(); i++) {
                                    String isActive = broadcasts.get(i).getAsJsonObject().get("isActive").getAsString();
                                    String id = broadcasts.get(i).getAsJsonObject().get("id").getAsString();
                                    String smallPicUrl = broadcasts.get(i).getAsJsonObject().get("smallPicUrl").getAsString();
                                    String bigPicUrl = broadcasts.get(i).getAsJsonObject().get("bigPicUrl").getAsString();
                                    String backgroundName = broadcasts.get(i).getAsJsonObject().get("backgroundName").getAsString();
                                    String backgroundCategory = broadcasts.get(i).getAsJsonObject().get("backgroundCategory").getAsString();
                                }

                            }

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(room_theme.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
//设置房间主题背景
    private void okgos() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String roomid = sp.getString("roomid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/room/setBackground?token=" + token)
                .params("userId", userid)
                .params("roomId", roomid)
                .params("newBackgroundId", 2)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(room_theme.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(room_theme.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public  void initrecycler() {

        //创建适配器，将数据传递给适配器
        mAdapter = new ThemeAdapter(this, mArrayList);
        //设置适配器adapter
        recyclerc12.setAdapter(mAdapter);

        //多列布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerc12.setLayoutManager(mLayoutManager);


        recyclerc12.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
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
        recyclerc12.setItemAnimator(defaultItemAnimator);
    }



    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
