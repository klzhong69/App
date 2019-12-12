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
    NestedScrollView scrollview;
    @BindView(R.id.bears)
    RelativeLayout bears;
    private View convertView;
    private ViewHolder viewHolder;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        this.inflater = LayoutInflater.from(this);
        viewHolder = new ViewHolder();
        scrollview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollview.post(new Runnable() {
                    public void run() {
                        scrollview.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
        initData();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    layout.addView(add(editText.getText().toString(), "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
                    editText.setText("");
                    but.setVisibility(View.GONE);
                    imageView45.setVisibility(View.VISIBLE);
                    return true;   //返回true，保留软键盘。false，隐藏软键盘
                }
                return false;
            }
        });
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    but.setVisibility(View.GONE);
                    imageView45.setVisibility(View.VISIBLE);
                } else {
                    but.setVisibility(View.VISIBLE);
                    imageView45.setVisibility(View.GONE);
                }
            }
        };
        editText.addTextChangedListener(watcher);

    }

    private void initData() {
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
        layout.addView(add("", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", 1, 0));
        layout.addView(add("", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", 1, 1));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 0));
        layout.addView(add("你好", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
    }

    private View add(String txt, String head, String src, int state, int type) {
        if (state == 0 && type == 0) {
            convertView = inflater.inflate(R.layout.chat_txt_left, null);
            viewHolder.txt = (TextView) convertView.findViewById(R.id.text);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            viewHolder.txt.setText(txt);
            Glide.with(convertView).load(head).into(viewHolder.head);
        } else if (state == 0 && type == 1) {
            convertView = inflater.inflate(R.layout.chat_txt_right, null);
            viewHolder.txt = (TextView) convertView.findViewById(R.id.text);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            viewHolder.txt.setText(txt);
            Glide.with(convertView).load(head).into(viewHolder.head);
        } else if (state == 1 && type == 0) {
            convertView = inflater.inflate(R.layout.chat_ima_left, null);
            viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imageViews2);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            Glide.with(convertView).load(head).into(viewHolder.head);
            Glide.with(convertView).load(src).into(viewHolder.imagesrc);
        } else if (state == 1 && type == 1) {
            convertView = inflater.inflate(R.layout.chat_ima_right, null);
            viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imageViews2);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            Glide.with(convertView).load(head).into(viewHolder.head);
            Glide.with(convertView).load(src).into(viewHolder.imagesrc);

        }
        return convertView;
    }


    @OnClick({R.id.imageView40, R.id.imageView45, R.id.but,R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView40:
                Intent intent = new Intent(chat.this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                break;
            case R.id.imageView45:
                showSimpleBottomSheetGrid();
                break;
            case R.id.but:
                layout.addView(add(editText.getText().toString(), "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
                editText.setText("");
                but.setVisibility(View.GONE);
                imageView45.setVisibility(View.VISIBLE);
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


    static class ViewHolder {
        private TextView txt;
        private ImageView imagesrc;
        private ImageView head;
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