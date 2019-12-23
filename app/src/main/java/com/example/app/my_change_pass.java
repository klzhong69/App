package com.example.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class my_change_pass extends AppCompatActivity {

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
    @BindView(R.id.imageView25)
    ImageView imageView25;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.imageView20)
    QMUIRadiusImageView imageView20;
    @BindView(R.id.imageView26)
    ImageView imageView26;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.textView149)
    TextView textView149;

    private Boolean extnumber = false;
    private Boolean extcapital = false;
    private Boolean extlower = false;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_change_pass);
        ButterKnife.bind(this);
        title.setText("修改密码");
        subtitle.setText("");

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                     temp = s.toString();
                    System.out.println(temp);
                    String tem = temp.substring(temp.length()-1, temp.length());
                    char[] temc = tem.toCharArray();
                    for(int i=0;i<temc.length;i++){
                        int mid = temc[i];
                        if(mid>=48&&mid<=57){//数字
                            extnumber=true;
                        }
                        if(mid>=65&&mid<=90){//大写字母
                            extcapital=true;
                        }
                        if(mid>97&&mid<=122){//小写字母
                            extlower=true;
                        }
                    }


                } catch (Exception e) {
                    // todo: handle exception
                }

            }
        };
        editText4.addTextChangedListener(watcher);

    }

    @OnClick({R.id.fold, R.id.imageView25, R.id.imageView26, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.imageView25:
                break;
            case R.id.imageView26:
                break;
            case R.id.but:
                if(extnumber && extcapital && extlower){
                    if(editText5.getText().toString().equals(editText4.getText().toString())){
                        Toast.makeText(my_change_pass.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(my_change_pass.this, "两次密码不同，请重新输入", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(my_change_pass.this, "密码必须包含大小写字母、数字", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
