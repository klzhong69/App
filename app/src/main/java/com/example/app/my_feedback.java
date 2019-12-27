package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_feedback extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView152)
    TextView textView152;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView153)
    TextView textView153;
    @BindView(R.id.imageView20)
    QMUIRadiusImageView imageView20;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.textView25)
    TextView textView25;
    @BindView(R.id.imageView113)
    ImageView imageView113;
    @BindView(R.id.but)
    QMUIRoundButton but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);
        ButterKnife.bind(this);
        title.setText("反馈意见");
        subtitle.setText("");
        editText.setText("");
        editText3.setText("");
    }

    @OnClick({R.id.fold, R.id.imageView113, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.imageView113:
                break;
            case R.id.but:
                if(editText.getText().equals("")){
                    if(editText3.getText().equals("")){
                        Toast.makeText(my_feedback.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(my_feedback.this, "请输入意见", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(my_feedback.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
