package com.example.hz52.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class add_account extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView41)
    QMUIRadiusImageView imageView41;
    @BindView(R.id.imageView76)
    ImageView imageView76;
    @BindView(R.id.textView87)
    TextView textView87;
    @BindView(R.id.textView172)
    TextView textView172;
    @BindView(R.id.imageView144)
    ImageView imageView144;
    @BindView(R.id.textView174)
    TextView textView174;
    @BindView(R.id.imageView42)
    QMUIRadiusImageView imageView42;
    @BindView(R.id.imageView77)
    ImageView imageView77;
    @BindView(R.id.textView88)
    TextView textView88;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView43)
    QMUIRadiusImageView imageView43;
    @BindView(R.id.imageView78)
    ImageView imageView78;
    @BindView(R.id.textView89)
    TextView textView89;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.but)
    QMUIRoundButton but;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        ButterKnife.bind(this);
        title.setText("添加账号");
        subtitle.setText("");
        textView174.setVisibility(View.VISIBLE);
        imageView42.setVisibility(View.GONE);
        imageView77.setVisibility(View.GONE);
        textView88.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);
        imageView43.setVisibility(View.GONE);
        imageView78.setVisibility(View.GONE);
        textView89.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
    }

    @OnClick({R.id.fold, R.id.imageView41, R.id.imageView76, R.id.textView87, R.id.textView172, R.id.imageView144, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView41:
            case R.id.imageView76:
            case R.id.textView87:
            case R.id.textView172:
            case R.id.imageView144:
                showSimpleBottomSheetList(
                        true, false, false, null,
                        3, false, false);
                break;
            case R.id.but:
                break;
        }
    }

    private void showSimpleBottomSheetList(boolean gravityCenter,
                                           boolean addCancelBtn,
                                           boolean withIcon,
                                           CharSequence title,
                                           int itemCount,
                                           boolean allowDragDismiss,
                                           boolean withMark) {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        builder.setGravityCenter(gravityCenter)
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        type = position;
                        textView172.setText(tag);
                        if (position == 2) {
                            textView174.setVisibility(View.GONE);
                            imageView42.setVisibility(View.VISIBLE);
                            imageView77.setVisibility(View.VISIBLE);
                            textView88.setVisibility(View.VISIBLE);
                            editText.setVisibility(View.VISIBLE);
                            imageView43.setVisibility(View.VISIBLE);
                            imageView78.setVisibility(View.VISIBLE);
                            textView89.setVisibility(View.VISIBLE);
                            editText2.setVisibility(View.VISIBLE);

                        } else {
                            textView174.setVisibility(View.VISIBLE);
                            imageView42.setVisibility(View.GONE);
                            imageView77.setVisibility(View.GONE);
                            textView88.setVisibility(View.GONE);
                            editText.setVisibility(View.GONE);
                            imageView43.setVisibility(View.GONE);
                            imageView78.setVisibility(View.GONE);
                            textView89.setVisibility(View.GONE);
                            editText2.setVisibility(View.GONE);
                        }
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }

        builder.addItem("微信");
        builder.addItem("支付宝");
        builder.addItem("银行卡");

        builder.build().show();
    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
