package com.example.myapplication;


import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentPagerAdapter;

import androidx.viewpager.widget.ViewPager;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class Main3Activity extends AppCompatActivity {


    @BindView(R.id.tabSegments)
    QMUITabSegment tabSegment;
    @BindView(R.id.viewpagers)
    ViewPager viewPager;
    private List<Fragment>myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        myFragment = new ArrayList<>();
        myFragment.add(new icon1());
        myFragment.add(new icon2());
        myFragment.add(new icon3());
        myFragment.add(new icon4());
        myFragment.add(new icon5());
        initView();
    }

    private void initView() {

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            //选中的item
            @Override
            public Fragment getItem(int position) {
                return myFragment.get(position);
            }

            @Override
            public int getCount() {
                return 5;
            }


        });
        viewPager.setCurrentItem(0, false);

        tabSegment.addTab(new QMUITabSegment.Tab("所有"));
        tabSegment.addTab(new QMUITabSegment.Tab("音乐"));
        tabSegment.addTab(new QMUITabSegment.Tab("CV"));
        tabSegment.addTab(new QMUITabSegment.Tab("电台"));
        tabSegment.addTab(new QMUITabSegment.Tab("广场"));
        tabSegment.setupWithViewPager(viewPager, false);
        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);
        tabSegment.setItemSpaceInScrollMode(QMUIDisplayHelper.dp2px(this, 16));

        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {//当某个 Tab 被选中时会触发
                Log.i("bqt", "【onTabSelected】" + index);
                tabSegment.hideSignCountView(index);//根据 index 在对应的 Tab 上隐藏红点
            }

            @Override
            public void onTabReselected(int index) {//当某个 Tab 处于被选中状态下再次被点击时会触发
                Log.i("bqt", "【onTabReselected】" + index);
                tabSegment.hideSignCountView(index);//根据 index 在对应的 Tab 上隐藏红点
            }

            @Override
            public void onTabUnselected(int index) {//当某个 Tab 被取消选中时会触发
                Log.i("bqt", "【onTabUnselected】" + index);
            }

            @Override
            public void onDoubleTap(int index) {//当某个 Tab 被双击时会触发
                Log.i("bqt", "【onDoubleTap】" + index);
            }
        });
    }
}
