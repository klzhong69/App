package com.example.hz52.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hz52.app.MQ.MqttMessageService;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class startup_page extends AppCompatActivity {

    @BindView(R.id.imageViewi3)
    QMUIRadiusImageView imageViewi3;
    @BindView(R.id.textView188)
    TextView textView188;
    @BindView(R.id.textView189)
    TextView textView189;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);
        ButterKnife.bind(this);
        countDownTimer = new CountDownTimer(3000+500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView189.setText(String.valueOf((millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(startup_page.this, MainActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
                finish();
            }
        }.start();

    }

    @OnClick({R.id.imageViewi3, R.id.textView188, R.id.textView189})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageViewi3:
            case R.id.textView188:
            case R.id.textView189:
                countDownTimer.cancel();
                countDownTimer.onFinish();
                break;
        }
    }
}
