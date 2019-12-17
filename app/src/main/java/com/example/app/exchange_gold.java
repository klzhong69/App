package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class exchange_gold extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView41)
    QMUIRadiusImageView imageView41;
    @BindView(R.id.imageView76)
    ImageView imageView76;
    @BindView(R.id.textView87)
    TextView textView87;
    @BindView(R.id.imageView43)
    QMUIRadiusImageView imageView43;
    @BindView(R.id.imageView80)
    ImageView imageView80;
    @BindView(R.id.textView89)
    TextView textView89;
    @BindView(R.id.textView166)
    TextView textView166;
    @BindView(R.id.textView167)
    TextView textView167;
    @BindView(R.id.imageView142)
    ImageView imageView142;
    @BindView(R.id.textView168)
    TextView textView168;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_gold);
        ButterKnife.bind(this);
        title.setText("兑换金币");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.but:
                break;
        }
    }
}
