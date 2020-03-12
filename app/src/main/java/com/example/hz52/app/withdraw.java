package com.example.hz52.app;

import android.content.Intent;
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

public class withdraw extends AppCompatActivity {

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
    @BindView(R.id.imageView143)
    ImageView imageView143;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        title.setText("提现");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.imageView43, R.id.imageView80, R.id.textView89, R.id.textView167, R.id.but, R.id.imageView143})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.imageView43:
            case R.id.imageView80:
            case R.id.textView89:
            case R.id.textView167:
            case R.id.imageView143:
                Intent intent2 = new Intent(withdraw.this, account.class);
                startActivity(intent2);
                break;
            case R.id.but:
                break;

        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
