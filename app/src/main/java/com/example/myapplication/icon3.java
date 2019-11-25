package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class icon3 extends Fragment {

    private List<String> mArrayList = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        //初始化数据
        init();
        ListView mListView = view.findViewById(R.id.list );
        TextView textView = view.findViewById(R.id.textView34);
        //创建Adapater
        ListViewAdapter adapter = new ListViewAdapter(getContext(), mArrayList);
        textView.setText("页面3");
        //设置Adapter
        mListView.setAdapter(adapter);


        //设置item点击监听事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), mArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            mArrayList.add("这是第" + i + "个View");
        }
    }
}
