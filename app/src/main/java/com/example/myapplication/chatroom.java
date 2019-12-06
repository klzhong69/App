package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.GridViewAdapter;
import com.example.myapplication.Adapter.RecyclerViewAdapter;
import com.example.myapplication.entity.BaseEntity;
import com.example.myapplication.entity.Chatroom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class chatroom extends AppCompatActivity {

    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.gridview)
    RecyclerView gridview;
    private Bitmap bitmap;
    private List<Chatroom> mData;
    private GridLayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;//适配器
    private GridViewAdapter mAdapters;//适配器
    private List<BaseEntity> mEntityList;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        ButterKnife.bind(this);
        mEntityList = new ArrayList<>();
        //初始化数据
        init();
        initrecycler();
    }

    private void init() {
        mData = new ArrayList<Chatroom>();
        Chatroom i1 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "陌生人1", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
        mData.add(i1);
        Chatroom i2 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png", "陌生人2", "");
        mData.add(i2);
        Chatroom i3 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png", "陌生人3", "");
        mData.add(i3);
        Chatroom i4 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png", "陌生人4", "");
        mData.add(i4);
        Chatroom i5 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png", "陌生人5", "");
        mData.add(i5);
        Chatroom i6 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png", "陌生人6", "");
        mData.add(i6);
        Chatroom i7 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png", "陌生人7", "");
        mData.add(i7);
        Chatroom i8 = new Chatroom("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png", "", "");
        mData.add(i8);

        //创建适配器，将数据传递给适配器
        mAdapters = new GridViewAdapter(this, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(this,4);
        gridview.setLayoutManager(mLayoutManager);


        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new GridViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(chatroom.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(chatroom.this, position + " Long click", Toast.LENGTH_SHORT).show();
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

    private void initrecycler() {
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
                Toast.makeText(chatroom.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(chatroom.this, position + " Long click", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.imageView4)
    public void onViewClicked() {
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer< Long >() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("bqt", "【订阅】" + aLong);
                        BaseEntity entity = new BaseEntity("周润发", "进入房间打赏流附近的司法所的飞机但是减肥i哦" + aLong, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
                        mAdapter.addData(mEntityList.size(), entity);
                        mRecyclerView.smoothScrollToPosition(mEntityList.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }
}
