package com.example.app.Model;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.example.app.R;
import com.example.app.chat;

public class ChatModel {

    private static View convertView;
    private static ViewHolder viewHolder;
    private static LayoutInflater inflater;

    public static class ViewHolder {
        private TextView txt;
        private ImageView imagesrc;
        private ImageView head;
    }



    public static void set(NestedScrollView scrollmess, EditText editText, LayoutInflater inflaters, LinearLayout layout, Button but, ImageView imageView45){
        inflater = inflaters;
        viewHolder = new ViewHolder();
        scrollmess.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollmess.post(new Runnable() {
                    public void run() {
                        scrollmess.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    layout.addView(ChatModel.add(editText.getText().toString(), "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
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

    public static View add(String txt, String head, String src, int state, int type) {
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

    public static void initData(LinearLayout layout) {
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
}
