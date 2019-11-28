package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.utils.MyApp;
import com.example.myapplication.cofig.MyImageLoader;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Home extends Fragment {


    public static final String AD_DOWNLOAD_ACTION = "det";
    private ViewPager viewPager;


    private Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    LocalBroadcastManager broadcastManager;
    private QMUITabSegment tabSegment;
    private ArrayList<String> imagePath;
    private List<Fragment> myFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, view);

        tabSegment = view.findViewById(R.id.tabSegments);

        viewPager = view.findViewById(R.id.viewpagers);
        //tabSegment1.setVisibility(View.VISIBLE);//使控件可见

        myFragment = new ArrayList<>();
        myFragment.add(new icon1());
        myFragment.add(new icon2());
        myFragment.add(new icon3());
        myFragment.add(new icon4());
        myFragment.add(new icon5());
        viewPager.setFocusable(false);
        viewPager.setOffscreenPageLimit(1);


        initData();
        View();
        initView();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        /*scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                Log.e(TAG, "onScrollChange: " + scrollX +"---" + scrollY + "----" +oldScrollX + "---" + oldScrollY );

                if (scrollY > oldScrollY) {//向下滚动
                    Log.i(TAG, "Scroll DOWN");
                    System.out.println("Scroll DOWN");
                }
                if (scrollY < oldScrollY) {//向上滚动
                    Log.i(TAG, "Scroll UP");
                    System.out.println("Scroll UP");
                }

                if (scrollY == 0) {// 滚动到顶
                    Log.i(TAG, "TOP SCROLL");
                }
                // 滚动到底
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i(TAG, "BOTTOM SCROLL");
                }

                //判断某个控件是否可见
                Rect scrollBounds = new Rect();
                tabSegment.getHitRect(scrollBounds);
                if (tabSegment.getLocalVisibleRect(scrollBounds)) {//可见
                    Log.e(TAG, "onScrollChange:  第3个可见");
                } else {//完全不可见
                    Log.e(TAG, "onScrollChange:  第3个不可见");
                }


                Log.e(TAG, "onScrollChange: ------------" + scrollY +"------"+ tabSegment.getTop() );
                //判断某个控件是否滚到顶部
                if (scrollY == tabSegment.getTop()){
                    Log.i(TAG, "onScrollChange: ------top-------" );
                    System.out.println("onScrollChange UP");
                }

                Log.e(TAG, "onScrollChange: bottmo" + scrollY +"-----"+ (tabSegment.getTop() + tabSegment.getHeight()) );


            }
        });*/
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

    @Override
    public void onResume() {
        super.onResume();
        receiveAdDownload();
    }

    /**
     * 注册广播接收器
     */
    private void receiveAdDownload() {
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AD_DOWNLOAD_ACTION);
        broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
    }

    BroadcastReceiver mAdDownLoadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resource = (int)intent.getSerializableExtra("det");
                ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
                layoutParams.height = resource;
                viewPager.setLayoutParams(layoutParams);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(mAdDownLoadReceiver);
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


        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

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


        int normalColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_gray_6);

        int selectColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_blue);

        tabSegment.setDefaultNormalColor(normalColor);
        tabSegment.setDefaultSelectedColor(selectColor);
        tabSegment.addTab(new QMUITabSegment.Tab("所有"));
        tabSegment.addTab(new QMUITabSegment.Tab("音乐"));
        tabSegment.addTab(new QMUITabSegment.Tab("CV"));
        tabSegment.addTab(new QMUITabSegment.Tab("电台"));
        tabSegment.addTab(new QMUITabSegment.Tab("广场"));
        tabSegment.setupWithViewPager(viewPager, false);
        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);
        tabSegment.setHasIndicator(true);  //是否需要显示indicator
        tabSegment.setIndicatorPosition(false);//true 时表示 indicator 位置在 Tab 的上方, false 时表示在下方
        tabSegment.setIndicatorWidthAdjustContent(true);

        tabSegment.setItemSpaceInScrollMode(QMUIDisplayHelper.dp2px(Objects.requireNonNull(getContext()), 16));

        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {//当某个 Tab 被选中时会触发
                if (index == 4) {
                    getActivity().onBackPressed();
                    Intent intent = new Intent(getContext(), Main2Activity.class);
                    startActivity(intent);

                } else {
                    //这里接收到广播和数据，进行处理就是了
                    ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
                    MyApp application = ((MyApp) getContext().getApplicationContext());
                    Map<Integer,Integer> a = application.getScores();
                    layoutParams.height = a.get(index);
                    viewPager.setLayoutParams(layoutParams);
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
