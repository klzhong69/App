package com.example.hz52.app;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hz52.app.cofig.Initialization;
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
    public static int height;
    public static int cardheight;

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

                Intent intent  =  new Intent(startup_page.this,MainActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();

            }
        }.start();

        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        height = resources.getDimensionPixelSize(resourceId);

        Rect outSize = new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(outSize);
        int left = outSize.left;
        int top = outSize.top;
        int right = outSize.right;
        int bottom = outSize.bottom;
        cardheight = bottom-height-200;



        System.out.println("系统栏高度"+height);
        System.out.println("left"+left+"/"+"top"+top+"/"+"right"+right+"/"+"bottom"+bottom);
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
