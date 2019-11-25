package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.banner)
    Banner banner;
    private ArrayList<String> imagePath;

    private List<Faxan> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {

        imagePath = new ArrayList<>();
        imagePath.add("https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png");
        imagePath.add("https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png");
        imagePath.add("https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png");

    }

    private void initView() {

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

    private void initWeather() {
        //苦力活动，请忽略
        Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", 288, 635, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
        weatherList.add(i1);
        Faxan i2 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", 288, 635, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png");
        weatherList.add(i2);
        Faxan i3 = new Faxan("芭比Uki宝贝祝大叔生日快乐", "[主持]芭比uu3号小可", "热门", 288, 635, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
        weatherList.add(i3);

    }
}
