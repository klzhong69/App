package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.cofig.MyImageLoader;
import com.example.myapplication.cofig.ScreenSlidePagerAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Home extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tabSegments)
    QMUITabSegment tabSegment;
    @BindView(R.id.viewpagers)
    ViewPager viewPager;
    private ArrayList<String> imagePath;
    private List<Fragment> myFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, view);
        myFragment = new ArrayList<>();
        myFragment.add(new icon1());
        myFragment.add(new icon2());
        myFragment.add(new icon3());
        myFragment.add(new icon4());
        myFragment.add(new icon5());
        initData();
        View();
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static Home newInstance() {
        Bundle args = new Bundle();
        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




    private void initData() {

        imagePath = new ArrayList<>();
        imagePath.add("https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png");
        imagePath.add("https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png");
        imagePath.add("https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png");

    }

    private void View() {

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(imagePath);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initView() {

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()){

            //选中的item
            @Override
            public Fragment getItem(int position) {
                return myFragment.get(position);
            }


            @Override
            public int getCount() {
                if (myFragment == null) {
                    return 0;
                } else {
                    return myFragment.size();
                }
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
        tabSegment.setItemSpaceInScrollMode(QMUIDisplayHelper.dp2px(getContext(), 16));

        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {//当某个 Tab 被选中时会触发
                if(index == 4){
                    Intent intent = new Intent(getContext(), Main2Activity.class);
                    startActivity(intent);

                }
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
