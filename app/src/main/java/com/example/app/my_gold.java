package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.bumptech.glide.Glide;
import com.example.app.Adapter.GoldViewAdapter;
import com.example.app.Entity.Mygold;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class my_gold extends AppCompatActivity {

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.textView59)
    TextView textView59;
    @BindView(R.id.textView60)
    TextView textView60;
    @BindView(R.id.textView57)
    TextView textView57;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imageView63)
    ImageView imageView63;
    @BindView(R.id.textView63)
    TextView textView63;

    private List<Mygold> mData;
    private GoldViewAdapter mAdapters;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gold);
        ButterKnife.bind(this);

        init();

        //创建适配器，将数据传递给适配器
        mAdapters = new GoldViewAdapter(this, mData);
        //设置适配器adapter
        recyclerView.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new GoldViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView = view.findViewById(R.id.imageView41);
                TextView textView = view.findViewById(R.id.textView64);
                //Glide.with(view).load(R.drawable.mess).into(imageView);
                /*for (int i = 0; i < mData.size(); i++) {
                    if (i == position) {

                    } else {


                    }
                }*/
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
        recyclerView.setItemAnimator(defaultItemAnimator);
    }

    private void init() {
        mData = new ArrayList<Mygold>();
        for (int i = 0; i < 8; i++) {
            Mygold i1 = new Mygold("600", "6元");
            mData.add(i1);
        }

    }
}
