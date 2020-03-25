package com.example.hz52.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class forget_password extends AppCompatActivity {


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
    @BindView(R.id.textView150)
    TextView textView150;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView22)
    QMUIRadiusImageView imageView22;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView116)
    ImageView imageView116;
    @BindView(R.id.imageView117)
    ImageView imageView117;
    @BindView(R.id.imageView137)
    ImageView imageView137;
    @BindView(R.id.imageView118)
    ImageView imageView118;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        title.setText("忘记密码");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.textView150, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.textView150:
                break;
            case R.id.but:
                break;
        }
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/page/getHome")
                .params("count", 2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            Toast.makeText(forget_password.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(forget_password.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
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
