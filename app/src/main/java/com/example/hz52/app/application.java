package com.example.hz52.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
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

public class application extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.imageView20)
    QMUIRadiusImageView imageView20;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.textView25)
    TextView textView25;
    @BindView(R.id.but)
    QMUIRoundButton but;
    private KeyListener storedKeylistener;
    private CharSequence txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        ButterKnife.bind(this);
        title.setText("申请理由");
        subtitle.setText("");
        storedKeylistener = editText.getKeyListener();
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int sum = start + 1;
                if (sum <= 150) {
                    txt = s;
                    if (after == 1) {
                        textView24.setText(sum + "");
                    } else if (after == 0) {
                        textView24.setText(start + "");
                    }

                } else {
                    textView24.setText(150 + "");
                    editText.setText("");
                    editText.setText(txt);
                    editText.setSelection(editText.getText().length());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editText.addTextChangedListener(watcher);
    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.title:
                title.setText("申请理由");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
