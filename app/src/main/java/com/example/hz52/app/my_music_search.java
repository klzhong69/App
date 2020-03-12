package com.example.hz52.app;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.MusicViewAdapter;
import com.example.hz52.app.Entity.Mymusic;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_music_search extends AppCompatActivity {

    @BindView(R.id.imageView37)
    ImageView imageView37;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.recycler3)
    RecyclerView recycler3;
    private ArrayList<Mymusic> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music_search);
        ButterKnife.bind(this);

        init();

        initData();
        //适配器
        MusicViewAdapter mAdapter = new MusicViewAdapter(this, mArrayList);
        //设置适配器adapter
        recycler3.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler3.setLayoutManager(layoutManager);
        recycler3.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MusicViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(my_music_search.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(my_music_search.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler3.setItemAnimator(defaultItemAnimator);
    }

    private void init() {

        searchView.setIconifiedByDefault(false);
        if (searchView == null) {
            return;
        } else {
            //获取到TextView的ID
            int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            //获取到TextView的控件
            TextView textView = (TextView) searchView.findViewById(id);
            //设置字体大小为14sp
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
            //设置提示文字颜色
            textView.setHintTextColor(getResources().getColor(R.color.back));

        }
        searchView.setFocusable(false);
        searchView.clearFocus();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("搜索音乐名称");//设置查询提示字符串

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String queryText) {
                System.out.println("onQueryTextChange:" + queryText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                //点击搜索
                System.out.println("onQueryTextSubmit:" + queryText);
                return true;
            }
        });

    }

    private void initData() {
        mArrayList = new ArrayList<Mymusic>();
        for (int i = 0; i < 10; i++) {
            Mymusic i1 = new Mymusic((long) i, "星坠-天空的幻想-林晓夜", "03.00", "2", "100%", "");
            mArrayList.add(i1);
        }
    }

    @OnClick(R.id.imageView37)
    public void onViewClicked() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }


    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
