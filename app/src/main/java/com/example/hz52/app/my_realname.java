package com.example.hz52.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_realname extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView97)
    TextView textView97;
    @BindView(R.id.textView98)
    TextView textView98;
    @BindView(R.id.textView99)
    TextView textView99;
    @BindView(R.id.textView100)
    TextView textView100;
    @BindView(R.id.textView101)
    TextView textView101;
    @BindView(R.id.but)
    QMUIRoundButton but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_realname);
        ButterKnife.bind(this);
        title.setText("实名认证");
        subtitle.setText("");
    }

    @OnClick({R.id.fold,  R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.but:
                Intent intent2 = new Intent(my_realname.this, my_realname_name.class);
                startActivity(intent2);
                
                break;
        }
    }



    @Override
    public void onBackPressed() {
        this.finish();
    }
}
