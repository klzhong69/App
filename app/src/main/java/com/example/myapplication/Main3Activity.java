package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.cofig.LiRecyclerViewAdapter;
import com.example.myapplication.cofig.PathImageBit;
import com.example.myapplication.cofig.RecyclerViewAdapter;
import com.example.myapplication.utils.BaseEntity;
import com.example.myapplication.utils.LiwuBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.security.AccessController.getContext;


public class Main3Activity extends AppCompatActivity {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.button11)
    Button button11;
    @BindView(R.id.button12)
    Button button12;
    @BindView(R.id.button13)
    Button button13;
    @BindView(R.id.lirecyclerview)
    RecyclerView lirecyclerview;

    private List<BaseEntity> mEntityList;
    private List<LiwuBase> mEntityLists;
    private int a = 0;
    private RecyclerViewAdapter mAdapter;//适配器
    private GridLayoutManager mLayoutManager;

    private LiRecyclerViewAdapter mAdapters;//适配器
    private int b = 0;
    private int c = 0;
    private Handler handel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        mEntityList = new ArrayList<>();
        mEntityLists = new ArrayList<>();

        initrecycler();
        initrecyclers();

    }

    private void initrecycler(){
        //创建适配器，将数据传递给适配器
        mAdapter = new RecyclerViewAdapter(this, mEntityList);
        //设置适配器adapter
        mRecyclerView.setAdapter(mAdapter);

       /* //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);*/

        /*//多列布局
        mLayoutManager = new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(mLayoutManager);*/


        //布局管理器
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        if (mAdapter.getItemCount() > 0) {
            mLinearLayoutManager.scrollToPositionWithOffset(mAdapter.getItemCount() - 1, Integer.MIN_VALUE);
        }


        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Main3Activity.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(Main3Activity.this, position + " Long click", Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        mRecyclerView.setItemAnimator(defaultItemAnimator);
    }

    private void initrecyclers(){
        //创建适配器，将数据传递给适配器
        mAdapters = new LiRecyclerViewAdapter(this, mEntityLists);
        //设置适配器adapter
        lirecyclerview.setAdapter(mAdapters);

        //布局管理器
        LinearLayoutManager mLinearLayoutManagers = new LinearLayoutManager(this);
        mLinearLayoutManagers.setStackFromEnd(true);
        if (mAdapters.getItemCount() > 0) {
            mLinearLayoutManagers.scrollToPositionWithOffset(mAdapters.getItemCount() - 1, Integer.MIN_VALUE);
        }

        lirecyclerview.setLayoutManager(mLinearLayoutManagers);

        lirecyclerview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new LiRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Main3Activity.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(Main3Activity.this, position + " Long click", Toast.LENGTH_SHORT).show();
                mAdapters.removeData(position);
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator1 = new DefaultItemAnimator();
        defaultItemAnimator1.setAddDuration(300);
        defaultItemAnimator1.setRemoveDuration(200);
        lirecyclerview.setItemAnimator(defaultItemAnimator1);

    }

    private void initData() {
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (mEntityLists.size() == 3) {
                    mAdapters.removeData(0);
                    lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                    c++;
                }
                b++;
                LiwuBase entitys1 = new LiwuBase("x" + b);
                mAdapters.addData(mEntityLists.size(), entitys1);
                lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                runLayoutAnimation(lirecyclerview);
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(mEntityLists.size()>0){
                                mAdapters.removeData(0);
                                lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                            }

                        }
                    }, 5000);
            }

            public void onFinish() {

            }
        }.start();
    }

    @OnClick({R.id.button6, R.id.button11, R.id.button12, R.id.button13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button6:
                BaseEntity entity = new BaseEntity("周润发", "进入房间" + a, "https://momeak.oss-cn-shenzhen.aliyuncs.com/l3.png");
                mAdapter.addData(mEntityList.size(), entity);
                a++;
                mRecyclerView.smoothScrollToPosition(mEntityList.size());
                break;
            case R.id.button11:
                if (mEntityLists.size() == 3) {
                    mAdapters.removeData(0);
                    lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                }

                LiwuBase entitys1 = new LiwuBase("x10");
                mAdapters.addData(mEntityLists.size(), entitys1);
                lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                runLayoutAnimation(lirecyclerview);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(mEntityLists.size()>0){
                                mAdapters.removeData(0);
                                lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                            }

                        }
                    }, 5000);

                break;
            case R.id.button12:
                initData();
                break;
            case R.id.button13:
                mAdapters.removeData(0);
                lirecyclerview.smoothScrollToPosition(mEntityLists.size());
                break;
        }
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

}


