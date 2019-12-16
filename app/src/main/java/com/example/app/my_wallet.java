package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_wallet extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView17)
    QMUIRadiusImageView imageView17;
    @BindView(R.id.imageView59)
    ImageView imageView59;
    @BindView(R.id.textView51)
    TextView textView51;
    @BindView(R.id.imageView35)
    QMUIRadiusImageView imageView35;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView53)
    TextView textView53;
    @BindView(R.id.textView54)
    TextView textView54;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.imageView60)
    ImageView imageView60;
    @BindView(R.id.textView52)
    TextView textView52;
    @BindView(R.id.imageView36)
    QMUIRadiusImageView imageView36;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.textView55)
    TextView textView55;
    @BindView(R.id.textView56)
    TextView textView56;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.imageView61)
    ImageView imageView61;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.textView59)
    TextView textView59;
    @BindView(R.id.textView60)
    TextView textView60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        title.setText("我的钱包");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.title:
                title.setText("我的钱包");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }
}
