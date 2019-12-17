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

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Sqlentity.User;
import com.example.app.dao.mUserDao;
import com.example.app.gen.DaoMaster;
import com.example.app.gen.DaoSession;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private static DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fold, R.id.textView150, R.id.imageView111, R.id.but, R.id.textView159})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.textView150:
                break;
            case R.id.imageView111:
                break;
            case R.id.but:
                Intent intent2 = new Intent(registered.this, information.class);
                startActivity(intent2);
                break;
            case R.id.textView159:
                Intent intent4 = new Intent(registered.this, agreement.class);
                startActivity(intent4);
                break;
        }
    }

}
