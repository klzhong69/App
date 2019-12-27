package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.Chats;
import com.example.app.Model.ChatModel;
import com.example.app.cofig.DateUtil;
import com.example.app.cofig.Initialization;
import com.example.app.cofig.KeyboardStateObserver;
import com.example.app.gen.DaoSession;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class chat extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.relativeLayout11)
    RelativeLayout relativeLayout11;
    private LayoutInflater inflater;
    private static DaoSession daoSession;
    private Long conver;
    private Long sendid;
    private String sendname;
    private String sendsrc;
    public static Observer<Integer> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        Initialization.setupDatabaseChat(this);

        Intent intent = getIntent();
        conver = intent.getLongExtra("conver", 0L);
        sendid = intent.getLongExtra("sendid", 0L);
        sendname = intent.getStringExtra("sendname");
        sendsrc = intent.getStringExtra("sendsrc");
        title.setText(sendname);
        subtitle.setText("");

        ChatModel.initData();
        ChatModel.initrecycler(this, recycler);


        KeyboardStateObserver.getKeyboardStateObserver(this).
                setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
                    @Override
                    public void onKeyboardShow() {
                        ChatModel.recly(recycler);
                    }

                    @Override
                    public void onKeyboardHide() {
                        ChatModel.recly(recycler);
                    }
                });

    }

    @OnClick({R.id.but, R.id.fold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but:
                if (!editText.getText().toString().equals("")) {

                    String time = DateUtil.getCurrentTimeYMDHMS();
                    String txt = editText.getText().toString();
                    Chats i1 = new Chats("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", txt, time, 2);
                    ChatModel.Add(recycler, i1);
                    editText.setText("");
                /*Chat chat = new Chat();
                chat.setConversation(conver);
                chat.setData(System.currentTimeMillis());
                chat.setReceiveId(sendid);
                chat.setSendId(123456L);
                chat.setReceivesrc(sendsrc);
                chat.setSendsrc("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg");
                chat.setTxt(txt);
                chat.setState(1);
                mChatDao.insert(chat);*/


                } else {
                    Toast.makeText(chat.this, "请输入发送内容", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
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

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}