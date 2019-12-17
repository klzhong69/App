package com.example.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.ChatModel;
import com.example.app.Model.ChatRoomModel;
import com.example.app.Model.GiftModel;
import com.example.app.Model.GiftheadModel;
import com.example.app.Model.HoldModel;
import com.example.app.Model.MessFriendsModel;
import com.example.app.Model.MessModel;
import com.example.app.Model.OnlineModel;
import com.qmuiteam.qmui.layout.QMUIPriorityLinearLayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.qmuiteam.qmui.widget.popup.QMUIQuickAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.textView39)
    TextView textView39;
    @BindView(R.id.gridview)
    RecyclerView gridview;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
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
    @BindView(R.id.imageViewc10)
    QMUIRadiusImageView imageViewc10;
    @BindView(R.id.imageViewc10s)
    ImageView imageViewc10s;
    @BindView(R.id.textViewc10)
    TextView textViewc10;
    @BindView(R.id.butc10)
    QMUIRoundButton butc10;
    @BindView(R.id.textView148)
    TextView textView148;
    @BindView(R.id.component10)
    RelativeLayout component10;
    @BindView(R.id.recyclerbutc1)
    RelativeLayout recyclerbutc1;
    @BindView(R.id.imageViewc1)
    ImageView imageViewc1;
    @BindView(R.id.textViewc1)
    TextView textViewc1;
    @BindView(R.id.imageViewc1t)
    ImageView imageViewc1t;
    @BindView(R.id.editTextc1)
    EditText editTextc1;
    @BindView(R.id.butc1)
    QMUIRoundButton butc1;
    @BindView(R.id.imageViewc1s)
    ImageView imageViewc1s;
    @BindView(R.id.priorityc1)
    QMUIPriorityLinearLayout priorityc1;
    @BindView(R.id.layoutc1)
    LinearLayout layoutc1;
    @BindView(R.id.scrollmessc1)
    NestedScrollView scrollmessc1;
    @BindView(R.id.relativec1)
    RelativeLayout relativec1;
    @BindView(R.id.component1)
    RelativeLayout component1;
    @BindView(R.id.recyclerbutc2)
    RelativeLayout recyclerbutc2;
    @BindView(R.id.imageViewc2)
    ImageView imageViewc2;
    @BindView(R.id.textViewc2)
    TextView textViewc2;
    @BindView(R.id.textViewc2s)
    TextView textViewc2s;
    @BindView(R.id.textViewc2t)
    TextView textViewc2t;
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
    @BindView(R.id.recyclerc5s)
    RecyclerView recyclerc5s;
    @BindView(R.id.relativec5)
    RelativeLayout relativec5;
    @BindView(R.id.component5)
    RelativeLayout component5;
    @BindView(R.id.recyclerbutc6)
    RelativeLayout recyclerbutc6;
    @BindView(R.id.imageViewc6)
    ImageView imageViewc6;
    @BindView(R.id.textView128)
    TextView textView128;
    @BindView(R.id.recyclerc6)
    RecyclerView recyclerc6;
    @BindView(R.id.relativec6)
    RelativeLayout relativec6;
    @BindView(R.id.component6)
    RelativeLayout component6;
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
    @BindView(R.id.component7)
    RelativeLayout component7;
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
    @BindView(R.id.recyclerbutc10)
    RelativeLayout recyclerbutc10;
    @BindView(R.id.recyclerbutc8)
    RelativeLayout recyclerbutc8;
    @BindView(R.id.recyclerbutc8s)
    RelativeLayout recyclerbutc8s;
    @BindView(R.id.recyclerbutc10s)
    RelativeLayout recyclerbutc10s;
    @BindView(R.id.recyclerc10)
    RelativeLayout recyclerc10;
    @BindView(R.id.recyclerc8)
    RelativeLayout recyclerc8;
    private Bitmap bitmap;
    private Disposable disposable;
    private chatroom context;
    public static Observer<Integer> observer;
    public static Observer<View> observers;
    private QMUIPopup mNormalPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        ButterKnife.bind(this);

        context = this;
        ChatRoomModel.initData();
        ChatRoomModel.initrecycler(context, recyclerview);
        ChatRoomModel.initrecyclers(context, gridview);

       /* Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("bqt", "【订阅】" + aLong);
                        Roomtxt entity = new Roomtxt("周润发", "进入房间打赏流附近的司法所的飞机但是减肥i哦" + aLong, "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png");
                        ChatRoomModel.Add(mRecyclerView, entity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    @OnClick({R.id.imageView101, R.id.imageView102, R.id.imageView103, R.id.imageView99, R.id.textView124, R.id.imageView104, R.id.imageView105, R.id.butc1, R.id.butc10, R.id.imageView4, R.id.imageViewc1t, R.id.imageViewc2t, R.id.imageViewc5s, R.id.recyclerbutc1, R.id.recyclerbutc2, R.id.recyclerbutc3, R.id.recyclerbutc4, R.id.recyclerbutc5, R.id.recyclerbutc6, R.id.recyclerbutc7, R.id.recyclerbutc8, R.id.recyclerbutc8s, R.id.recyclerbutc9, R.id.recyclerbutc10, R.id.recyclerbutc10s, R.id.imageViewc1, R.id.imageViewc2, R.id.imageViewc3, R.id.imageViewc4, R.id.imageViewc5, R.id.imageViewc6, R.id.imageViewc7, R.id.recyclerc8, R.id.imageViewc9, R.id.recyclerc10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView101:
                QMUIPopups.quickAction(this,
                        QMUIDisplayHelper.dp2px(this, 56),
                        QMUIDisplayHelper.dp2px(this, 56))
                        .shadow(true)
                        .edgeProtection(QMUIDisplayHelper.dp2px(this, 20))
                        .addAction(new QMUIQuickAction.Action().icon(R.drawable.back).text("复制").onClick(
                                new QMUIQuickAction.OnClickListener() {
                                    @Override
                                    public void onClick(QMUIQuickAction quickAction, QMUIQuickAction.Action action, int position) {
                                        quickAction.dismiss();
                                        Toast.makeText(chatroom.this, "复制成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ))
                        .addAction(new QMUIQuickAction.Action().icon(R.drawable.back).text("划线").onClick(
                                new QMUIQuickAction.OnClickListener() {
                                    @Override
                                    public void onClick(QMUIQuickAction quickAction, QMUIQuickAction.Action action, int position) {
                                        quickAction.dismiss();
                                        Toast.makeText(chatroom.this, "划线成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ))
                        .addAction(new QMUIQuickAction.Action().icon(R.drawable.back).text("分享").onClick(
                                new QMUIQuickAction.OnClickListener() {
                                    @Override
                                    public void onClick(QMUIQuickAction quickAction, QMUIQuickAction.Action action, int position) {
                                        quickAction.dismiss();
                                        Toast.makeText(chatroom.this, "分享成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ))
                        .show(view);
                break;
            case R.id.imageView102:
                component5.setVisibility(View.VISIBLE);
                MessModel.initData();
                MessModel.initrecycler(context, recyclerc5, 1);
                MessModel.initrecyclers(context, recyclerc5s, 1);
                break;
            case R.id.imageView103:
                component10.setVisibility(View.VISIBLE);
                break;
            case R.id.imageView104:
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
                component6.setVisibility(View.VISIBLE);
                OnlineModel.initData();
                OnlineModel.initrecycler(context, recyclerc6);
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
                layoutc1.addView(ChatModel.add(editTextc1.getText().toString(), "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png", "", 0, 1));
                editTextc1.setText("");
                break;
            case R.id.butc10:
                component10.setVisibility(View.GONE);
                break;
            case R.id.imageViewc5s:
                component5.setVisibility(View.GONE);
                component2.setVisibility(View.VISIBLE);
                MessFriendsModel.initData(0);
                MessFriendsModel.initrecycler(context, recyclerc2, 1);
                break;
            case R.id.imageView4:
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
            case R.id.recyclerbutc6:
                component6.setVisibility(View.GONE);
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
            case R.id.recyclerbutc10:
            case R.id.recyclerbutc10s:
                break;

            case R.id.imageViewc1:
            case R.id.imageViewc2:
            case R.id.imageViewc3:
            case R.id.imageViewc4:
            case R.id.imageViewc5:
            case R.id.imageViewc6:
            case R.id.imageViewc7:
            case R.id.recyclerc8:
            case R.id.imageViewc9:
            case R.id.recyclerc10:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                switch (integer) {
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
                        ChatModel.set(scrollmessc1, editTextc1, LayoutInflater.from(chatroom.this), layoutc1, butc1, imageViewc1s);
                        ChatModel.initData(layoutc1);
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
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


        observers = new Observer<View>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(View view) {

                String[] listItems = new String[]{
                        "抱人",
                        "禁言",
                        "加锁",
                        "一键全锁",
                        "礼物",
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
                                HoldModel.initData();
                                HoldModel.initrecycler(context, recyclerc3);
                                break;
                            case 1:
                                break;
                            case 2:

                                break;
                            case 3:
                                break;
                            case 4:
                                component9.setVisibility(View.VISIBLE);
                                GiftModel.initData();
                                GiftModel.initrecycler(context, recyclerc9);
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
                        .show(view);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void showSimpleBottomSheetList(boolean gravityCenter,
                                           boolean addCancelBtn,
                                           boolean withIcon,
                                           CharSequence title,
                                           int itemCount,
                                           boolean allowDragDismiss,
                                           boolean withMark) {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        builder.setGravityCenter(gravityCenter)
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if(position==0){
                            chatroom.this.finish();
                        }else if(position==1){

                        }
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }
        /*for (int i = 1; i <= itemCount; i++) {
            if(withIcon){
                builder.addItem(ContextCompat.getDrawable(this, R.mipmap.icon_tabbar_lab), "Item " + i);
            }else{
                builder.addItem("Item " + i);
            }

        }*/
        builder.addItem("直接离开");
        builder.addItem("悬浮窗模式");
        builder.build().show();
    }

    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(this);
        builder.addItem(R.drawable.icon_more_operation_share_friend, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_moment, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_weibo, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_chat, "分享到私信", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
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

}
