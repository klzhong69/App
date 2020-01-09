package com.example.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.app.MQ.MqttMessageService;
import com.example.app.Model.ChatModel;
import com.example.app.Model.ListModel;
import com.example.app.Model.MessModel;
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.Conver;
import com.example.app.cofig.DateUtil;
import com.example.app.cofig.Initialization;
import com.example.app.cofig.KeyboardStateObserver;
import com.example.app.cofig.Mess;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mConverDao;
import com.example.app.gen.DaoSession;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LayoutInflater inflater;
    private static DaoSession daoSession;
    private String conver;
    public static Long sendid;
    private String sendname;
    public static Observer<Integer> observerchat;
    private int a =0;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        Initialization.setupDatabaseChat(this);
        Initialization.setupDatabaseConver(this);

        context = this;

        Intent intent = getIntent();
        conver = intent.getStringExtra("conver");
        sendid = intent.getLongExtra("sendid", 0L);
        sendname = intent.getStringExtra("sendname");
        title.setText(sendname);
        subtitle.setText("  ");

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
        if(list.size()>0){
            if(list.get(0).getSum() !=0){
                list.get(0).setSum(0);
                mConverDao.update(list.get(0));
            }

        }

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




    }

    /**
     * 判断某个界面是否在前台
     *
     *
     * @return 是否在前台显示
     */
    public static boolean isForeground() {
        return isForeground(context, context.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName()))
                return true;
        }
        return false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        //聊天
        observerchat = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {


            }

            @Override
            public void onNext(Integer integer) {

                ChatModel.recly(recycler, 0);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    protected void onDestroy(){
        super.onDestroy();
    }

    @OnClick({R.id.but, R.id.fold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but:
                if (!editText.getText().toString().equals("")) {

                    SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                    String userid = sp.getString("userid", "");
                    String avatarUrl = sp.getString("avatarUrl", "");
                    String nickname = sp.getString("nickname", "");

                    String time = DateUtil.getCurrentTimeYMDHMS();
                    String txt = editText.getText().toString();
                    Chats i1 = new Chats(avatarUrl, txt, time, 2);
                    ChatModel.Add(recycler, i1);
                    editText.setText("");
                    send(userid,avatarUrl,nickname,sendid,txt,conver);

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

    public static void send(String userid,String avatarUrl,String nickname,Long sendid,String txt,String conver){

        Long data = System.currentTimeMillis()/1000;
        Gson gson = new Gson();


        Chat chat = new Chat();
        chat.setConversation(conver);
        chat.setData(data);
        chat.setSendId(Long.valueOf(userid));
        chat.setSendsrc(avatarUrl);
        chat.setTxt(txt);
        chat.setState(1);
        mChatDao.insert(chat);

        List<Conver> list = mConverDao.query(sendid);
        if(list.size()>0){
            Conver convers = list.get(0);
            convers.setData(data);
            convers.setTxt(txt);
            mConverDao.update(convers);
        }


        Map<String,String> map = new HashMap<String,String>();
        map.put("sendId", userid);
        map.put("senderName",nickname);
        map.put("senderAvatarUrl",avatarUrl);
        map.put("content",txt);

        String xini = gson.toJson(map);
        JsonObject returnData = new JsonParser().parse(xini).getAsJsonObject();

        Mess mess = new Mess();
        mess.setType(3);
        mess.setSendTime(data);
        mess.setData(returnData);

        String meg = gson.toJson(mess);
        MqttMessageService.publishMessage("user/"+sendid, meg);
    }

   /* private void showSimpleBottomSheetGrid() {
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
    }*/

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}