package com.example.app.Model;

import android.content.Context;
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
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.User;
import com.example.app.chat;
import com.example.app.chatroom;
import com.example.app.cofig.DateUtil;
import com.example.app.cofig.Initialization;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mUserDao;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import static com.example.app.cofig.DateUtil.dateToString;

public class ChatModel {

    private static View convertView;
    private static View convertTime;
    private static ViewHolder viewHolder;
    private static LayoutInflater inflater;

    public static class ViewHolder {
        private TextView txt;
        private ImageView imagesrc;
        private ImageView head;
    }



    public static void set(NestedScrollView scrollmess, EditText editText, LayoutInflater inflaters,int a){
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
                    if(a==0){
                        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                            @Override
                            public ObservableSource<? extends Integer> call() throws Exception {
                                return Observable.just(0);
                            }
                        });
                        observable.subscribe(chat.observer);
                    }else{
                        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                            @Override
                            public ObservableSource<? extends Integer> call() throws Exception {
                                return Observable.just(0);
                            }
                        });
                        observable.subscribe(chatroom.observerchat);
                    }

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
        } else if (state == 1 && type == 0) {
            convertView = inflater.inflate(R.layout.chat_txt_right, null);
            viewHolder.txt = (TextView) convertView.findViewById(R.id.text);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);
            viewHolder.txt.setText(txt);
            Glide.with(convertView).load(head).into(viewHolder.head);
        } else if (state == 0 && type == 1) {
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


    public static View addtime(String txt){

        convertTime = inflater.inflate(R.layout.chat_time, null);
        viewHolder.txt = (TextView) convertTime.findViewById(R.id.textView183);
        convertTime.setTag(viewHolder);
        viewHolder.txt.setText(txt);

        return convertTime;
    }


    public static void initData(LinearLayout layout,Long conver) {

        try{
            List<Chat> list = mChatDao.queryBuilder(conver,0,10);
            for(int i=0;i<list.size();i++){
                System.out.println("内容："+list.get(i).getConversation());
                if(i==0){
                    layout.addView(addtime(DateUtil.stampToDate(list.get(i).getData().toString())));
                }else{
                    Long longs = (list.get(i).getData())-(list.get(i-1).getData());
                    if(longs>1800000L){
                        layout.addView(addtime(DateUtil.stampToDate(list.get(i).getData().toString())));
                    }
                }
                layout.addView(add(list.get(i).getTxt(), list.get(i).getSendsrc(), "",  list.get(i).getState(),0));
            }
        }catch (Exception ignored){
        }


    }
}
