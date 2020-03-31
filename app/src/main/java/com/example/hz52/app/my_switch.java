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
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.SwitchAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Sqlentity.User;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mUserDao;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_switch extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.imageView44)
    ImageView imageView44;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.imageView114)
    ImageView imageView114;
    private List<User> user;
    private String userid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_switch);
        ButterKnife.bind(this);
        Initialization.setupDatabaseUser(this);
        title.setText("切换账户");
        subtitle.setText("");

        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid","");
        token = sp.getString("token","");
        initData();
        init();
    }



    private void initData() {
        user = new ArrayList<User>();
        try {
            user  = mUserDao.queryBuilder();
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getUserId().toString().equals(userid)) {
                    user.get(i).setState(1);
                }
            }
        }catch (Exception ignored){}
    }

    private void init() {
        //适配器
        SwitchAdapter mAdapter = new SwitchAdapter(this, user);
        //设置适配器adapter
        recyclerView2.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new SwitchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                okgo(user.get(position).getUserId());
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(my_switch.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recyclerView2.setItemAnimator(defaultItemAnimator);

    }

    private void okgo(Long userids) {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/user/getUserInfo?token=" + token)
                .params("userId",userids)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if(prexiew.getCode()==0){

                            String id = prexiew.getData().get("uniqueId").getAsString();
                            String name = prexiew.getData().get("nickname").getAsString();
                            String userima = prexiew.getData().get("avatarUrl").getAsString();
                            String token = prexiew.getData().get("token").getAsString();
                            String phone = prexiew.getData().get("phone").getAsString();
                            String birthday = prexiew.getData().get("birthday").getAsString();
                            String gender = prexiew.getData().get("gender").getAsString();

                            SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                            sp.edit().putString("userid", id).apply();
                            sp.edit().putString("token", token).apply();
                            sp.edit().putString("phone", phone).apply();
                            sp.edit().putString("nickname", name).apply();
                            sp.edit().putString("gender", gender).apply();
                            sp.edit().putString("birthday", birthday).apply();
                            sp.edit().putString("avatarUrl", userima).apply();
                            sp.edit().putString("login", "true").apply();

                            Intent intent1 = new Intent(my_switch.this, MainActivity.class);
                            intent1.putExtra("id", 4);
                            startActivity(intent1);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                        }else{

                            Toast.makeText(my_switch.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @OnClick({R.id.fold, R.id.imageView44, R.id.textView37, R.id.imageView114})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView44:
            case R.id.textView37:
            case R.id.imageView114:
                Intent intent1 = new Intent(this, login.class);
                intent1.putExtra("type",2);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
