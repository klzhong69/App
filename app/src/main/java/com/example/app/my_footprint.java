package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FootprintAdapter;
import com.example.app.Entity.Footprint;
import com.example.app.Entity.MyApp;
import com.example.app.cofig.DateUtil;
import com.example.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_footprint extends AppCompatActivity {


    @BindView(R.id.recycler7)
    RecyclerView recycler7;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    private List<Footprint> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_footprint);
        ButterKnife.bind(this);
        initData();
        title.setText("我的足迹");
        subtitle.setText("");
        //适配器
        FootprintAdapter mAdapter = new FootprintAdapter(this, mArrayList);
        //设置适配器adapter
        recycler7.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler7.setLayoutManager(layoutManager);
        recycler7.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FootprintAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(my_footprint.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(my_footprint.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler7.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Footprint>();

        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        // Long userid = sp.getLong("userid", 0);
        Long userid = Long.valueOf("700647775");
        OkGo.<String>post(application.getUrl()+"/app/user/getRoomHistory?token="+application.getToken())
                .params("userId",userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray jsonArray =  prexiew.getData().getAsJsonArray("room");
                        if(prexiew.getCode()==0){
                            for (int i = 0; i < jsonArray.size(); i++) {

                                Footprint i1 = new Footprint(jsonArray.get(i).getAsJsonObject().get("coverUrl").getAsString(), jsonArray.get(i).getAsJsonObject().get("ownerName").getAsString(), DateUtil.stampToDates(jsonArray.get(i).getAsJsonObject().get("createdTime").getAsString()), jsonArray.get(i).getAsJsonObject().get("roomName").getAsString());
                                mArrayList.add(i1);
                            }

                        }else if(prexiew.getCode()==40000){
                            Toast.makeText(my_footprint.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.title:
                title.setText("我的足迹");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
