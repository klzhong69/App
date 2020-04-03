package com.example.hz52.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Model.FamilyHomeModel;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class family_home extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView39)
    TextView textView39;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.imageView39)
    ImageView imageView39;
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.textView41)
    TextView textView41;
    @BindView(R.id.textView42)
    TextView textView42;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.relative2)
    RelativeLayout relative2;
    @BindView(R.id.textView43)
    TextView textView43;
    @BindView(R.id.imageView17)
    QMUIRadiusImageView imageView17;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.imageView42)
    ImageView imageView42;
    @BindView(R.id.textView44)
    TextView textView44;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.imageView20)
    QMUIRadiusImageView imageView20;
    @BindView(R.id.imageView43)
    ImageView imageView43;
    @BindView(R.id.textView45)
    TextView textView45;
    @BindView(R.id.imageView21)
    QMUIRadiusImageView imageView21;
    @BindView(R.id.imageView22)
    QMUIRadiusImageView imageView22;
    @BindView(R.id.imageView44)
    ImageView imageView44;
    @BindView(R.id.textView46)
    TextView textView46;
    @BindView(R.id.imageView45)
    ImageView imageView45;
    @BindView(R.id.imageView46)
    ImageView imageView46;
    @BindView(R.id.imageView47)
    ImageView imageView47;
    @BindView(R.id.relative3)
    RelativeLayout relative3;
    @BindView(R.id.textView47)
    TextView textView47;
    @BindView(R.id.recycler4)
    RecyclerView recycler4;
    @BindView(R.id.relative4)
    RelativeLayout relative4;
    @BindView(R.id.imageView48)
    ImageView imageView48;
    @BindView(R.id.textView49)
    TextView textView49;
    @BindView(R.id.imageView49)
    ImageView imageView49;
    @BindView(R.id.textView50)
    TextView textView50;
    @BindView(R.id.relative5)
    RelativeLayout relative5;
    @BindView(R.id.largeLabel)
    RelativeLayout largeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_home);
        ButterKnife.bind(this);
        title.setText("【爱乐】AL粉丝团");
        subtitle.setText("");
        Context context = this;
        FamilyHomeModel.initData();
        FamilyHomeModel.initrecycler(context, recycler4);


    }

    @OnClick({R.id.fold, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
