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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.SwitchAdapter;
import com.example.hz52.app.Sqlentity.User;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.dao.mUserDao;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_switch);
        ButterKnife.bind(this);
        Initialization.setupDatabaseUser(this);
        title.setText("切换账户");
        subtitle.setText("");

        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        initData(Long.parseLong(userid));
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
                SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                sp.edit().putLong("userid", user.get(position).getId()).apply();
                Intent intent = new Intent(my_switch.this, MainActivity.class);
                intent.putExtra("id", 4);
                startActivity(intent);
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

    private void initData(long userid) {
        user = new ArrayList<User>();
        try {
            user  = mUserDao.queryBuilder();
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getId().equals(userid)) {
                    user.get(i).setState(1);
                }
                System.out.println("ID"+user.get(i).getId());
            }
        }catch (Exception ignored){}
    }
    @OnClick({R.id.fold, R.id.imageView44, R.id.textView37, R.id.imageView114})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.imageView44:
            case R.id.textView37:
            case R.id.imageView114:
                Intent intent1 = new Intent(this, login.class);
                intent1.putExtra("type",1);
                startActivity(intent1);
                overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
        }
    }



    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
