package com.example.app;

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

public class my_change_photo extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.imageView20)
    QMUIRadiusImageView imageView20;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.textView150)
    TextView textView150;
    @BindView(R.id.imageView111)
    ImageView imageView111;

    private Boolean butnum = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_change_photo);
        ButterKnife.bind(this);
        Intent intent =getIntent();
        butnum= intent.getBooleanExtra("butnum",false);
        imageView111.setVisibility(View.GONE);
        title.setText("换绑手机号");
        subtitle.setText("");
        if(butnum){
            editText4.setText("");
            editText4.setHint("请输入新的手机号");
            editText5.setText("");
            but.setText("确认绑定");
        }else{

            editText4.setText("");
            editText4.setHint("请输入当前手机号");
            editText5.setText("");
            but.setText("确认解绑");
        }

    }

    @OnClick({R.id.fold, R.id.but, R.id.textView150, R.id.imageView111})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.but:
                if(butnum){
                    this.finish();
                }else{
                    this.finish();
                    Intent intent2 = new Intent(my_change_photo.this, my_change_photo.class);
                    intent2.putExtra("butnum",true);
                    startActivity(intent2);
                }
                break;
            case R.id.textView150:
                break;
            case R.id.imageView111:
                break;
        }
    }
}
