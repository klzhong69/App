package com.example.hz52.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class friends_card extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView22)
    ImageView imageView22;
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.imageView28)
    QMUIRadiusImageView imageView28;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.imageView50)
    ImageView imageView50;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.imageView52)
    ImageView imageView52;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.imageView53)
    QMUIRadiusImageView imageView53;
    @BindView(R.id.textView34)
    TextView textView34;
    @BindView(R.id.imageView54)
    ImageView imageView54;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.imageView56)
    ImageView imageView56;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.imageView57)
    QMUIRadiusImageView imageView57;
    @BindView(R.id.textView39)
    TextView textView39;
    @BindView(R.id.imageView58)
    ImageView imageView58;
    @BindView(R.id.textView41)
    TextView textView41;
    @BindView(R.id.textView42)
    TextView textView42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_card);
        ButterKnife.bind(this);
        title.setText("购买挚友卡");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.title:
                title.setText("购买挚友卡");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
