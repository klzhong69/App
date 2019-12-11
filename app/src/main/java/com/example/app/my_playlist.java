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

import com.example.app.Adapter.MusicViewAdapter;
import com.example.app.Adapter.PlaylistAdapter;
import com.example.app.Entity.Mymusic;
import com.example.app.Entity.Playlist;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class my_playlist extends AppCompatActivity {

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recycler6)
    RecyclerView recycler6;
    private ArrayList<Playlist> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);
        ButterKnife.bind(this);

        initData();
        //适配器
        PlaylistAdapter mAdapter = new PlaylistAdapter(this, mArrayList);
        //设置适配器adapter
        recycler6.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler6.setLayoutManager(layoutManager);
        recycler6.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new PlaylistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(my_playlist.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(my_playlist.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler6.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Playlist>();
        for (int i = 0; i < 4; i++) {
            Playlist i1 = new Playlist("默认歌单", "["+"26"+"]");
            mArrayList.add(i1);
        }


    }
}
