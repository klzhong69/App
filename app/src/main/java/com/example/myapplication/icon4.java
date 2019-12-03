package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication.utils.Faxan;
import com.example.myapplication.cofig.ListViewAdapter;
import com.example.myapplication.utils.MyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class icon4 extends Fragment {

    private List<Faxan> mArrayList;
    private int layoutParamsHeight =0;
    public static final String AD_DOWNLOAD_ACTION4 = "det4";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.papge, container, false);


        //初始化数据
        init();

        ListView mListView = view.findViewById(R.id.list );
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
    }

    private void init() {
        mArrayList = new ArrayList();
        Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
        mArrayList.add(i1);
        Faxan i2 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png");
        mArrayList.add(i2);
        Faxan i3 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635","https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
        mArrayList.add(i3);
        Faxan i4 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
        mArrayList.add(i4);
        Faxan i5 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png");
        mArrayList.add(i5);
        Faxan i6 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", "288", "635","https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
        mArrayList.add(i6);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        double are = (double) width/430;
        double h = (350+170+190*2)*are;
        double b = 155*are;
        layoutParamsHeight = (int) (h+6*b+60);

        MyApp application = ((MyApp) getContext().getApplicationContext());
        application.setH4(layoutParamsHeight);

    }
}
