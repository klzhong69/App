package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication.cofig.ListViewAdapter;
import com.example.myapplication.utils.Faxan;
import com.example.myapplication.utils.MyApp;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class List extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.listMode)
    ListView mListView;

    private static final String REFRESH_HEADER_PULLDOWN = "下拉可以刷新";
    private static final String REFRESH_HEADER_REFRESHING = "正在刷新...";
    private static final String REFRESH_HEADER_LOADING = "正在加载...";
    private static final String REFRESH_HEADER_RELEASE = "释放立即刷新";
    private static final String REFRESH_HEADER_FINISH = "刷新完成";
    private static final String REFRESH_HEADER_FAILED = "刷新失败";
    private static final String REFRESH_HEADER_SECONDARY = "释放进入二楼";
    private static final String REFRESH_HEADER_LASTTIME = "上次更新 M-d HH:mm";

    private java.util.List<Faxan> mArrayList;
    private int layoutParamsHeight =0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);
        unbinder = ButterKnife.bind(this, view);
        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);

        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setScrollableWhenRefreshing(true));

        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                System.out.println("aaa");
                //refreshlayout.autoRefresh();
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                System.out.println("bbb");
                for(int i=0;i<10;i++){
                    Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
                    mArrayList.add(i1);
                }

                //refreshlayout.autoLoadMore();//自动加载
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });

        //初始化数据
        init();

        //创建Adapater
        ListViewAdapter adapter = new ListViewAdapter(getContext(), mArrayList);
        //设置Adapter
        mListView.setAdapter(adapter);


        //设置item点击监听事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), mArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //这里接收到广播和数据，进行处理就是了

    }

    private void init() {
        mArrayList = new ArrayList();
        for(int i=0;i<10;i++){
            Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
            mArrayList.add(i1);
        }
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        double are = (double) width/430;
        double h = (350+170+190*2)*are;
        double b = 155*are;
        layoutParamsHeight = (int) (h+10*b+60);

    }

    public static List newInstance() {
        Bundle args = new Bundle();
        List fragment = new List();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
