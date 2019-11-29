package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.Constants;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private boolean mIsShowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        switchLanguage(Constants.langae);
        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离

    }



    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageView7.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView4.setClickable(true);
            }
        });
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        imageView7.setCameraDistance(scale);
        imageView4.setCameraDistance(scale);
    }

    // 翻转卡片
    public void flipCard() {
        // 正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(imageView7);
            mLeftInSet.setTarget(imageView4);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOutSet.setTarget(imageView4);
            mLeftInSet.setTarget(imageView7);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                int id = 1;
                String a = "ads";
                intent.putExtra("id", id);
                intent.putExtra("ids", a);
                startActivity(intent);
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.button2:
                flipCard();
                break;
            case R.id.button3:
                if (Constants.langae.equals("zh")) {
                    Constants.langae = "en";
                } else {
                    Constants.langae = "zh";
                }
                Locale locale = new Locale(Constants.langae);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                Resources resources = getResources();
                resources.updateConfiguration(config, resources.getDisplayMetrics());

                //让之前打开的所有界面全部彻底关闭
                /*for (Activity activity : Constants.activityList) {
                    activity.finish();
                }
                //回到应用的首页
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                 */
                //刷新
                startActivity(new Intent(this, Main2Activity.class));

                break;
            case R.id.button4:
                Intent intent1 = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent1);
                break;
        }
    }

    //核心设置的代码
    protected void switchLanguage(String language) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        switch (language) {
            case "zh":
                config.locale = Locale.CHINESE;
                resources.updateConfiguration(config, dm);
                break;
            case "en":
                config.locale = Locale.ENGLISH;
                resources.updateConfiguration(config, dm);
                break;
            default:
                config.locale = Locale.US;
                resources.updateConfiguration(config, dm);
                break;
        }
    }
}
