package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_diamond extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.textView59)
    TextView textView59;
    @BindView(R.id.textView60)
    TextView textView60;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.buts)
    QMUIRoundButton buts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diamond);
        ButterKnife.bind(this);
        title.setText("我的钻石");
        subtitle.setText("操作记录");
    }

    @OnClick({R.id.fold, R.id.subtitle, R.id.but, R.id.buts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.subtitle:
                Intent intent1 = new Intent(my_diamond.this, operation_record.class);
                startActivity(intent1);
                break;
            case R.id.but:
                Intent intent2 = new Intent(my_diamond.this, withdraw.class);
                startActivity(intent2);
                break;
            case R.id.buts:
                Intent intent3 = new Intent(my_diamond.this, exchange_gold.class);
                startActivity(intent3);
                break;
        }
    }
}
