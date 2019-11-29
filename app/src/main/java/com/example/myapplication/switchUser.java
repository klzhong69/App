package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Dao.mUserDao;
import com.example.myapplication.Model.User;
import com.example.myapplication.cofig.UserListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class switchUser extends AppCompatActivity {

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.imageView41)
    ImageView imageView41;
    @BindView(R.id.bear)
    RelativeLayout bear;
    @BindView(R.id.userlist)
    ListView userlist;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.imageView44)
    ImageView imageView44;
    private int a = 0;
    private List<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);
        ButterKnife.bind(this);
        imageView41.setVisibility(View.INVISIBLE);

        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        Long userid = sp.getLong("userid", 0);
        textView6.setText(userid + "");
        init(userid);
        //通过构造函数来获取

        /*Gson gson = new Gson();
        String jsonArray = gson.toJson(user, new TypeToken<List<User>>() {
        }.getType());
        System.out.println(jsonArray);*/


        //创建Adapater
        UserListAdapter adapter = new UserListAdapter(this, user);
        //设置Adapter
        userlist.setAdapter(adapter);


        //设置item点击监听事件
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                sp.edit().putLong("userid", user.get(position).getId()).apply();
                Intent intent = new Intent(switchUser.this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
            }
        });
    }

    private void init(Long userid) {
        user = new ArrayList<>();
        user  = mUserDao.queryBuilder();

        for (int i = 0; i < user.size(); i++) {
            if (!user.get(i).getId().equals(userid)) {
                user.get(i).setState(false);
            }
        }
    }

    @OnClick({R.id.imageView40, R.id.imageView44, R.id.textView37})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView40:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                break;
            case R.id.imageView44:
            case R.id.textView37:

                break;
        }
    }
}
