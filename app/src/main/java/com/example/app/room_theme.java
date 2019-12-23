package com.example.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Adapter.ThemeAdapter;
import com.example.app.Adapter.ThemeAdapter;
import com.example.app.Entity.Holdpeople;
import com.example.app.Entity.Theme;

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
}
