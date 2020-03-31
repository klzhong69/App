package com.example.hz52.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_realname_name extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.textView129)
    TextView textView129;
    @BindView(R.id.textView130)
    TextView textView130;
    @BindView(R.id.imageView23)
    ImageView imageView23;
    @BindView(R.id.imageView25)
    ImageView imageView25;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.but)
    QMUIRoundButton but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_realname_name);
        ButterKnife.bind(this);
        title.setText("实名认证");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.imageView23, R.id.imageView25, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView23:
                editText.setText("");
                break;
            case R.id.imageView25:
                editText4.setText("");
                break;
            case R.id.but:
                if(!editText.getText().toString().equals("") && !editText4.getText().toString().equals("")){
                    Intent intent2 = new Intent(my_realname_name.this, my_realname_pic.class);
                    startActivity(intent2);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }else{
                    Toast.makeText(my_realname_name.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
