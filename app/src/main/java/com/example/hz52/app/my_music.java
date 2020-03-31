package com.example.hz52.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.MusicViewAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Model.MusicModel;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.cofig.LogDownloadListener;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mMusicDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.ywl5320.libenum.MuteEnum;
import com.ywl5320.libmusic.WlMusic;
import com.ywl5320.listener.OnCompleteListener;
import com.ywl5320.listener.OnPreparedListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static android.os.Environment.DIRECTORY_MUSIC;

public class my_music extends AppCompatActivity {


    @BindView(R.id.recycler5)
    RecyclerView recycler5;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView141)
    ImageView imageView141;
    public static WlMusic wlMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        ButterKnife.bind(this);
        title.setText("我的音乐");
        subtitle.setText("添加");
        Context context = this;
        Initialization.setupDatabaseMusic(this);

        MusicModel.initData(context,recycler5);


    }

    @OnClick({R.id.fold, R.id.subtitle, R.id.imageView141})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.subtitle:
                Intent intent1 = new Intent(my_music.this, scan_code.class);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView141:
                Intent intent2 = new Intent(my_music.this, my_music_search.class);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }


    public static void music(int postion) {
        wlMusic = WlMusic.getInstance();
        wlMusic.setSource(MusicModel.mArrayList.get(postion).getUrl()); //设置音频源
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

        System.out.println("状态：" + wlMusic.isPlaying());
        System.out.println("时长" + wlMusic.getDuration());

        //seek时间
       /* seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                position = wlMusic.getDuration() * progress / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                wlMusic.seek(position, false, false);// 表示在seeking中，此时不回调时间
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                wlMusic.seek(position, true, true);//表示seek已经完成，然后才回调时间，避免自己控制时间逻辑和时间显示不稳定问题。
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if(wlMusic.isPlaying()){
                wlMusic.stop();
            }
        }catch (Exception ignored){}

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
