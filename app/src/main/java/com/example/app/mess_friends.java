package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FriendsAdapter;
import com.example.app.Adapter.MessageAdapter;
import com.example.app.Entity.Friends;
import com.example.app.Entity.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class mess_friends extends AppCompatActivity {

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;
    @BindView(R.id.recycler12)
    RecyclerView recycler12;
    private List<Friends> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_friends);
        ButterKnife.bind(this);
        initData();

        init();

    }

    private void init() {
        //适配器
        FriendsAdapter mAdapter = new FriendsAdapter(this, mArrayList);
        //设置适配器adapter
        recycler12.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler12.setLayoutManager(layoutManager);
        recycler12.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mess_friends.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(mess_friends.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler12.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Friends>();
        for (int i = 0; i < 4; i++) {
            Friends i1 = new Friends("Ema90", "hi伙伴，明天一起直播吗",  "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "");
            mArrayList.add(i1);
        }

    }
}
