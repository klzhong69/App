package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class chat extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView45)
    ImageView imageView45;
    @BindView(R.id.bears)
    RelativeLayout bears;
    @BindView(R.id.scrollmess)
    NestedScrollView scrollview;
    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.imageView41)
    ImageView imageView41;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.bear)
    RelativeLayout bear;
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
            convertView = inflater.inflate(R.layout.chattxt_left, null);
            viewHolder.txt = (TextView) convertView.findViewById(R.id.text);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            viewHolder.txt.setText(txt);
            Glide.with(convertView).load(head).into(viewHolder.head);
        } else if (state == 0 && type == 1) {
            convertView = inflater.inflate(R.layout.chattxt_right, null);
            viewHolder.txt = (TextView) convertView.findViewById(R.id.text);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            viewHolder.txt.setText(txt);
            Glide.with(convertView).load(head).into(viewHolder.head);
        } else if (state == 1 && type == 0) {
            convertView = inflater.inflate(R.layout.chatima_left, null);
            viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imageViews2);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            Glide.with(convertView).load(head).into(viewHolder.head);
            Glide.with(convertView).load(src).into(viewHolder.imagesrc);
        } else if (state == 1 && type == 1) {
            convertView = inflater.inflate(R.layout.chatima_right, null);
            viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imageViews2);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            Glide.with(convertView).load(head).into(viewHolder.head);
            Glide.with(convertView).load(src).into(viewHolder.imagesrc);

        }
        return convertView;
    }

    @OnClick({R.id.editText, R.id.imageView40, R.id.imageView41})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editText:
                break;
            case R.id.imageView40:
                this.fileList();
                break;
            case R.id.imageView41:
                break;
        }
    }

    static class ViewHolder {
        private TextView txt;
        private ImageView imagesrc;
        private ImageView head;
    }


    @OnClick(R.id.imageView45)
    public void onViewClicked() {
        showSimpleBottomSheetGrid();
    }

    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        new QMUIBottomSheet.BottomGridSheetBuilder(this)
                .addItem(R.mipmap.x3, "相机", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.x1, "相册", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .setOnSheetItemClickListener((dialog, itemView) -> {
                    dialog.dismiss();
                    int tag = (int) itemView.getTag();
                    switch (tag) {
                        case TAG_SHARE_WECHAT_FRIEND:
                            Toast.makeText(this, "分享到微信", Toast.LENGTH_SHORT).show();
                            break;
                        case TAG_SHARE_WECHAT_MOMENT:
                            Toast.makeText(this, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                            break;
                    }
                })
                .build().show();
    }
}
