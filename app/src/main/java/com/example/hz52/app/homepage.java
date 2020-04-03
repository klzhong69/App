package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Model.HomePageModel;
import com.example.hz52.app.Sqlentity.Conver;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mConverDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.ywl5320.libenum.MuteEnum;
import com.ywl5320.libmusic.WlMusic;
import com.ywl5320.listener.OnCompleteListener;
import com.ywl5320.listener.OnPreparedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class homepage extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.imageView35)
    QMUIRadiusImageView imageView35;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.relativeLayou)
    RelativeLayout relativeLayou;
    @BindView(R.id.imageView11)
    ImageView imageView11;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.recycler3)
    RecyclerView recycler3;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.imageView20)
    ImageView imageView20;
    @BindView(R.id.imageView22)
    QMUIRadiusImageView imageView22;
    @BindView(R.id.imageView26)
    QMUIRadiusImageView imageView26;
    @BindView(R.id.imageView27)
    QMUIRadiusImageView imageView27;
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.textView13)
    TextView textView13;
    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.imageView28)
    QMUIRadiusImageView imageView28;
    @BindView(R.id.imageView24)
    QMUIRadiusImageView imageView24;
    @BindView(R.id.imageView29)
    QMUIRadiusImageView imageView29;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.textView28)
    TextView textView28;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.textView30)
    TextView textView30;
    @BindView(R.id.relativeLayout3)
    RelativeLayout relativeLayout3;
    @BindView(R.id.imageView30)
    ImageView imageView30;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.relativeLayout4)
    RelativeLayout relativeLayout4;
    @BindView(R.id.imageView34)
    ImageView imageView34;
    @BindView(R.id.textView31)
    TextView textView31;
    @BindView(R.id.imageView36)
    ImageView imageView36;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.relativeLayout5)
    RelativeLayout relativeLayout5;
    @BindView(R.id.largeLabel)
    RelativeLayout largeLabel;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    private homepage context;
    private Long followId;
    public String userid;
    private String nickname;
    private String avatarUrl;
    private QMUITipDialog tipDialog;
    private Boolean bool = false;
    private WlMusic wlMusic;
    private String signtureVoiceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
        title.setText("个人主页");
        subtitle.setText("");

        Intent intent = getIntent();
        followId = intent.getLongExtra("id", 0L);
        System.out.println(followId);
        context = this;
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        initData();
        Initialization.setupDatabaseChat(this);
        Initialization.setupDatabaseConver(this);

        if (userid.equals(String.valueOf(followId))) {
            relativeLayout5.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.fold, R.id.imageView34, R.id.textView31, R.id.imageView36, R.id.textView32, R.id.imageView6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.imageView34:
            case R.id.textView31:
                if (bool) {
                    okgos();
                }else{
                    okgo();
                }
                break;
            case R.id.imageView36:
            case R.id.textView32:
                SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                String userid = sp.getString("userid", "");
                String usersrc = sp.getString("avatarUrl", "");
                String username = sp.getString("nickname", "");
                if (mConverDao.query(followId, Long.valueOf(userid)).size() == 0) {

                    long data = System.currentTimeMillis() / 1000;
                    Conver convers = new Conver();
                    convers.setSendsrc(avatarUrl);
                    convers.setSendname(nickname);
                    convers.setSendId(Long.valueOf(followId));
                    convers.setUserId(Long.valueOf(userid));
                    convers.setData(data);
                    convers.setSum(0);
                    convers.setTxt("我是" + username);
                    convers.setType(1);
                    mConverDao.insert(convers);
                    chat.send(userid, usersrc, username, followId, "我是" + username, "user/" + followId);
                }

                Intent intent2 = new Intent(context, chat.class);
                intent2.putExtra("conver", "user/" + followId);
                intent2.putExtra("sendid", followId);
                intent2.putExtra("sendname", nickname);
                context.startActivity(intent2);
                
                break;
            case R.id.imageView6:
                if (!signtureVoiceUrl.equals("")) {
                    music(signtureVoiceUrl);
                }else{
                    Toast.makeText(homepage.this,  "音频为空，请进行录制", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private void initData() {

        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        System.out.println(token);
        OkGo.<String>post(application.getUrl() + "/app/user/getInfo?token=" + token)
                .params("userId", userid)
                .params("followId", followId)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            try {
                                JsonArray friend = prexiew.getData().getAsJsonArray("friends");
                                JsonArray photo = prexiew.getData().getAsJsonArray("photos");
                                JsonArray giftWall = prexiew.getData().getAsJsonArray("giftWall");

                                if (prexiew.getData().get("signtureText").getAsString().equals("")) {
                                    textView11.setText("签名");
                                } else {
                                    textView11.setText(prexiew.getData().get("signtureText").getAsString());
                                }

                                avatarUrl = prexiew.getData().get("avatarUrl").getAsString();
                                nickname = prexiew.getData().get("nickname").getAsString();

                                String gold = prexiew.getData().get("gold").getAsString();
                                String diamond = prexiew.getData().get("diamond").getAsString();

                                signtureVoiceUrl = prexiew.getData().get("signtureVoiceUrl").getAsString();

                                String isFollow = prexiew.getData().get("isFollow").getAsString();
                                if (isFollow.equals("1")) {
                                    Glide.with(homepage.this).load(R.drawable.s_guanzhu).into(imageView34);
                                    textView31.setText("取消关注");
                                    bool = true;
                                }else{
                                    bool = false;
                                }

                                textView2.setText(prexiew.getData().get("nickname").getAsString());
                                textView3.setText("ID " + prexiew.getData().get("userId").getAsString());
                                Glide.with(homepage.this).load(prexiew.getData().get("avatarUrl").getAsString()).into(imageView2);
                                textView5.setText(prexiew.getData().get("followCount").getAsString());
                                textView9.setText(prexiew.getData().get("fansCount").getAsString());

                                if (photo != null || giftWall != null) {
                                    HomePageModel.initData(photo, giftWall);
                                    HomePageModel.initrecycler(context, recycler);
                                    HomePageModel.initrecyclert(context, recycler3);
                                }


                            }catch (Exception ignored){}
                            tipDialog.dismiss();
                        } else {
                            tipDialog.dismiss();
                            Toast.makeText(homepage.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    private void music(String url) {
        wlMusic = WlMusic.getInstance();
        wlMusic.setSource(url); //设置音频源
        wlMusic.setCallBackPcmData(false);//是否返回音频PCM数据
        wlMusic.setShowPCMDB(false);//是否返回音频分贝大小
        wlMusic.setPlayCircle(false); //设置不间断循环播放音频
        wlMusic.setVolume(65); //设置音量 65%
        wlMusic.setPlaySpeed(1.0f); //设置播放速度 (1.0正常) 范围：0.25---4.0f
        wlMusic.setPlayPitch(1.0f); //设置播放速度 (1.0正常) 范围：0.25---4.0f
        wlMusic.setMute(MuteEnum.MUTE_CENTER); //设置立体声（左声道、右声道和立体声）
        wlMusic.setConvertSampleRate(null);//设定恒定采样率（null为取消）
        wlMusic.prePared();

        wlMusic.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {
                wlMusic.start();
            }
        });

        wlMusic.setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });

    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/user/follow?token=" + token)
                .params("userId", userid)
                .params("followId", followId)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(homepage.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            if (prexiew.getMsg().equals("关注成功")) {
                                Glide.with(homepage.this).load(R.drawable.s_guanzhu).into(imageView34);
                                textView31.setText("取消关注");
                                bool = true;
                            }

                        } else {
                            Toast.makeText(homepage.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private void okgos() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/user/unFollow?token=" + token)
                .params("userId", userid)
                .params("followId", followId)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(homepage.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            if (prexiew.getMsg().equals("取关成功")) {
                                Glide.with(homepage.this).load(R.drawable.s_guanzhus).into(imageView34);
                                textView31.setText("关注");
                                bool = false;
                            }

                        } else {
                            Toast.makeText(homepage.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onBackPressed() {
        this.finish();
    }

}
