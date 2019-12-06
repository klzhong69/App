package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ListViewAdapter;
import com.example.myapplication.entity.Faxan;
import com.example.myapplication.entity.MyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;

public class icon1 extends Fragment {

    public static Observable<Integer> observable;
    private List<Faxan> mArrayList;
    private int layoutParamsHeight = 0;
    public static final String AD_DOWNLOAD_ACTION1 = "det1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.papge, container, false);
        //初始化数据
        mArrayList = new ArrayList<Faxan>();

        init();

        RecyclerView mListView = view.findViewById(R.id.list);
        //创建适配器，将数据传递给适配器

        //适配器
        ListViewAdapter mAdapter = new ListViewAdapter(getContext(), mArrayList);
        //设置适配器adapter
        mListView.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mListView.setLayoutManager(layoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(getContext(), position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        mListView.setItemAnimator(defaultItemAnimator);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void init() {

        Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
        mArrayList.add(i1);
        Faxan i2 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png");
        mArrayList.add(i2);
        Faxan i3 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
        mArrayList.add(i3);


        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        double are = (double) width/430;
        double h = (350+170+190*2)*are;
        double b = 155*are;
        layoutParamsHeight = (int) (h+3*b+60);

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(layoutParamsHeight);
                e.onComplete();
            }
        });
        observable1.subscribe(Home.observer);


        observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(layoutParamsHeight);
            }
        });
        /*MyApp application = ((MyApp) getContext().getApplicationContext());
        application.setH1(layoutParamsHeight);
        Intent intent = new Intent(AD_DOWNLOAD_ACTION1);
        intent.putExtra("det1", layoutParamsHeight);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);*/
    }
}
