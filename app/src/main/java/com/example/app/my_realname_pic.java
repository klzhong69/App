package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_realname_pic extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView131)
    TextView textView131;
    @BindView(R.id.textView132)
    TextView textView132;
    @BindView(R.id.textView141)
    TextView textView141;
    @BindView(R.id.imageView51)
    ImageView imageView51;
    @BindView(R.id.imageView109)
    ImageView imageView109;
    @BindView(R.id.imageView55)
    ImageView imageView55;
    @BindView(R.id.imageView110)
    ImageView imageView110;
    @BindView(R.id.textView142)
    TextView textView142;
    @BindView(R.id.textView143)
    TextView textView143;
    @BindView(R.id.but)
    QMUIRoundButton but;

    private Boolean a = false;
    private Boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_realname_pic);
        ButterKnife.bind(this);
        title.setText("实名认证");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.imageView55, R.id.imageView110, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.imageView55:
                a=true;
                break;
            case R.id.imageView110:
                b=true;
                break;
            case R.id.but:
                if(a && b){
                    Intent intent2 = new Intent(my_realname_pic.this, my_realnames.class);
                    startActivity(intent2);
                }else{
                    Toast.makeText(my_realname_pic.this, "请上传相应照片", Toast.LENGTH_SHORT).show();
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
