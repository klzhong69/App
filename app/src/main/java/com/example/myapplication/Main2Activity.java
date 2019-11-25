package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.list)
    ListView mListView;
    private List<String> mArrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        //初始化数据
        init();

        //创建Adapater
        ListViewAdapter adapter = new ListViewAdapter(this, mArrayList);

        //设置Adapter
        mListView.setAdapter(adapter);


        //设置item点击监听事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, mArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            mArrayList.add("这是第" + i + "个View");
        }
    }
    /*private void initWeather() {
        //苦力活动，请忽略
        Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", 288, 635, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
        mArrayList.add(i1);
        Faxan i2 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", 288, 635, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png");
        mArrayList.add(i2);
        Faxan i3 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", 288, 635, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
        mArrayList.add(i3);

    }*/
}
