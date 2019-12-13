package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.example.app.Model.ChatModel;
import com.qmuiteam.qmui.layout.QMUIPriorityLinearLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class chat extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView45)
    ImageView imageView45;
    @BindView(R.id.priority)
    QMUIPriorityLinearLayout priority;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.scrollmess)
    NestedScrollView scrollmess;
    @BindView(R.id.bears)
    RelativeLayout bears;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);

        ChatModel.set(scrollmess,editText,inflater,layout,but,imageView45);

        ChatModel.initData(layout);
    }




    @OnClick({R.id.imageView45, R.id.but, R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView45:
                showSimpleBottomSheetGrid();
                break;
            case R.id.but:
                layout.addView(ChatModel.add(editText.getText().toString(), "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
                editText.setText("");
                break;
            case R.id.fold:
                this.finish();
                break;
            case R.id.title:
                title.setText("Emg00");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }

    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        new QMUIBottomSheet.BottomGridSheetBuilder(this)
                .addItem(R.drawable.addpic, "相机", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.addpic, "相册", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .setOnSheetItemClickListener((dialog, itemView) -> {
                    dialog.dismiss();
                    int tag = (int) itemView.getTag();
                    switch (tag) {
                        case TAG_SHARE_WECHAT_FRIEND:
                            Toast.makeText(this, "打开相机", Toast.LENGTH_SHORT).show();
                            break;
                        case TAG_SHARE_WECHAT_MOMENT:
                            Toast.makeText(this, "打开相册", Toast.LENGTH_SHORT).show();
                            break;
                    }
                })
                .build().show();
    }
}