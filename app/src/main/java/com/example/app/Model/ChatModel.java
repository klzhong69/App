package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.ChatAdapter;
import com.example.app.Entity.Chats;

import java.util.ArrayList;
import java.util.List;

public class ChatModel {

    private static ChatAdapter mAdapter;
    private static List<Chats> mArrayList;

    public static void initData() {
        mArrayList = new ArrayList<Chats>();
        for (int i = 0; i < 20; i++) {
            int type=0;
            if(i<=5 && i>0){
                type=1;
            }else if(i>5 && i<=10){
                type=2;
            }else if(i>10){
                type=1;
            }
            Chats i1 = new Chats("https://momeak.oss-cn-shenzhen.aliyuncs.com/h3.jpg", "你好", "2019-12-27", type);
            mArrayList.add(i1);
        }

    }

    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
        mAdapter = new ChatAdapter(context, mArrayList);
        //设置适配器adapter
        recycler13.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(context);
        mLinearLayoutManager.setStackFromEnd(true);
        if (mAdapter.getItemCount() > 0) {
            mLinearLayoutManager.scrollToPositionWithOffset(mAdapter.getItemCount() - 1, Integer.MIN_VALUE);

        }
        recycler13.scrollToPosition(mAdapter.getItemCount()-1);
        recycler13.setLayoutManager(mLinearLayoutManager);

        recycler13.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler13.setItemAnimator(defaultItemAnimator);


    }



    public static void Add(RecyclerView mRecyclerView, Chats entity){
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

    public static void recly(RecyclerView mRecyclerView){
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

    /*private static View convertView;
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


    }*/
}
