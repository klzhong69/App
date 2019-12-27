package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Sqlentity.User;
import com.example.app.dao.mUserDao;
import com.example.app.gen.DaoMaster;
import com.example.app.gen.DaoSession;
import com.example.app.utils.Translation;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registered extends AppCompatActivity {

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
    @BindView(R.id.imageView111)
    ImageView imageView111;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView116)
    ImageView imageView116;
    @BindView(R.id.imageView117)
    ImageView imageView117;
    @BindView(R.id.imageView137)
    ImageView imageView137;
    @BindView(R.id.textView158)
    TextView textView158;
    @BindView(R.id.textView159)
    TextView textView159;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        title.setText("立即注册");
        subtitle.setText("");
        editText4.setText("");
        editText5.setText("");
        editText.setText("");
    }

    @OnClick({R.id.fold, R.id.textView150, R.id.imageView111, R.id.but, R.id.textView159})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.textView150:
                break;
            case R.id.imageView111:
                break;
            case R.id.but:
                if (!editText4.getText().toString().equals("")) {

                    if (!editText5.getText().toString().equals("")) {
                        if (!editText.getText().toString().equals("")  &&  editText.getText().length()>=8  &&  editText.getText().length()<16) {
                            Intent intent2 = new Intent(registered.this, information.class);
                            intent2.putExtra("phone", editText4.getText().toString());
                            intent2.putExtra("pass", editText.getText().toString());
                            startActivity(intent2);

                        } else {
                            Toast.makeText(registered.this, "请输入密码，密码长度大于8位，小于16位", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(registered.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(registered.this, "请输入手机号", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.textView159:
                Intent intent4 = new Intent(registered.this, agreement.class);
                intent4.putExtra("about", 0);
                startActivity(intent4);
                break;
        }
    }




    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }

}
