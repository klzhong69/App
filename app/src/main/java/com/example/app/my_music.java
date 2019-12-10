package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FamilyViewAdapter;
import com.example.app.Adapter.MusicViewAdapter;
import com.example.app.Entity.Familysea;
import com.example.app.Entity.Mymusic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class my_music extends AppCompatActivity {

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recycler5)
    RecyclerView recycler5;
    private List<Mymusic> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        ButterKnife.bind(this);

        initData();
        //适配器
        MusicViewAdapter mAdapter = new MusicViewAdapter(this, mArrayList);
        //设置适配器adapter
        recycler5.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler5.setLayoutManager(layoutManager);
        recycler5.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MusicViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(my_music.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(my_music.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler5.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Mymusic>();
        for(int i=0;i<6;i++){
            Mymusic i1 = new Mymusic("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "星坠-天空的幻想-林晓夜","03.00", "0");
            mArrayList.add(i1);
        }


    }
}
