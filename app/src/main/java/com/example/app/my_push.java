package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_push extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView119)
    ImageView imageView119;
    @BindView(R.id.imageView112)
    ImageView imageView112;
    @BindView(R.id.textView137)
    TextView textView137;
    @BindView(R.id.textView138)
    TextView textView138;
    @BindView(R.id.switch2)
    Switch switch2;
    @BindView(R.id.switch3)
    Switch switch3;
    @BindView(R.id.textView151)
    TextView textView151;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_push);
        ButterKnife.bind(this);
        title.setText("推送通知设置");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.switch2, R.id.switch3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.switch2:
                break;
            case R.id.switch3:
                break;
        }
    }
}
