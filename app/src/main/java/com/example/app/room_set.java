package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class room_set extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView133)
    TextView textView133;
    @BindView(R.id.textView134)
    TextView textView134;
    @BindView(R.id.textView135)
    TextView textView135;
    @BindView(R.id.textView136)
    TextView textView136;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.but2)
    QMUIRoundButton but2;
    @BindView(R.id.but3)
    QMUIRoundButton but3;
    @BindView(R.id.but4)
    QMUIRoundButton but4;
    @BindView(R.id.imageView119)
    ImageView imageView119;
    @BindView(R.id.textView137)
    TextView textView137;
    @BindView(R.id.imageView120)
    ImageView imageView120;
    @BindView(R.id.imageView121)
    ImageView imageView121;
    @BindView(R.id.textView138)
    TextView textView138;
    @BindView(R.id.imageView122)
    ImageView imageView122;
    @BindView(R.id.textView139)
    TextView textView139;
    @BindView(R.id.imageView123)
    ImageView imageView123;
    @BindView(R.id.textView140)
    TextView textView140;
    @BindView(R.id.imageView124)
    ImageView imageView124;
    @BindView(R.id.imageView125)
    ImageView imageView125;
    @BindView(R.id.imageView126)
    ImageView imageView126;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.title:
                title.setText("房间设置");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }
}
