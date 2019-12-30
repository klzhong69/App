package com.example.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.Adapter.GoldViewAdapter;
import com.example.app.Entity.Mygold;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class my_gold extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
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
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.buts)
    QMUIRoundButton buts;
    private List<Mygold> mData;
    private GoldViewAdapter mAdapters;
    private GridLayoutManager mLayoutManager;
    private Observer<Integer> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gold);
        ButterKnife.bind(this);
        title.setText("我的金币");
        subtitle.setText("");

        Intent intent = getIntent();
        String gold = intent.getStringExtra("gold");
        textView59.setText(gold);
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
                ImageView  imageView = view.findViewById(R.id.imageView41);
                TextView textView = view.findViewById(R.id.textView64);
                TextView textView2 = view.findViewById(R.id.textView65);

                observer = new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if(integer==0){
                            textView.setTextColor(Color.WHITE);
                            textView2.setTextColor(Color.WHITE);
                            Glide.with(view).load(R.color.tabbarcolor).into(imageView);
                        }else{
                            textView.setTextColor(Color.BLACK);
                            textView2.setTextColor(Color.BLACK);
                            Glide.with(view).load(R.color.qmui_config_color_white).into(imageView);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };

                for (int i = 0; i < mData.size(); i++) {
                    if (i == position) {

                        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                            @Override
                            public ObservableSource<? extends Integer> call() throws Exception {
                                return Observable.just(0);
                            }
                        });
                        observable.subscribe(observer);
                    } else {

                        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                            @Override
                            public ObservableSource<? extends Integer> call() throws Exception {
                                return Observable.just(1);
                            }
                        });
                        observable.subscribe(observer);
                    }
                }
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

    @Override
    protected void onResume() {
        super.onResume();






    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.title:

                break;
            case R.id.subtitle:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
