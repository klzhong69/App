package com.example.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.Roomtxt;
import com.example.app.Model.ChatRoomModel;
import com.qmuiteam.qmui.layout.QMUIPriorityLinearLayout;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class chatroom extends AppCompatActivity {

    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.gridview)
    RecyclerView gridview;
    @BindView(R.id.imageView40)
    ImageView imageView40;
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
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.textView39)
    TextView textView39;
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
    @BindView(R.id.recyclerbut)
    RelativeLayout recyclerbut;
    @BindView(R.id.imageView109)
    ImageView imageView109;
    @BindView(R.id.textView128)
    TextView textView128;
    @BindView(R.id.textView129)
    TextView textView129;
    @BindView(R.id.recycler16)
    RecyclerView recycler16;
    @BindView(R.id.relative11)
    RelativeLayout relative11;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView45)
    ImageView imageView45;
    @BindView(R.id.priority)
    QMUIPriorityLinearLayout priority;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.scrollmess)
    NestedScrollView scrollmess;
    private Bitmap bitmap;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        ButterKnife.bind(this);

        Context context = this;
        ChatRoomModel.initData();
        ChatRoomModel.initrecycler(context, mRecyclerView);
        ChatRoomModel.initrecyclers(context, gridview);

        
    }


    @OnClick(R.id.imageView4)
    public void onViewClicked() {
        Observable.interval(1, TimeUnit.SECONDS)
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
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    @OnClick({R.id.imageView101, R.id.imageView102, R.id.imageView103, R.id.imageView104, R.id.imageView105})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView101:
               /* QMUIPopups.quickAction(getContext(),
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
                        .show(v);*/
                break;
            case R.id.imageView102:
                break;
            case R.id.imageView103:
                break;
            case R.id.imageView104:
                break;
            case R.id.imageView105:
                break;
        }
    }
}

