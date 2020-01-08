package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Entity.MyApp;
import com.example.app.cofig.Preview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
        editText4.setText("");
        editText5.setText("");
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
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.but:
                if(editText4.getText().equals("")){
                    if(editText5.getText().equals("")){
                        if(butnum){
                            okgo();
                        }else{
                            this.finish();
                            Intent intent2 = new Intent(my_change_photo.this, my_change_photo.class);
                            intent2.putExtra("butnum",true);
                            startActivity(intent2);
                        }
                    }else{
                        Toast.makeText(my_change_photo.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(my_change_photo.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.textView150:
                textView150.setVisibility(View.GONE);
                imageView111.setVisibility(View.VISIBLE);
                break;
            case R.id.imageView111:
                break;
        }
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        String token = sp.getString("token","");
        OkGo.<String>post(application.getUrl()+"/app/user/changePhone?token="+token)
                .params("userId",userid)
                .params("phone",editText4.getText().toString())
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if(prexiew.getCode()==0){

                            Toast.makeText(my_change_photo.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                            my_change_photo.this.finish();

                        }else if(prexiew.getCode()==40000){
                            Toast.makeText(my_change_photo.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
