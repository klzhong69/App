package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.Chats;
import com.example.app.Entity.MyApp;
import com.example.app.Model.ChatModel;
import com.example.app.Model.ListModel;
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.Conver;
import com.example.app.cofig.DateUtil;
import com.example.app.cofig.Initialization;
import com.example.app.cofig.KeyboardStateObserver;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mConverDao;
import com.example.app.gen.DaoSession;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.classics)
    ClassicsHeader classics;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LayoutInflater inflater;
    private static DaoSession daoSession;
    private String conver;
    private Long sendid;
    private String sendname;
    private String sendsrc;
    public static Observer<Integer> observerchat;
    private int a =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        Initialization.setupDatabaseChat(this);

        Intent intent = getIntent();
        conver = intent.getStringExtra("conver");
        sendid = intent.getLongExtra("sendid", 0L);
        sendname = intent.getStringExtra("sendname");
        sendsrc = intent.getStringExtra("sendsrc");
        title.setText(sendname);
        subtitle.setText("");

        refreshLayout.setRefreshHeader(new MaterialHeader(this).setScrollableWhenRefreshing(true));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                a++;
                refreshlayout.autoRefresh();
                ChatModel.datas(conver,a,10);
                ChatModel.initrecycler(chat.this, recycler);
                ChatModel.recly(recycler,a*10);
                refreshlayout.finishRefresh();

            }
        });

        ChatModel.initData(conver,0,10);
        ChatModel.initrecycler(this, recycler);

        MyApp application = ((MyApp) this.getApplicationContext());
        for(int i=0;i<application.getUsermess().size();i++){
            if(application.getUsermess().get(i).getSendId().equals(sendid)){
                application.getUsermess().remove(i);
            }

        }

        List<Conver> list =  mConverDao.query(sendid);
        list.get(0).setSum(0);
        mConverDao.update(list.get(0));


        KeyboardStateObserver.getKeyboardStateObserver(this).
                setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
                    @Override
                    public void onKeyboardShow() {
                        ChatModel.recly(recycler,0);
                    }

                    @Override
                    public void onKeyboardHide() {
                        ChatModel.recly(recycler,0);
                    }
                });


        //聊天
        observerchat = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

                List<Conver> list = mConverDao.query(sendid);
                Chats i1 = new Chats(list.get(0).getSendsrc(), list.get(0).getTxt(), list.get(0).getData().toString(), 2);
                ChatModel.Add(recycler, i1);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

    }

    @OnClick({R.id.but, R.id.fold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but:
                if (!editText.getText().toString().equals("")) {

                    SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                    String userid = sp.getString("userid", "");
                    String avatarUrl = sp.getString("avatarUrl", "");

                    String time = DateUtil.getCurrentTimeYMDHMS();
                    String txt = editText.getText().toString();
                    Chats i1 = new Chats(avatarUrl, txt, time, 2);
                    ChatModel.Add(recycler, i1);
                    editText.setText("");
                    Chat chat = new Chat();
                    chat.setConversation(conver);
                    chat.setData(System.currentTimeMillis());
                    chat.setSendId(Long.valueOf(userid));
                    chat.setSendsrc(avatarUrl);
                    chat.setTxt(txt);
                    chat.setState(1);
                    mChatDao.insert(chat);

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