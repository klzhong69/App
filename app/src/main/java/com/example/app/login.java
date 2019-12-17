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
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class login extends AppCompatActivity {

    @BindView(R.id.imageView115)
    ImageView imageView115;
    @BindView(R.id.textView155)
    TextView textView155;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView116)
    ImageView imageView116;
    @BindView(R.id.imageView117)
    ImageView imageView117;
    @BindView(R.id.textView156)
    TextView textView156;
    @BindView(R.id.textView157)
    TextView textView157;
    @BindView(R.id.textView158)
    TextView textView158;
    @BindView(R.id.textView159)
    TextView textView159;
    @BindView(R.id.imageView132)
    ImageView imageView132;
    @BindView(R.id.imageView133)
    ImageView imageView133;
    @BindView(R.id.imageView134)
    ImageView imageView134;
    @BindView(R.id.textView160)
    TextView textView160;
    @BindView(R.id.imageView135)
    ImageView imageView135;
    @BindView(R.id.imageView136)
    ImageView imageView136;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.but, R.id.textView156, R.id.textView157, R.id.textView159, R.id.imageView132, R.id.imageView133, R.id.imageView134})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but:
                SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                sp.edit().putLong("userid", 1L).apply();
                Intent intent1 = new Intent(login.this, MainActivity.class);
                intent1.putExtra("id",4);
                startActivity(intent1);
                break;
            case R.id.textView156:
                Intent intent2 = new Intent(login.this, forget_password.class);
                startActivity(intent2);
                break;
            case R.id.textView157:
                Intent intent3 = new Intent(login.this, registered.class);
                startActivity(intent3);
                break;
            case R.id.textView159:
                Intent intent4 = new Intent(login.this, agreement.class);
                intent4.putExtra("about",0);
                startActivity(intent4);
                break;
            case R.id.imageView132:
                break;
            case R.id.imageView133:
                break;
            case R.id.imageView134:
                break;
        }
    }

}
