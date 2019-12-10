package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FamilyHomeAdapter;
import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Adapter.ModifyViewAdapter;
import com.example.app.Entity.Familyhome;
import com.example.app.Entity.Modify;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class family_home extends AppCompatActivity {


    @BindView(R.id.recycler4)
    RecyclerView recycler4;
    private ArrayList<Familyhome> mData;
    private FamilyHomeAdapter mAdapters;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_home);
        ButterKnife.bind(this);

        init();

        //创建适配器，将数据传递给适配器
        mAdapters = new FamilyHomeAdapter(this, mData);
        //设置适配器adapter
        recycler4.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(this, 5);
        recycler4.setLayoutManager(mLayoutManager);


        recycler4.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new FamilyHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(family_home.this, position + " click", Toast.LENGTH_SHORT).show();
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
        recycler4.setItemAnimator(defaultItemAnimator);
    }

    private void init() {
        mData = new ArrayList<Familyhome>();
        for (int i = 0; i < 12; i++) {
            Familyhome i1 = new Familyhome("芭比uu","https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
            mData.add(i1);
        }

    }
}
