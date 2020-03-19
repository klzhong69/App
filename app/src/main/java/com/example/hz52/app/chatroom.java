package com.example.hz52.app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Adapter.HoldpeopleAdapter;
import com.example.hz52.app.Entity.Chats;
import com.example.hz52.app.Entity.Holdpeople;
import com.example.hz52.app.Entity.Roomhead;
import com.example.hz52.app.Entity.Roomtxt;
import com.example.hz52.app.Model.ChatModel;
import com.example.hz52.app.Model.ChatRoomModel;
import com.example.hz52.app.Model.GiftModel;
import com.example.hz52.app.Model.GiftheadModel;
import com.example.hz52.app.Model.MessFriendsModel;
import com.example.hz52.app.Model.MessModel;
import com.example.hz52.app.Model.PaimaiModel;
import com.example.hz52.app.cofig.DateUtil;
import com.example.hz52.app.cofig.KeyboardStateObserver;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.qmuiteam.qmui.widget.popup.QMUIQuickAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.skyfishjy.library.RippleBackground;
import com.wildma.pictureselector.Constant;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class chatroom extends AppCompatActivity {

    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.textView121)
    TextView textView121;
    @BindView(R.id.textView122)
    TextView textView122;
    @BindView(R.id.textView123)
    TextView textView123;
    @BindView(R.id.imageView99)
    ImageView imageView99;
    @BindView(R.id.textView124)
    TextView textView124;
    @BindView(R.id.imageView100)
    ImageView imageView100;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.rippleback)
    RippleBackground rippleback;
    @BindView(R.id.textView39)
    TextView textView39;
    @BindView(R.id.gridview)
    RecyclerView gridview;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.relativeLayout)
    NestedScrollView relativeLayout;
    @BindView(R.id.textView19)
    TextView textView19;
    @BindView(R.id.imageView96)
    ImageView imageView96;
    @BindView(R.id.imageView97)
    ImageView imageView97;
    @BindView(R.id.textView120)
    TextView textView120;
    @BindView(R.id.imageView98)
    ImageView imageView98;
    @BindView(R.id.imageView106)
    ImageView imageView106;
    @BindView(R.id.imageView101)
    ImageView imageView101;
    @BindView(R.id.imageView102)
    ImageView imageView102;
    @BindView(R.id.imageView103)
    ImageView imageView103;
    @BindView(R.id.imageView104)
    ImageView imageView104;
    @BindView(R.id.imageView105)
    ImageView imageView105;
    @BindView(R.id.relativeLayout3)
    RelativeLayout relativeLayout3;
    @BindView(R.id.recyclerbutc11)
    RelativeLayout recyclerbutc11;
    @BindView(R.id.imageViewc11)
    QMUIRadiusImageView imageViewc11;
    @BindView(R.id.imageViewc11s)
    ImageView imageViewc11s;
    @BindView(R.id.textViewc11)
    TextView textViewc11;
    @BindView(R.id.butc11)
    QMUIRoundButton butc11;
    @BindView(R.id.textView148)
    TextView textView148;
    @BindView(R.id.recyclerc11)
    RelativeLayout recyclerc11;
    @BindView(R.id.recyclerbutc11s)
    RelativeLayout recyclerbutc11s;
    @BindView(R.id.recyclerbutc1)
    RelativeLayout recyclerbutc1;
    @BindView(R.id.imageViewc1)
    ImageView imageViewc1;
    @BindView(R.id.textViewc1)
    TextView textViewc1;
    @BindView(R.id.imageViewc1t)
    ImageView imageViewc1t;
    @BindView(R.id.recyclerc1)
    RecyclerView recyclerc1;
    @BindView(R.id.editTextc1)
    EditText editTextc1;
    @BindView(R.id.butc1)
    QMUIRoundButton butc1;
    @BindView(R.id.relativeLayout11)
    RelativeLayout relativeLayout11;
    @BindView(R.id.relativec1)
    RelativeLayout relativec1;
    @BindView(R.id.component1)
    RelativeLayout component1;
    @BindView(R.id.recyclerbutc2)
    RelativeLayout recyclerbutc2;
    @BindView(R.id.imageViewc2)
    ImageView imageViewc2;
    @BindView(R.id.textViewc2s)
    TextView textViewc2s;
    @BindView(R.id.textViewc2t)
    TextView textViewc2t;
    @BindView(R.id.textViewc2d)
    TextView textViewc2d;
    @BindView(R.id.textViewc2f)
    TextView textViewc2f;
    @BindView(R.id.recyclerc2)
    RecyclerView recyclerc2;
    @BindView(R.id.relativec2)
    RelativeLayout relativec2;
    @BindView(R.id.imageViewc2t)
    ImageView imageViewc2t;
    @BindView(R.id.component2)
    RelativeLayout component2;
    @BindView(R.id.recyclerbutc3)
    RelativeLayout recyclerbutc3;
    @BindView(R.id.imageViewc3)
    ImageView imageViewc3;
    @BindView(R.id.textViewc3)
    TextView textViewc3;
    @BindView(R.id.textViewc3s)
    TextView textViewc3s;
    @BindView(R.id.recyclerc3)
    RecyclerView recyclerc3;
    @BindView(R.id.relativec3)
    RelativeLayout relativec3;
    @BindView(R.id.component3)
    RelativeLayout component3;
    @BindView(R.id.recyclerbutc4)
    RelativeLayout recyclerbutc4;
    @BindView(R.id.imageViewc4)
    ImageView imageViewc4;
    @BindView(R.id.imageViewc4s)
    QMUIRadiusImageView imageViewc4s;
    @BindView(R.id.textViewc4d)
    TextView textViewc4d;
    @BindView(R.id.gridviewc4)
    RecyclerView gridviewc4;
    @BindView(R.id.textViewc4)
    TextView textViewc4;
    @BindView(R.id.textViewc4s)
    TextView textViewc4s;
    @BindView(R.id.textViewc4t)
    TextView textViewc4t;
    @BindView(R.id.recyclerc4)
    RecyclerView recyclerc4;
    @BindView(R.id.imageViewc4t)
    QMUIRadiusImageView imageViewc4t;
    @BindView(R.id.textViewc4a)
    TextView textViewc4a;
    @BindView(R.id.imageViewc4a)
    QMUIRadiusImageView imageViewc4a;
    @BindView(R.id.textViewc4b)
    TextView textViewc4b;
    @BindView(R.id.imageViewc4b)
    ImageView imageViewc4b;
    @BindView(R.id.imageViewc4c)
    QMUIRadiusImageView imageViewc4c;
    @BindView(R.id.imageViewc4d)
    ImageView imageViewc4d;
    @BindView(R.id.textViewc4c)
    TextView textViewc4c;
    @BindView(R.id.relativec4)
    RelativeLayout relativec4;
    @BindView(R.id.component4)
    RelativeLayout component4;
    @BindView(R.id.recyclerbutc5)
    RelativeLayout recyclerbutc5;
    @BindView(R.id.imageViewc5)
    ImageView imageViewc5;
    @BindView(R.id.textViewc5)
    TextView textViewc5;
    @BindView(R.id.imageViewc5s)
    ImageView imageViewc5s;
    @BindView(R.id.recyclerc5)
    RecyclerView recyclerc5;
    @BindView(R.id.relativec5)
    RelativeLayout relativec5;
    @BindView(R.id.component5)
    RelativeLayout component5;
    @BindView(R.id.recyclerbutc7)
    RelativeLayout recyclerbutc7;
    @BindView(R.id.imageViewc7)
    ImageView imageViewc7;
    @BindView(R.id.textViewc7)
    TextView textViewc7;
    @BindView(R.id.textViewc7s)
    TextView textViewc7s;
    @BindView(R.id.recyclerc7)
    RecyclerView recyclerc7;
    @BindView(R.id.relativec7)
    RelativeLayout relativec7;
    @BindView(R.id.textViewc7t)
    TextView textViewc7t;
    @BindView(R.id.component7)
    RelativeLayout component7;
    @BindView(R.id.recyclerbutc8)
    RelativeLayout recyclerbutc8;
    @BindView(R.id.imageViewc8)
    QMUIRadiusImageView imageViewc8;
    @BindView(R.id.imageViewc8s)
    QMUIRadiusImageView imageViewc8s;
    @BindView(R.id.textViewc8)
    TextView textViewc8;
    @BindView(R.id.imageViewc8t)
    ImageView imageViewc8t;
    @BindView(R.id.textViewc8s)
    TextView textViewc8s;
    @BindView(R.id.textViewc8t)
    TextView textViewc8t;
    @BindView(R.id.textViewc8a)
    TextView textViewc8a;
    @BindView(R.id.imageViewc8a)
    ImageView imageViewc8a;
    @BindView(R.id.imageViewc8b)
    ImageView imageViewc8b;
    @BindView(R.id.imageViewc8c)
    ImageView imageViewc8c;
    @BindView(R.id.imageViewc8d)
    ImageView imageViewc8d;
    @BindView(R.id.textViewc8b)
    TextView textViewc8b;
    @BindView(R.id.imageViewc8e)
    ImageView imageViewc8e;
    @BindView(R.id.imageViewc8f)
    ImageView imageViewc8f;
    @BindView(R.id.imageViewc8g)
    ImageView imageViewc8g;
    @BindView(R.id.imageViewc8h)
    ImageView imageViewc8h;
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.recyclerc8)
    RelativeLayout recyclerc8;
    @BindView(R.id.recyclerbutc8s)
    RelativeLayout recyclerbutc8s;
    @BindView(R.id.component8)
    RelativeLayout component8;
    @BindView(R.id.recyclerbutc9)
    RelativeLayout recyclerbutc9;
    @BindView(R.id.imageViewc9)
    ImageView imageViewc9;
    @BindView(R.id.imageViewc9s)
    QMUIRadiusImageView imageViewc9s;
    @BindView(R.id.textViewc9)
    TextView textViewc9;
    @BindView(R.id.textViewc9s)
    TextView textViewc9s;
    @BindView(R.id.imageViewc9t)
    ImageView imageViewc9t;
    @BindView(R.id.imageViewc9a)
    ImageView imageViewc9a;
    @BindView(R.id.textViewc9t)
    TextView textViewc9t;
    @BindView(R.id.textViewc9a)
    TextView textViewc9a;
    @BindView(R.id.textViewc9b)
    TextView textViewc9b;
    @BindView(R.id.textViewc9c)
    TextView textViewc9c;
    @BindView(R.id.recyclerc9)
    RecyclerView recyclerc9;
    @BindView(R.id.imageViewc9b)
    QMUIRadiusImageView imageViewc9b;
    @BindView(R.id.textViewc9d)
    TextView textViewc9d;
    @BindView(R.id.imageViewc9c)
    QMUIRadiusImageView imageViewc9c;
    @BindView(R.id.textViewc9e)
    TextView textViewc9e;
    @BindView(R.id.imageViewc9d)
    ImageView imageViewc9d;
    @BindView(R.id.relativec9)
    RelativeLayout relativec9;
    @BindView(R.id.component9)
    RelativeLayout component9;
    @BindView(R.id.svga1)
    SVGAImageView svga1;
    @BindView(R.id.svga2)
    SVGAImageView svga2;
    @BindView(R.id.svga3)
    SVGAImageView svga3;
    @BindView(R.id.svga)
    ConstraintLayout svga;
    @BindView(R.id.gift1)
    SVGAImageView gift1;
    @BindView(R.id.gift2)
    SVGAImageView gift2;
    @BindView(R.id.gift)
    ConstraintLayout gift;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.recyclerbutc12)
    RelativeLayout recyclerbutc12;
    @BindView(R.id.editTextc12)
    EditText editTextc12;
    @BindView(R.id.butc12)
    QMUIRoundButton butc12;
    @BindView(R.id.relativeLayout12)
    RelativeLayout relativeLayout12;

    private chatroom context;
    public static Observer<Integer> observer;
    public static Observer<Integer> observerchat;
    public static Observer<Integer> observers;
    private QMUIPopup mNormalPopup;
    private String conver;
    private Long sendid;
    private String sendname;
    private String sendsrc;
    private boolean bool = false;
    public static Observer<Integer> observersvga;
    private SVGAParser parser;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private RtcEngine mRtcEngine;
    private int mRoomMode;
    private String mChannelName;
    private String mTitleName;
    private Long mLocalUid;
    public static final String TAG = "chatroom";
    private int position;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private Boolean bIsBroadCaster = false;//上麦权限
    private Boolean administrator = false;//房主权限
    private Boolean Anchor = false;//管理员权限
    private Boolean host = false;//主持人权限


    /**
     * 声网频道内业务回调
     */
    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {

        @Override
        public void onUserJoined(final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "远端主播加入" + uid + "/" + elapsed);

                    if (ChatRoomModel.mUserList.size() < 9) {
                        for (int i = 0; i < ChatRoomModel.mUserList.size(); i++) {
                            if (ChatRoomModel.mUserList.get(i).getUid() == 0) {
                                Roomhead roomhead = new Roomhead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "苗苗", "", "", (long) uid, 0, false, false);
                                ChatRoomModel.locsuser(i, roomhead);
                                break;
                            }
                        }
                    }

                }
            });
        }

        @Override
        public void onUserOffline(final int uid, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 当用户离开时，从用户列表中清除
                    int uids = getUserIndex((long) uid);
                    Roomhead i1 = new Roomhead("", "", "", "", 0L, 0, false, false);
                    ChatRoomModel.mUserList.set(uids, i1);
                    ChatRoomModel.mAdapters.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onUserMuteAudio(final int uid, final boolean muted) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 收到某个uid mute 状态后刷新人员列表
                    int index = getUserIndex((long) uid);
                    ChatRoomModel.mUserList.get(index).setAudioMute(muted);
                    ChatRoomModel.mAdapters.notifyItemChanged(index);
                }
            });
        }

       /* @Override
        public void onJoinChannelSuccess(final String channel, final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // onJoinChannelSuccess 回调中，uid 不会为0
                    // 当 joinChannel api 中填入 0 时，agora 服务器会生成一个唯一的随机数，并在 onJoinChannelSuccess 回调中返回

                    SharedPreferences sp = Objects.requireNonNull(getSharedPreferences("Room", Context.MODE_PRIVATE));
                    String id = sp.getString("roomid", "");

                    if (bIsBroadCaster) {
                        Log.e(TAG, "主播加入" + uid);
                        if (mChannelName.equals(id)) {
                            Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                                @Override
                                public ObservableSource<? extends Integer> call() throws Exception {
                                    return Observable.just(11);
                                }
                            });
                            observable.subscribe(observer);
                        }
                    }


                }
            });
        }*/

        @Override
        public void onAudioVolumeIndication(final AudioVolumeInfo[] speakers, int totalVolume) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (speakers != null) {
                        for (AudioVolumeInfo audioVolumeInfo : speakers) {

                            if (audioVolumeInfo.uid != 0) {
                                int index = getUserIndex((long) audioVolumeInfo.uid);
                                if (index >= 0) {
                                    View view = ChatRoomModel.mLayoutManager.findViewByPosition(index);
                                    RippleBackground rippleBackground;
                                    if (view != null) {
                                        rippleBackground = view.findViewById(R.id.rippleback);
                                        if (audioVolumeInfo.volume > 0) {
                                            if (!rippleBackground.isRippleAnimationRunning()) {
                                                rippleBackground.startRippleAnimation();
                                            }
                                        } else {
                                            if (rippleBackground.isRippleAnimationRunning()) {
                                                rippleBackground.stopRippleAnimation();
                                            }
                                        }
                                    }
                                }
                            } else {
                                int index = getUserIndex(mLocalUid);
                                if (index >= 0) {
                                    System.out.println("音量" + audioVolumeInfo.volume);
                                    View view = ChatRoomModel.mLayoutManager.findViewByPosition(position);
                                    RippleBackground rippleBackground;
                                    if (view != null) {
                                        rippleBackground = view.findViewById(R.id.rippleback);
                                        if (audioVolumeInfo.volume > 0) {
                                            if (!rippleBackground.isRippleAnimationRunning()) {
                                                rippleBackground.startRippleAnimation();
                                            }
                                        } else {
                                            if (rippleBackground.isRippleAnimationRunning()) {
                                                rippleBackground.stopRippleAnimation();
                                            }
                                        }
                                    }

                                }
                            }

                        }
                    }

                }
            });
        }


    };
    private Window window;
    private QMUIBottomSheet.BottomListSheetBuilder builder;


    private int getUserIndex(Long uid) {

        for (int i = 0; i < ChatRoomModel.mUserList.size(); i++) {
            if (ChatRoomModel.mUserList.get(i).getUid().equals(uid)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_chatroom);
        ButterKnife.bind(this);
        context = this;
        window = this.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);

        ViewGroup.LayoutParams para1;
        para1 = view2.getLayoutParams();
        para1.height = height;
        view2.setLayoutParams(para1);

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)) {

            initAgoraEngineAndJoinChannel();
        }

        ChatRoomModel.initrecycler(context, recyclerview);
        ChatRoomModel.initData();
        ChatRoomModel.initrecyclers(context, gridview);

        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });


    }


    public boolean checkSelfPermission(String permission, int requestCode) {

        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQ_ID_RECORD_AUDIO) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initAgoraEngineAndJoinChannel();
            } else {
                showLongToast("No permission for " + Manifest.permission.RECORD_AUDIO);
                finish();
            }
        }
    }

    public final void showLongToast(final String msg) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }


    public void initAgoraEngineAndJoinChannel() {
        String appID = "cb1df47400774887bdc905241b4e7ea4";

        try {
            // 初始化SDK对象
            mRtcEngine = RtcEngine.create(context, appID, mRtcEventHandler);
            mRtcEngine.setLogFile("/sdcard/chatRoom.log");

        } catch (Exception e) {

            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }

        setupData();

        /** 根据房间类型设置 audioProfile **/
        switch (mRoomMode) {
            case Constant.ChatRoomGamingStandard:
                /** 开黑聊天室 */
                mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_SPEECH_STANDARD, Constants.AUDIO_SCENARIO_CHATROOM_GAMING);
                break;
            case Constant.ChatRoomEntertainmentStandard:
                /** 娱乐聊天室 */
                mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_STANDARD, Constants.AUDIO_SCENARIO_CHATROOM_ENTERTAINMENT);
                break;
            case Constant.ChatRoomEntertainmentHighQuality:
                /** K 歌房 */
                mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY, Constants.AUDIO_SCENARIO_CHATROOM_ENTERTAINMENT);
                break;
            case Constant.ChatRoomGamingHighQuality:
                /** FM 超高音质**/
                mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO, Constants.AUDIO_SCENARIO_CHATROOM_ENTERTAINMENT);
                break;
        }
        // 设置直播模式
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        // 启动音量监听
        mRtcEngine.enableAudioVolumeIndication(300, 3, true);
        //混响
        //AUDIO_REVERB_OFF(0)：原声，即关闭本地语音混响
        //AUDIO_REVERB_POPULAR(1)：流行
        //AUDIO_REVERB_RNB(2)：R&B
        //AUDIO_REVERB_ROCK(3)：摇滚
        //AUDIO_REVERB_HIPHOP(4)：嘻哈
        //AUDIO_REVERB_VOCAL_CONCERT(5)：演唱会
        //AUDIO_REVERB_KTV(6)：KTV
        //AUDIO_REVERB_STUDIO(7)：录音棚
        mRtcEngine.setLocalVoiceReverbPreset(7);
        // 当 joinChannel api 中填入 0 时，agora 服务器会生成一个唯一的随机数，并在 onJoinChannelSuccess 回调中返回
        SharedPreferences sp = Objects.requireNonNull(getSharedPreferences("User", Context.MODE_PRIVATE));
        mLocalUid = Long.valueOf(sp.getString("userid", ""));
        if (mChannelName.equals(sp.getString("userid", ""))) {
            administrator = true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mRtcEngine.joinChannel(null, mChannelName, "", Math.toIntExact(mLocalUid));
        }

    }

    /**
     * 获取从上一个界面传过来的频道信息，角色信息
     */
    private void setupData() {
        Intent intent = getIntent();
        if (intent != null) {
            mRoomMode = intent.getIntExtra(Constant.ACTION_KEY_ROOM_MODE, Constant.ChatRoomGamingStandard);
            bIsBroadCaster = intent.getIntExtra(Constant.ACTION_KEY_CROLE, Constants.CLIENT_ROLE_AUDIENCE) == Constants.CLIENT_ROLE_BROADCASTER;
            mChannelName = intent.getStringExtra(Constant.ACTION_KEY_ROOM_NAME);
            mTitleName = intent.getStringExtra(Constant.ACTION_KEY_TITLE_NAME);
            textView123.setText(mTitleName);
            textView121.setText("ID " + mChannelName);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @OnClick({R.id.fold, R.id.imageView98, R.id.imageView101, R.id.imageView102, R.id.imageView103, R.id.imageView99, R.id.textView124, R.id.imageView104, R.id.imageView105, R.id.textViewc2s, R.id.textViewc2t, R.id.textViewc2d, R.id.textViewc2f, R.id.textViewc3s, R.id.textViewc7t, R.id.butc1, R.id.butc11, R.id.butc12, R.id.imageView4, R.id.imageViewc1t, R.id.imageViewc2t, R.id.imageViewc5s, R.id.recyclerbutc1, R.id.recyclerbutc2, R.id.recyclerbutc3, R.id.recyclerbutc4, R.id.recyclerbutc5, R.id.recyclerbutc7, R.id.recyclerbutc8, R.id.recyclerbutc8s, R.id.recyclerbutc9, R.id.recyclerbutc11, R.id.recyclerbutc11s, R.id.recyclerbutc12, R.id.imageViewc1, R.id.imageViewc2, R.id.imageViewc3, R.id.imageViewc4, R.id.imageViewc5, R.id.imageViewc7, R.id.recyclerc8, R.id.imageViewc9, R.id.recyclerc11, R.id.relativec1, R.id.relativec2, R.id.relativec3, R.id.relativec4, R.id.relativec5, R.id.relativec7, R.id.relativec9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView101:
                QMUIPopups.quickAction(this,
                        QMUIDisplayHelper.dp2px(this, 46),
                        QMUIDisplayHelper.dp2px(this, 46))
                        .shadow(true)
                        .edgeProtection(QMUIDisplayHelper.dp2px(this, 20))
                        .addAction(new QMUIQuickAction.Action().icon(R.drawable.sets).text("设置").onClick(
                                new QMUIQuickAction.OnClickListener() {
                                    @Override
                                    public void onClick(QMUIQuickAction quickAction, QMUIQuickAction.Action action, int position) {
                                        if (administrator) {
                                            quickAction.dismiss();
                                            Intent intent2 = new Intent(chatroom.this, room_set.class);
                                            startActivity(intent2);
                                        } else {
                                            Toast.makeText(chatroom.this, "您没有此权限", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                        ))
                        .addAction(new QMUIQuickAction.Action().icon(R.drawable.musics).text("音乐").onClick(
                                new QMUIQuickAction.OnClickListener() {
                                    @Override
                                    public void onClick(QMUIQuickAction quickAction, QMUIQuickAction.Action action, int position) {
                                        quickAction.dismiss();
                                        Intent intent2 = new Intent(chatroom.this, my_music.class);
                                        startActivity(intent2);
                                    }
                                }
                        ))
                        .show(view);


                break;
            case R.id.imageView102:
                component5.setVisibility(View.VISIBLE);
                MessModel.initData(context);
                MessModel.initrecycler(context, context, recyclerc5, 1);
                break;
            case R.id.imageView103:
                if (!bIsBroadCaster) {
                    component7.setVisibility(View.VISIBLE);
                    PaimaiModel.initrecycler(context, recyclerc7);
                    textViewc7s.setText(PaimaiModel.mArrayList.size() + "人");
                } else {
                    // mRtcEngine.startAudioMixing("/assets/baidu.mp3",false,false,1);
                }
                break;
            case R.id.imageView104:
                //component12.setVisibility(View.VISIBLE);
                break;
            case R.id.imageView105:
                component4.setVisibility(View.VISIBLE);
                GiftModel.initData();
                GiftheadModel.initData();
                GiftModel.initrecycler(context, recyclerc4);
                GiftheadModel.initrecyclers(context, gridviewc4, textViewc4d);
                break;
            case R.id.imageView99:
            case R.id.textView124:
                Intent intent2 = new Intent(chatroom.this, room_online.class);
                startActivity(intent2);
                overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;

            case R.id.imageView98:
                Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> call() throws Exception {
                        return Observable.just(0, 2);
                    }
                });
                observable.subscribe(observersvga);
                break;

            case R.id.textViewc7t:
                SharedPreferences sp = Objects.requireNonNull(getSharedPreferences("User", Context.MODE_PRIVATE));
                String userid = sp.getString("userid", "");
                String nickname = sp.getString("nickname", "");
                String avatarUrl = sp.getString("avatarUrl", "");
                String gender = sp.getString("gender", "");
                if (bool) {
                    int i = PaimaiModel.get(userid);
                    PaimaiModel.Remove(i);
                    textViewc7t.setText("申请排麦");
                    bool = false;

                } else {
                    Holdpeople i1 = new Holdpeople(userid, avatarUrl, nickname, gender, "0");
                    PaimaiModel.Add(recyclerc7, i1);
                    textViewc7t.setText("取消排麦");
                    bool = true;
                }
                textViewc7s.setText(PaimaiModel.mArrayList.size() + "人");
                break;

            case R.id.textViewc3s:
                for (int i = 0; i < HoldpeopleAdapter.states.size(); i++) {
                    if (HoldpeopleAdapter.states.get(i) != null) {
                        Roomhead roomhead = new Roomhead(HoldpeopleAdapter.mEntityList.get(i).getUserima(), HoldpeopleAdapter.mEntityList.get(i).getName(), "", "", mLocalUid, 0, false, false);
                        ChatRoomModel.showBroadCast(mRtcEngine, mLocalUid, position, roomhead);
                        PaimaiModel.Remove(i);
                        component3.setVisibility(View.GONE);
                        bIsBroadCaster = true;
                    }
                }
                break;
            case R.id.imageViewc1t:
                component1.setVisibility(View.GONE);
                component5.setVisibility(View.VISIBLE);
                break;
            case R.id.imageViewc2t:
                component2.setVisibility(View.GONE);
                component5.setVisibility(View.VISIBLE);
                break;
            case R.id.butc1:
                String time = DateUtil.getCurrentTimeYMDHMS();
                Chats i1 = new Chats("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", editTextc1.getText().toString(), time, 2);
                ChatModel.Add(recyclerc1, i1);
                editTextc1.setText("");
                break;
            case R.id.butc11:
                //component11.setVisibility(View.GONE);
                break;
            case R.id.butc12:
                Roomtxt entity = new Roomtxt(editTextc12.getText().toString(), "周润发", "https://momeak.oss-cn-shenzhen.aliyuncs.com/l3.png", "");
                ChatRoomModel.Add(recyclerview, entity);
               /* Observable.just(1, 2)
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                if (integer == 1) {
                                    Roomtxt entity = new Roomtxt("进入房间打赏", "周润发", "https://momeak.oss-cn-shenzhen.aliyuncs.com/l3.png", "");
                                    ChatRoomModel.Add(recyclerview, entity);
                                } else if (integer == 2) {
                                    Roomtxt entity = new Roomtxt("【房间公告】", "", "", "---为了更好的体验请大家文明用语---");
                                    ChatRoomModel.Add(recyclerview, entity);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });*/
                break;
            case R.id.imageViewc5s:
                component5.setVisibility(View.GONE);
                component2.setVisibility(View.VISIBLE);
                textViewc2s.setVisibility(View.GONE);
                textViewc2d.setVisibility(View.VISIBLE);
                textViewc2t.setVisibility(View.VISIBLE);
                textViewc2f.setVisibility(View.GONE);
                MessFriendsModel.initData(context, 1,recyclerc2);
                break;
            case R.id.textViewc2s:
                textViewc2s.setVisibility(View.GONE);
                textViewc2d.setVisibility(View.VISIBLE);
                textViewc2t.setVisibility(View.VISIBLE);
                textViewc2f.setVisibility(View.GONE);
                MessFriendsModel.initData(context, 1,recyclerc2);
                break;
            case R.id.textViewc2d:
                MessFriendsModel.initData(context, 2,recyclerc2);
                break;
            case R.id.textViewc2t:
                textViewc2s.setVisibility(View.VISIBLE);
                textViewc2d.setVisibility(View.GONE);
                textViewc2t.setVisibility(View.GONE);
                textViewc2f.setVisibility(View.VISIBLE);
                MessFriendsModel.initData(context, 1,recyclerc2);
                break;
            case R.id.textViewc2f:
                MessFriendsModel.initData(context, 2,recyclerc2);
                break;
            case R.id.imageView4:
                showSimpleBottomSheetGrid();
                break;
            case R.id.fold:
                showSimpleBottomSheetList(
                        true, true, false, "您确定要离开房间吗？",
                        2, true, false);
                break;

            case R.id.recyclerbutc1:
                component1.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc2:
                component5.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc3:
                component3.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc4:
                component4.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc5:
                component5.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc7:
                component7.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc8:
            case R.id.recyclerbutc8s:
                component8.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc9:
                component9.setVisibility(View.GONE);
                break;
            case R.id.recyclerbutc11:
            case R.id.recyclerbutc11s:
                break;
            case R.id.recyclerbutc12:
                //component12.setVisibility(View.GONE);
                break;
            case R.id.imageViewc1:
            case R.id.imageViewc2:
            case R.id.imageViewc3:
            case R.id.imageViewc4:
            case R.id.imageViewc5:
            case R.id.imageViewc7:
            case R.id.recyclerc8:
            case R.id.imageViewc9:
            case R.id.recyclerc11:
            case R.id.relativec1:
            case R.id.relativec2:
            case R.id.relativec3:
            case R.id.relativec4:
            case R.id.relativec5:
            case R.id.relativec7:
            case R.id.relativec9:
                break;
        }
    }

    private void showMessagePositiveDialog(int dex) {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("您已上麦，是否下麦？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_POSITIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        ChatRoomModel.showAudience(mRtcEngine, dex);
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * Activity创建或者从后台重新回到前台时被调用
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 子页面回调（按页面顺序）
         */
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                switch (integer) {
                    case 0:
                        if (mRtcEngine != null) {
                            mRtcEngine.leaveChannel();
                        }
                        RtcEngine.destroy();
                        chatroom.this.finish();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        component5.setVisibility(View.GONE);
                        component1.setVisibility(View.VISIBLE);
                        conver = "";
                        sendid = 0L;
                        sendname = "苗苗";
                        sendsrc = "https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg";
                        textViewc1.setText(sendname);
                        ChatModel.initData(conver, 0L, 0, 10);
                        ChatModel.initrecycler(chatroom.this, recyclerc1);
                        KeyboardStateObserver.getKeyboardStateObserver(chatroom.this).
                                setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
                                    @Override
                                    public void onKeyboardShow() {
                                        ChatModel.recly(recyclerc1, 0);
                                    }

                                    @Override
                                    public void onKeyboardHide() {
                                        ChatModel.recly(recyclerc1, 0);
                                    }
                                });
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                            component8.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        Log.e(TAG, "主持人");
                        Glide.with(context).load("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg").into(imageView2);
                        textView39.setText("苗苗主播");
                        textView19.setText("萌妹带你飞123");
                        break;
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };

        /**
         * 坑位
         */
        observers = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer view) {
                System.out.println("坑位" + view);
                position = view;
                //administrator || Anchor || host ||
                if (administrator || Anchor || host || mLocalUid.equals(ChatRoomModel.mUserList.get(position).getUid())) {
                    onClickBtn2(gridview.getChildAt(view));
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        /**
         * 私聊
         */
        observerchat = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {


                String time = DateUtil.getCurrentTimeYMDHMS();
                String txt = editTextc1.getText().toString();
                if (txt.equals("")) {
                    Chats i1 = new Chats("https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", txt, time, 2);
                    ChatModel.Add(recyclerc1, i1);
                    editTextc1.setText("");
                }

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

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        /**
         * 显示svga动画
         */
        observersvga = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                svga.setVisibility(View.VISIBLE);
                switch (integer) {
                    case 0:
                        loadAnimation(svga1, 0, "https://github.com/yyued/SVGA-Samples/blob/master/posche.svga?raw=true", "", "", "", "");
                        break;
                    case 1:
                        loadAnimation(svga2, 1, "https://github.com/yyued/SVGA-Samples/blob/master/kingset.svga?raw=true", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "99", "", "");
                        break;
                    case 2:
                        loadAnimation(svga3, 1, "https://github.com/yyued/SVGA-Samples/blob/master/kingset.svga?raw=true", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg", "99", "Pony send Kitty flowers.", "banner");
                        break;

                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };

    }

    @Override
    public void onBackPressed() {
        showSimpleBottomSheetList(
                true, true, false, "您确定要离开房间吗？",
                2, true, false);
    }

    /**
     * 坑位弹窗
     */
    private void onClickBtn2(View v) {
        String[] listItems = new String[]{
                "抱人",
                "禁言",
                "加锁",
                "一键全锁",
                "下麦",
        };
        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);

        ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.simple_list_item, data);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        component3.setVisibility(View.VISIBLE);
                        PaimaiModel.initrecyclers(context, recyclerc3);
                        break;
                    case 1:
                        ChatRoomModel.self(mRtcEngine, position);
                        ChatRoomModel.mUserList.get(position).setMsima("1");
                        ChatRoomModel.mAdapters.notifyItemChanged(position);
                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                    case 4:
                        showMessagePositiveDialog(position);
                        break;
                }
                if (mNormalPopup != null) {
                    mNormalPopup.dismiss();
                }
            }
        };
        mNormalPopup = QMUIPopups.listPopup(context,
                QMUIDisplayHelper.dp2px(context, 250),
                QMUIDisplayHelper.dp2px(context, 300),
                adapter,
                onItemClickListener)
                .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                .preferredDirection(QMUIPopup.DIRECTION_TOP)
                .shadow(true)
                .offsetYIfTop(QMUIDisplayHelper.dp2px(context, 5))
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                })
                .show(v);
    }

    /**
     * 退出弹窗
     */
    private void showSimpleBottomSheetList(boolean gravityCenter,
                                           boolean addCancelBtn,
                                           boolean withIcon,
                                           CharSequence title,
                                           int itemCount,
                                           boolean allowDragDismiss,
                                           boolean withMark) {
        builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        builder.setGravityCenter(gravityCenter)
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if (position == 0) {
                            Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                                @Override
                                public ObservableSource<? extends Integer> call() throws Exception {
                                    return Observable.just(0);
                                }
                            });
                            observable.subscribe(chatroom.observer);
                        } else {
                            moveTaskToBack(true);
                            Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                                @Override
                                public ObservableSource<? extends Integer> call() throws Exception {
                                    return Observable.just(0);
                                }
                            });
                            observable.subscribe(MainActivity.observer);

                        }
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }

        builder.addItem("直接离开");
        builder.addItem("悬浮窗模式");
        builder.build().show();
    }


    /**
     * 分享弹窗
     */
    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(this);
        builder.addItem(R.drawable.wei_xing, "微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.pengyouquan, "朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.qq, "QQ", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.qqkongjian, "QQ空间", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .setAddCancelBtn(true)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        switch (tag) {
                            case TAG_SHARE_WECHAT_FRIEND:
                                Toast.makeText(chatroom.this, "分享到微信", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_WECHAT_MOMENT:
                                Toast.makeText(chatroom.this, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_WEIBO:
                                Toast.makeText(chatroom.this, "分享到微博", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_CHAT:
                                Toast.makeText(chatroom.this, "分享到私信", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }).build().show();


    }


    /**
     * 加载svga
     */
    private void loadAnimation(SVGAImageView svga, int type, String url, String ima, String imaforkey, String txt, String txtforkey) {
        // new URL needs try catch.
        parser = new SVGAParser(this);
        try {
            switch (type) {
                case 0:
                    parser.decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                            SVGADrawable drawable = new SVGADrawable(videoItem);
                            svga.setImageDrawable(drawable);
                            svga.startAnimation();
                        }

                        @Override
                        public void onError() {

                        }
                    });
                    break;
                case 1:
                    parser.decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                            SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();

                            if (!ima.equals("")) {
                                dynamicEntity.setDynamicImage(ima, imaforkey); // Here is the KEY implementation.
                                TextPaint textPaint = new TextPaint();
                                textPaint.setColor(Color.WHITE);
                                textPaint.setTextSize(28);
                                dynamicEntity.setDynamicText(txt, textPaint, txtforkey);

                            }
                            SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                            svga.setImageDrawable(drawable);
                            svga.startAnimation();
                        }

                        @Override
                        public void onError() {

                        }
                    });
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

